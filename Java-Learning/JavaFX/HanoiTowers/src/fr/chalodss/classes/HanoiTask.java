package fr.chalodss.classes;

import static fr.chalodss.utils.Constants.*;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import javafx.application.Platform;
import javafx.concurrent.Task;

/**
 * @author Charles T.
 *
 */
public final class HanoiTask extends Task<Void> {

  volatile boolean        cancelled = false;

  final List<Deque<Disk>> towers;
  private final int       nbDisks;
  private final long      time;

  public HanoiTask(final int nbDisks, final long time) {
    this.towers  = Arrays.asList(new ArrayDeque<>(), new ArrayDeque<>(), new ArrayDeque<>());
    this.nbDisks = nbDisks;
    this.time    = time;
  }

  private void hanoi(int nbDisks, int start, int end, int tmp) {
    if (cancelled)
      return;
    if (nbDisks > 0) {
      hanoi(nbDisks - 1, start, tmp, end);
      moveDisk(start, end);
      hanoi(nbDisks - 1, tmp, end, start);
    }
  }

  private void moveDisk(int start, int end) {
    var disk = towers.get(start - 1).pop();

    updatePos(disk, disk.xPos[end - 1], SY - ((towers.get(end - 1).size() + 1) * disk.getHeight()));
    towers.get(end - 1).push(disk);
    pause();
  }

  private void updatePos(Disk disk, double x, double y) {
    Platform.runLater(() -> disk.move(x, y));
  }

  private void pause() {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  @Override
  protected Void call() throws Exception {
    hanoi(nbDisks, 1, 3, 2);
    return null;
  }

}
