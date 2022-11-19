package fr.chalodss.classes.fractals.sierpinski;

import fr.chalodss.utils.Services;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * @author Charles T.
 * 
 */
public final class ControllerFST {

  private static final List<Number> TIMES = Arrays.asList(1, 0.1, 0.01, 0.001, 0.0001);
  private static final List<Number> SIZES = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

  @FXML
  private ComboBox<Number>          cmbIterations;
  @FXML
  private ComboBox<Number>          cmbTimes;
  @FXML
  private Label                     initialized;
  @FXML
  private Label                     nbSteps;

  private Pane                      board;

  private SierpinskiTriangle        triangle;

  private Future<Integer>           result;


  public ControllerFST(Pane board) {
    this.board = board;
  }

  @FXML
  private void initialize() {
    cmbIterations.setItems(FXCollections.observableList(SIZES));
    cmbTimes.setItems(FXCollections.observableList(TIMES));

    cmbIterations.getSelectionModel().select(0);
    cmbTimes.getSelectionModel().select(0);

    var canvas = new Canvas();
    var gc     = canvas.getGraphicsContext2D();

    canvas.widthProperty().bind(board.widthProperty());
    canvas.heightProperty().bind(board.heightProperty());
    board.getChildren().add(canvas);

    ViewFST.setGc(gc);
    ViewFST.reset();
  }

  @FXML
  private void init() {
    initLogic();
    initServices();
    initView();
  }

  @FXML
  private void run() {
    var service = Services.getSvc();

    if (service != null && triangle.steps == 0) {
      result = service.submit(triangle);
      try {
        nbSteps.setText(result.get().toString());
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
      var time = cmbTimes.getValue().doubleValue();
      ViewFST.draw(triangle.points, time);
    }
    initialized.setText("- - - - - - - - - -");
    result = null;
  }

  private void initLogic() {
    var iterations = cmbIterations.getValue().intValue();
    var pA         = new Point2D(265, 650);
    var pB         = new Point2D(1015, 650);
    var pC         = new Point2D(640, 50);

    triangle = new SierpinskiTriangle(iterations, pA, pB, pC);
  }

  private void initServices() {
    Services.shutdownAndAwaitTermination();
    Services.setSvc(Executors.newSingleThreadExecutor());
  }

  private void initView() {
    nbSteps.setText("0");
    initialized.setText("Initialized");
    ViewFST.reset();
  }

}
