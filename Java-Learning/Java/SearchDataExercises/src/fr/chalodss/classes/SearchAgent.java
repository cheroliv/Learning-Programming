package fr.chalodss.classes;

import static fr.chalodss.utils.Constants.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Charles T.
 *
 */
public final class SearchAgent implements Runnable {

  private static AtomicBoolean find  = new AtomicBoolean(false);
  private static AtomicInteger count = new AtomicInteger(0);

  private List<Integer>        list;
  private int                  elem;
  private int                  start;
  private int                  end;

  public SearchAgent(List<Integer> list, int elem, int start, int end) {
    this.list  = list;
    this.elem  = elem;
    this.start = start;
    this.end   = end;
  }

  @Override
  public synchronized void run() {
    for (var i = start; !find.get() && i <= end; i++) {
      count.getAndIncrement();
      if (list.get(i) == elem) {
        find.set(true);
        System.out.println(NBI + count.get() + IND + i + VTF + elem);
        return;
      }
    }
  }

}
