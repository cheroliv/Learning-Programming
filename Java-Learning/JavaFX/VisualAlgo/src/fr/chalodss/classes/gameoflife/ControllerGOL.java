package fr.chalodss.classes.gameoflife;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * @author Charles T.
 * 
 */
public final class ControllerGOL {

  private static final List<Number> SIZES  = Arrays.asList(80, 40, 20, 16, 10, 8, 5, 4, 2);
  private static final List<String> STATES = Arrays.asList("ALIVE", "DEAD");
  private static final List<Number> TIMES  = Arrays.asList(1, 0.1, 0.01, 0.001, 0.0001);

  @FXML
  private ComboBox<Number>          cmbCellSizes;
  @FXML
  private ComboBox<String>          cmbCellStates;
  @FXML
  private ComboBox<Number>          cmbTimes;
  @FXML
  private TextField                 density;
  @FXML
  private Label                     gridSize;

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
    cmbCellStates.getSelectionModel().select(1);
    cmbTimes.setItems(FXCollections.observableList(TIMES));
    cmbTimes.getSelectionModel().selectFirst();

    golLayer.widthProperty().bind(board.widthProperty());
    golLayer.heightProperty().bind(board.heightProperty());

    gridLayer.widthProperty().bind(board.widthProperty());
    gridLayer.heightProperty().bind(board.heightProperty());

    board.getChildren().add(golLayer);
    board.getChildren().add(gridLayer);

    var gcGolLayer = golLayer.getGraphicsContext2D();
    var gcGrid     = gridLayer.getGraphicsContext2D();

    ViewGOL.setGcGolLayer(gcGolLayer);
    ViewGOL.setGcGrid(gcGrid);
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

  private void onCanvasPressed(int dim) {
    gridLayer.setOnMouseClicked(e -> {
      var state = cmbCellStates.getValue();
      var x     = (int) (e.getX() / dim);
      var y     = (int) (e.getY() / dim);

      ViewGOL.setCellColor(state);
      switch (state) {
        case "ALIVE" -> game.grid[y + 1].set(x + 1, true);
        case "DEAD"  -> game.grid[y + 1].set(x + 1, false);
        default      -> throw new IllegalArgumentException();
      }
      ViewGOL.drawCell(x, y);
    });
  }

  private void onMouseDragged(int dim) {
    gridLayer.setOnMouseDragged(e -> {
      var state = cmbCellStates.getValue();
      var x     = (int) (e.getX() / dim);
      var y     = (int) (e.getY() / dim);

      ViewGOL.setCellColor(state);
      switch (state) {
        case "ALIVE" -> game.grid[y + 1].set(x + 1, true);
        case "DEAD"  -> game.grid[y + 1].set(x + 1, false);
        default      -> throw new IllegalArgumentException();
      }
      ViewGOL.drawCell(x, y);
    });
  }

  private void initLogic() {
    var dim    = cmbCellSizes.getValue().intValue();
    var width  = 1280 / dim;
    var height = 720 / dim;

    game = new Game(width, height);
    initTimer();
  }

  private void initTimer() {
    if (timer != null) {
      timer.stop();
    }
    time  = cmbTimes.getValue().doubleValue() * 1000;
    timer = new AnimationTimer() {
            Instant start = Instant.now();

            @Override
            public void handle(long arg) {
              if (Duration.between(start, Instant.now()).toMillis() >= time) {
                game.update();
                ViewGOL.drawCells(game.newGrid, game.prevGrid);
                game.resetNewGrid();
                start = Instant.now();
              }
            }
          };
  }

  private void initView() {
    var dim    = cmbCellSizes.getValue().intValue();
    var state  = cmbCellStates.getValue();
    var width  = 1280 / dim;
    var height = 720 / dim;

    ViewGOL.setDim(dim);
    ViewGOL.setWidth(width);
    ViewGOL.setHeight(height);
    ViewGOL.setCellColor(state);
    ViewGOL.reset();
    ViewGOL.drawCells(game.grid);
    ViewGOL.drawGrid();
    onCanvasPressed(dim);
    onMouseDragged(dim);
  }

}
