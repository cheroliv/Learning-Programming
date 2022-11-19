package fr.chalodss.classes.graphs.mazes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Charles T.
 * 
 */
public final class Cell {

  ECell      type;
  int        x;
  int        y;
  boolean    visited;
  List<Cell> adjCells;


  public Cell(ECell type, int x, int y) {
    this.type     = type;
    this.x        = x;
    this.y        = y;
    this.visited  = false;
    this.adjCells = new ArrayList<>();
  }

  @Override
  public String toString() {
    return " (" + x + ", " + y + ")" + " " + visited;
  }

}
