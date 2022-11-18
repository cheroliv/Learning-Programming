package fr.chalodss.classes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author Charles T.
 * 
 */
public final class Controller {

  @FXML
  private TextField expr;
  @FXML
  private TextField res;

  public Controller() {

  }

  @FXML
  private void initialize() {
    res.textProperty().bind(Calculator.res);
  }

  @FXML
  private void buildExpression(ActionEvent event) {
    expr.setText(expr.getText() + ((Button) event.getSource()).getText());
  }

  @FXML
  private void evaluateExpression() {
    Calculator.evaluate(expr.getText());
  }

}
