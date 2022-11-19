package fr.chalodss.classes.fractals.sierpinski;

import java.util.List;
import fr.chalodss.classes.fractals.sierpinski.SierpinskiTriangle.PointSet;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * @author Charles T.
 * 
 */
public final class ViewFST {

  private static GraphicsContext gc;
  private static Timeline        timeLine;

  private ViewFST() {

  }

  static void draw(List<PointSet> pointSets, double time) {
    var iterator = pointSets.iterator();

    timeLine = new Timeline(new KeyFrame(Duration.millis(time * 1000.0), e -> {
      var p = iterator.next();
      gc.strokePolygon(p.xPoints, p.yPoints, 3);
    }));
    timeLine.setCycleCount(pointSets.size());
    timeLine.play();
  }

  static void reset() {
    if (timeLine != null) {
      timeLine.stop();
    }
    gc.clearRect(0, 0, 1280, 720);
    gc.setFill(Color.web("#566573"));
    gc.fillRect(0, 0, 1280, 720);
    gc.setStroke(Color.web("#00E5FF"));
  }

  public static void setGc(GraphicsContext gc) {
    ViewFST.gc = gc;
  }

}
