package fr.chalodss.classes;

import static fr.chalodss.classes.EState.*;
import javafx.scene.layout.Pane;

public final class Renderer {

  public Renderer() {

  }

  private void addBeams(Pane board, Game game) {
    for (Entity e : game.beams) {
      if (!board.getChildren().contains(e)) {
        board.getChildren().add(e);
      }
    }
  }

  private void removeEntities(Pane board) {
    board.getChildren().removeIf(e -> {
      if (e.getClass() != Player.class) {
        var entity = (Entity) e;
        return entity.state == DEAD;
      }
      return false;
    });
  }

  void render(Pane board, Game game) {
    addBeams(board, game);
    removeEntities(board);
  }

}
