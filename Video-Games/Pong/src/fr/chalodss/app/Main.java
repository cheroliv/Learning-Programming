package fr.chalodss.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Charles T.
 *
 */
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    try {
      BorderPane root  = FXMLLoader.load(getClass().getResource("/View.fxml"));
      Scene      scene = new Scene(root);

      primaryStage.setResizable(false);
      primaryStage.setTitle("Pong");
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

}
