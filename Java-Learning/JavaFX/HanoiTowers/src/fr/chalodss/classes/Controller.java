package fr.chalodss.classes;

import static fr.chalodss.utils.Constants.*;

import java.util.Arrays;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

/**
 * @author Charles T.
 *
 */
public final class Controller {
  @FXML
  private Pane              board;
  @FXML
  private ComboBox<Integer> sizes;
  @FXML
  private ComboBox<Long>    delay;

  private IntegerProperty   nbDisks;

  private HanoiTask         task;


  public Controller() {

  }

  @FXML
  private void initialize() {
    var disks       = Arrays.asList(1, 2, 3, 8, 16, 32, 64, 128);
    var times       = Arrays.asList(1000L, 100L, 10L, 1L);

    var obListDisks = FXCollections.observableList(disks);
    var obListTimes = FXCollections.observableList(times);

    sizes.setItems(obListDisks);
    delay.setItems(obListTimes);

    nbDisks = new SimpleIntegerProperty();
    nbDisks.bind(sizes.valueProperty());
  }

  @FXML
  private void init() {
    double dw = DW;
    double dh = DH.get(nbDisks.get() / 16);
    double x  = SX;
    double y  = SY - DH.get(nbDisks.get() / 16);

    task = new HanoiTask(nbDisks.get(), delay.getValue());
    for (var i = 0; i < nbDisks.get(); i++) {
      task.towers.get(0).push(new Disk(x, y, dw, dh));
      x  += ((dw / nbDisks.doubleValue()) / 2);
      y  -= dh;
      dw -= (dw / nbDisks.doubleValue());
    }
    board.getChildren().addAll(task.towers.get(0));
  }

  @FXML
  private void solve() {
    var t = new Thread(task);

    t.setDaemon(true);
    t.start();
  }

  @FXML
  private void reset() {
    task.cancelled = true;
    task.cancel();
    board.getChildren().removeIf(Disk.class::isInstance);
  }

}
