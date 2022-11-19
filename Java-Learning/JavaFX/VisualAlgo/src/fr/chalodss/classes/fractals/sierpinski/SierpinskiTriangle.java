package fr.chalodss.classes.fractals.sierpinski;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import javafx.geometry.Point2D;

public final class SierpinskiTriangle implements Callable<Integer> {

  private Point2D pA;
  private Point2D pB;
  private Point2D pC;
  private int     iterations;
  List<PointSet>  points;
  int             steps;

  static class PointSet {
    double[] xPoints;
    double[] yPoints;

    public PointSet(double[] xPoints, double[] yPoints) {
      this.xPoints = xPoints;
      this.yPoints = yPoints;
    }
  }

  public SierpinskiTriangle(int iterations, Point2D... pts) {
    this.points     = new ArrayList<>();
    this.iterations = iterations;
    this.pA         = pts[0];
    this.pB         = pts[1];
    this.pC         = pts[2];
  }

  private void addPoints(Point2D pA, Point2D pB, Point2D pC) {
    double[] xPoints = {pA.getX(), pB.getX(), pC.getX()};
    double[] yPoints = {pA.getY(), pB.getY(), pC.getY()};

    points.add(new PointSet(xPoints, yPoints));
  }

  private Point2D middlePoint(Point2D pA, Point2D pB) {
    return new Point2D((pA.getX() + pB.getX()) / 2, (pA.getY() + pB.getY()) / 2);
  }

  private void buildTriangle(Point2D pA, Point2D pB, Point2D pC, int iterations) {
    if (iterations > 0) {
      steps++;
      addPoints(pA, pB, pC);
      Point2D[] newPoints = {middlePoint(pA, pB), middlePoint(pA, pC), middlePoint(pB, pC)};
      buildTriangle(pA, newPoints[0], newPoints[1], iterations - 1);
      buildTriangle(newPoints[0], pB, newPoints[2], iterations - 1);
      buildTriangle(newPoints[1], newPoints[2], pC, iterations - 1);
    }
  }

  @Override
  public Integer call() throws Exception {
    buildTriangle(pA, pB, pC, iterations);
    return steps;
  }

}
