package fr.chalodss.app;

import fr.chalodss.utils.Services;
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
      BorderPane root  = FXMLLoader.load(getClass().getResource("/view.fxml"));
      Scene      scene = new Scene(root);

      scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
      primaryStage.setTitle("VisualAlgo");
      primaryStage.setResizable(false);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void stop() {
    Services.shutdownAndAwaitTermination();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
