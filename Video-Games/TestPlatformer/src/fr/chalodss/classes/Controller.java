package fr.chalodss.classes;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public final class Controller {

  @FXML
  private Pane pane;
  @FXML
  private Text score;
  @FXML
  private Text level;
  @FXML
  private Text lives;


  public Controller() {

  }

  @FXML
  private void initialize() {

  }

  @FXML
  private void init() {
    TileMaps.initMap();

    var player = new Player(10, 478);

    pane.setOnKeyPressed(this::handleKeyPressed);
    pane.setOnKeyReleased(this::handleKeyReleased);

    pane.getChildren().addAll(TileMaps.tileMap);
    pane.getChildren().add(player);
  }

  @FXML
  private void pause() {

  }

  @FXML
  private void reset() {

  }

  @FXML
  private void start() {

  }

  private void handleKeyPressed(KeyEvent e) {

  }

  private void handleKeyReleased(KeyEvent e) {

  }

}
