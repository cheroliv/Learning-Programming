package fr.chalodss.classes.graphs.mazes;

import static fr.chalodss.classes.graphs.mazes.ECell.*;

import fr.chalodss.utils.Services;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * @author Charles T.
 * 
 */
public final class ControllerMaze {

  private static final List<ECell>  CELLS = Arrays.asList(END, PATH, START, WALL);
  private static final List<Number> SIZES = Arrays.asList(80, 40, 20, 16, 10, 8, 5, 4, 2);
  private static final List<Number> TIMES = Arrays.asList(1, 0.1, 0.01, 0.001, 0.0001);

  @FXML
  private ComboBox<Number>          cmbCellSizes;
  @FXML
  private TextField                 densities;
  @FXML
  private Label                     mazeSize;
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
  private MazeGrid2D                maze;
  private Future<Boolean>           result;


  public ControllerMaze(Pane pane, String algorithm) {
    this.board     = pane;
    this.algorithm = algorithm;
    this.mazeLayer = new Canvas();
    this.gridLayer = new Canvas();
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
    densities.setText("0.3");
    updateMazeSize();
  }

  @FXML
  public void init() {
    initLogic();
    initServices();
    initView();
  }

  @FXML
  public void run() {
    var service = Services.getSvc();

    if (service != null && maze.isMazeInitialized()) {
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
    if (result != null) {
      double time   = cmbTimes.getValue().doubleValue() * 1000;
      int    length = maze.path.size();

      if (length > 1) {
        maze.path.remove(maze.start);
        maze.path.remove(maze.end);
        ViewMaze.setAlgoColor(algorithm);
        ViewMaze.drawPath(maze.path, time);
      }
    }
    result = null;
  }

  @FXML
  private void updateMazeSize() {
    var cellSize = cmbCellSizes.getValue().intValue();
    var mapSize  = (1280 * 720) / (cellSize * cellSize);

    mazeSize.setText("" + mapSize);
  }

  private void onCanvasPressed(int dim) {
    gridLayer.setOnMouseClicked(e -> {
      var x    = (int) (e.getX() / dim);
      var y    = (int) (e.getY() / dim);
      var cell = maze.data[y][x];

      if (cell.type != WALL) {
        cell.type = cmbCells.getValue();
        ViewMaze.setCellColor(cell.type);
        ViewMaze.drawCell(cell);
        switch (cell.type) {
          case START -> maze.start = maze.data[y][x];
          case END -> maze.end = maze.data[y][x];
          default -> throw new IllegalArgumentException();
        }
        cell.visited = (cell.type == START);
        cmbCells.setValue(cell.type == START ? END : WALL);
      }
    });
  }

  private void initLogic() {
    int    dim     = cmbCellSizes.getValue().intValue();
    double density = Double.parseDouble(densities.getText());
    int    width   = (int) (gridLayer.getWidth() / dim);
    int    heigth  = (int) (gridLayer.getHeight() / dim);

    maze = new MazeGrid2D(width, heigth, density);
    maze.setAlgorithm(algorithm);
    maze.setAdjacentCells();
  }

  private void initServices() {
    Services.shutdownAndAwaitTermination();
    Services.setSvc(Executors.newSingleThreadExecutor());
  }

  private void initView() {
    int dim = cmbCellSizes.getValue().intValue();

    cmbCells.setValue(START);
    pathFound.setText("- - - - -");
    pathLength.setText("0");
    ViewMaze.setDim(dim);
    ViewMaze.reset(gridLayer.getWidth(), gridLayer.getHeight());
    ViewMaze.drawMaze(maze);
    ViewMaze.drawGrid();
    onCanvasPressed(dim);
  }

}
