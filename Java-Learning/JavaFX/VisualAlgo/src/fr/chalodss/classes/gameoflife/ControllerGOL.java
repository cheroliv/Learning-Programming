package fr.chalodss.classes.gameoflife;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 * @author Charles T.
 * 
 */
public final class ControllerGOL {

  private static final List<Number> SIZES  = Arrays.asList(40, 20, 16, 10, 8, 5, 4, 2);
  private static final List<Number> TIMES  = Arrays.asList(1, 0.1, 0.01);
  private static final List<String> STATES = Arrays.asList("ALIVE", "DEAD");

  @FXML
  private ComboBox<Number>          cmbCellSizes;
  @FXML
  private ComboBox<String>          cmbCellStates;
  @FXML
  private ComboBox<Number>          cmbTimes;
  @FXML
  private Label                     gridSize;
  @FXML
  private Label                     fileName;

  private Pane                      board;
  private Canvas                    golLayer;
  private Canvas                    gridLayer;

  private Game                      game;

  private AnimationTimer            timer;
  private double                    time;


  public ControllerGOL(Pane board) {
    this.board     = board;
    this.golLayer  = new Canvas();
    this.gridLayer = new Canvas();
  }

  @FXML
  private void initialize() {
    cmbCellSizes.setItems(FXCollections.observableList(SIZES));
    cmbCellSizes.getSelectionModel().selectFirst();
    cmbCellStates.setItems(FXCollections.observableList(STATES));
    cmbCellStates.getSelectionModel().select(0);
    cmbTimes.setItems(FXCollections.observableList(TIMES));
    cmbTimes.getSelectionModel().selectFirst();

    golLayer.widthProperty().bind(board.widthProperty());
    golLayer.heightProperty().bind(board.heightProperty());

    gridLayer.widthProperty().bind(board.widthProperty());
    gridLayer.heightProperty().bind(board.heightProperty());

    board.getChildren().add(golLayer);
    board.getChildren().add(gridLayer);

    ViewGOL.setGcGolLayer(golLayer.getGraphicsContext2D());
    ViewGOL.setGcGrid(gridLayer.getGraphicsContext2D());
  }

  @FXML
  private void init() {
    initLogic();
    initView();
  }

  @FXML
  private void run() {
    timer.start();
  }

  @FXML
  private void updateGridSize() {
    var cellSize = cmbCellSizes.getValue().intValue();
    var mapSize  = (1280 * 720) / (cellSize * cellSize);

    gridSize.setText("" + mapSize);
  }

  @FXML
  private void updateTime() {
    time = cmbTimes.getValue().doubleValue() * 1000;
  }

  @FXML
  private void loadPattern() throws IOException {
    var          fileChooser  = new FileChooser();
    var          selectedFile = fileChooser.showOpenDialog(null);
    List<String> lines        = null;

    if (selectedFile != null) {
      init();
      fileName.setText(selectedFile.getName());
      lines = Files.readAllLines(Paths.get(selectedFile.getPath()));
      var cellSize = cmbCellSizes.getValue().intValue();
      var x        = (1280 / cellSize) / 2 - (lines.get(0).length() / 2);
      var y        = (720 / cellSize) / 2 - (lines.size() / 2);
      var i        = y;

      for (var line : lines) {
        for (var j = 0; j < line.length(); j++) {
          char c = line.charAt(j);
          if (c == 'x') {
            game.grid[i].set(x + j, true);
          }
        }
        i++;
      }
    }
    ViewGOL.drawCells(game.grid);
  }

  private void initLogic() {
    var cellSize = cmbCellSizes.getValue().intValue();
    var width    = 1280 / cellSize;
    var height   = 720 / cellSize;

    game = new Game(width, height);
    initTimer();
  }

  private void initTimer() {
    if (timer != null) {
      timer.stop();
    }
    updateTime();
    timer = new AnimationTimer() {
            Instant start = Instant.now();

            @Override
            public void handle(long arg) {
              if (Duration.between(start, Instant.now()).toMillis() >= time) {
                game.update();
                ViewGOL.drawCells(game.newGrid);
                game.resetNewGrid();
                start = Instant.now();
              }
            }
          };
  }

  private void initView() {
    var cellSize = cmbCellSizes.getValue().intValue();
    var state    = cmbCellStates.getValue();
    var width    = 1280 / cellSize;
    var height   = 720 / cellSize;

    ViewGOL.setDim(cellSize);
    ViewGOL.setWidth(width);
    ViewGOL.setHeight(height);
    ViewGOL.setCellColor(state);
    ViewGOL.reset();
    ViewGOL.drawCells(game.grid);
    ViewGOL.drawGrid();
    onMouseClicked(cellSize);
    onMouseDragged(cellSize);
  }

  private void onMouseClicked(int cellSize) {
    gridLayer.setOnMouseClicked(e -> {
      var state = cmbCellStates.getValue();
      var x     = (int) (e.getX() / cellSize);
      var y     = (int) (e.getY() / cellSize);

      ViewGOL.setCellColor(state);
      switch (state) {
        case "ALIVE"    -> game.grid[y + 1].set(x + 1, true);
        case "DEAD"     -> game.grid[y + 1].set(x + 1, false);
        default         -> throw new IllegalArgumentException();
      }
      ViewGOL.drawCell(x, y);
    });
  }

  private void onMouseDragged(int cellSize) {
    gridLayer.setOnMouseDragged(e -> {
      var state = cmbCellStates.getValue();
      var x     = (int) (e.getX() / cellSize);
      var y     = (int) (e.getY() / cellSize);

      ViewGOL.setCellColor(state);
      switch (state) {
        case "ALIVE"    -> game.grid[y + 1].set(x + 1, true);
        case "DEAD"     -> game.grid[y + 1].set(x + 1, false);
        default         -> throw new IllegalArgumentException();
      }
      ViewGOL.drawCell(x, y);
    });
  }

}
