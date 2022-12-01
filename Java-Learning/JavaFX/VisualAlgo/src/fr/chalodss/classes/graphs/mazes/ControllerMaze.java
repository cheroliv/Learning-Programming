package fr.chalodss.classes.graphs.mazes;

import static fr.chalodss.classes.graphs.mazes.ECell.*;

import fr.chalodss.utils.Services;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 * @author Charles T.
 * 
 */
public final class ControllerMaze {

  private static final List<ECell>  CELLS = Arrays.asList(END, GRASS, MOUNTAIN, PATH, START, WALL, WATER);
  private static final List<Number> SIZES = Arrays.asList(80, 40, 20, 16, 8, 4, 2);
  private static final List<Number> TIMES = Arrays.asList(1, 0.1, 0.01, 0.001, 0.0001);

  @FXML
  private ComboBox<Number>          cmbCellSizes;
  @FXML
  private CheckBox                  boxRand;
  @FXML
  private CheckBox                  boxFile;
  @FXML
  private TextField                 densities;
  @FXML
  private ComboBox<Number>          cmbTimes;
  @FXML
  private ComboBox<ECell>           cmbCells;
  @FXML
  private Label                     pathFound;
  @FXML
  private Label                     pathLength;

  private Pane                      board;
  private Canvas                    mazeLayer;
  private Canvas                    gridLayer;

  private String                    algorithm;
  private int                       cellSize;
  private double                    density;
  private MazeGrid2D                maze;
  private Future<Boolean>           result;
  private double                    time;


  public ControllerMaze(Pane pane, String algorithm) {
    this.board     = pane;
    this.algorithm = algorithm;
    this.mazeLayer = new Canvas();
    this.gridLayer = new Canvas();
    this.cellSize  = 80;
    this.time      = 1000;
  }

  @FXML
  public void initialize() {
    cmbCellSizes.setItems(FXCollections.observableList(SIZES));
    cmbCellSizes.getSelectionModel().select(0);
    cmbTimes.setItems(FXCollections.observableList(TIMES));
    cmbTimes.getSelectionModel().select(0);
    cmbCells.setItems(FXCollections.observableList(CELLS));
    cmbCells.getSelectionModel().select(0);

    mazeLayer.widthProperty().bind(board.widthProperty());
    mazeLayer.heightProperty().bind(board.heightProperty());

    gridLayer.widthProperty().bind(board.widthProperty());
    gridLayer.heightProperty().bind(board.heightProperty());

    board.getChildren().add(mazeLayer);
    board.getChildren().add(gridLayer);

    var gcMazeLayer = mazeLayer.getGraphicsContext2D();
    var gcGrid      = gridLayer.getGraphicsContext2D();

    ViewMaze.setGcMazeLayer(gcMazeLayer);
    ViewMaze.setGcGrid(gcGrid);

    boxRand.selectedProperty().addListener(e -> boxFile.setSelected(!boxRand.isSelected()));
    boxFile.selectedProperty().addListener(e -> boxRand.setSelected(!boxFile.isSelected()));
    boxRand.setSelected(true);

    cmbCellSizes.valueProperty().addListener(e -> cellSize = cmbCellSizes.getValue().intValue());
    densities.textProperty().addListener(e -> density = Double.parseDouble(densities.getText()));
    cmbTimes.valueProperty().addListener(e -> time = cmbTimes.getValue().doubleValue() * 1000);
  }

  @FXML
  public void init() {
    initLogic();
    initView();
    initServices();
  }

  @FXML
  public void run() {
    var service = Services.getSvc();

    if (service != null && maze.isInitialized()) {
      result = service.submit(maze);
      try {
        pathFound.setText(result.get().toString());
        pathLength.setText(String.valueOf(maze.path.size()));
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
      }
      Services.shutdownAndAwaitTermination();
    }
  }

  @FXML
  private void draw() {
    if (result != null && maze.path.size() > 1) {
      maze.path.remove(maze.start);
      maze.path.remove(maze.end);
      ViewMaze.setAlgoColor(algorithm);
      ViewMaze.drawPath(maze.path, time);
    }
    result = null;
  }

  @FXML
  private void loadMaze() throws IOException {
    var          fileChooser  = new FileChooser();
    var          selectedFile = fileChooser.showOpenDialog(null);
    List<String> lines        = null;

    if (selectedFile != null) {
      lines = Files.readAllLines(Paths.get(selectedFile.getPath()));
      var height = lines.size();
      var width  = lines.get(0).length();
      var data   = new Cell[height][width];
      var i      = 0;

      cellSize = 1280 / width;
      for (var line : lines) {
        for (var j = 0; j < line.length(); j++) {
          char c = line.charAt(j);
          switch (c) {
            case 'S' -> data[i][j] = new Cell(START, i, j, 0);
            case 'E' -> data[i][j] = new Cell(END, i, j, 0);
            case 'P' -> data[i][j] = new Cell(PATH, i, j, 1);
            case 'G' -> data[i][j] = new Cell(GRASS, i, j, 2);
            case 'M' -> data[i][j] = new Cell(MOUNTAIN, i, j, 8);
            case 'X' -> data[i][j] = new Cell(WALL, i, j, 0);
            case 'W' -> data[i][j] = new Cell(WATER, i, j, 4);
            default -> throw new IllegalArgumentException();
          }
        }
        i++;
      }
      maze = new MazeGrid2D(width, height, data);
      maze.setStart();
      maze.setEnd();
    }
  }

  private void initLogic() {
    if (boxRand.isSelected()) {
      initRand();
    } else if (boxFile.isSelected()) {
      initFile();
    }
  }

  private void initRand() {
    maze = new MazeGrid2D(1280 / cellSize, 720 / cellSize, density);
    maze.setAlgorithm(algorithm);
    maze.setAdjacentCells();
  }

  private void initFile() {
    if (maze != null) {
      maze.setAlgorithm(algorithm);
      maze.setAdjacentCells();
    }
  }

  private void initServices() {
    Services.shutdownAndAwaitTermination();
    Services.setSvc(Executors.newSingleThreadExecutor());
  }

  private void initView() {
    if (maze != null) {
      cmbCells.setValue(START);
      pathFound.setText("- - - - -");
      pathLength.setText("0");
      ViewMaze.setCellSize(cellSize);
      ViewMaze.reset(1280, 720);
      ViewMaze.drawMaze(maze);
      ViewMaze.drawGrid();
      onMouseCliked(cellSize);
    }
  }

  private void onMouseCliked(int dim) {
    gridLayer.setOnMouseClicked(e -> {
      var x    = (int) (e.getX() / dim);
      var y    = (int) (e.getY() / dim);
      var cell = maze.data[y][x];

      cell.type = cmbCells.getValue();
      ViewMaze.setCellColor(cell.type);
      ViewMaze.drawCell(cell);
      switch (cell.type) {
        case START:
          maze.start = maze.data[y][x];
          maze.start.distance = 0;
          maze.start.visited = true;
          cmbCells.setValue(END);
          break;
        case END:
          maze.end = maze.data[y][x];
          cmbCells.setValue(START);
          break;
        case GRASS:
          maze.data[y][x].type = GRASS;
          maze.data[y][x].weight = 2;
          break;
        case MOUNTAIN:
          maze.data[y][x].type = MOUNTAIN;
          maze.data[y][x].weight = 8;
          break;
        case WALL:
          maze.data[y][x].type = WALL;
          break;
        case WATER:
          maze.data[y][x].type = WATER;
          maze.data[y][x].weight = 4;
          break;
        default:
          break;
      }
    });
  }

}
