package fr.chalodss.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Charles T.
 * 
 */
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    try {
      VBox  root  = FXMLLoader.load(getClass().getResource("/View.fxml"));
      Scene scene = new Scene(root);

      scene.getStylesheets().add(getClass().getResource("/view.css").toExternalForm());
      primaryStage.setTitle("Chronometer");
      primaryStage.setResizable(false);
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
