package fr.chalodss.classes;

import java.util.concurrent.Executors;

/**
 * @author Charles T.
 * 
 */
public final class TestMonitor {

  private static class Monitor {
    private boolean available;

    private Monitor() {
      this.available = true;
    }

    public synchronized void get(boolean flag) {
      while (flag != available) {
        try {
          wait();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    }

    public synchronized void set(boolean flag) {
      available = flag;
      notifyAll();
    }
  }
  
  private static class TicTac implements Runnable {
    private Monitor monitor;
    private String  name;
    private boolean flag;
    private int     limit;
    private long    delay;

    private TicTac(Monitor monitor, String name, boolean flag, int limit, long delay) {
      this.monitor = monitor;
      this.name    = name;
      this.flag    = flag;
      this.limit   = limit;
      this.delay   = delay;
    }

    @Override
    public synchronized void run() {
      while (limit > 0) {
        monitor.get(flag);
        if (limit % 10 == 0 && name.equals("Tic"))
          System.out.println();
        else
          System.out.print(name + " ");
        limit--;
        try {
          wait(delay);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
        monitor.set(!flag);
      }
    }
  }
  
  private TestMonitor() {

  }

  public static void testMonitor() {
    var service = Executors.newFixedThreadPool(2);
    var monitor = new Monitor();
    var t1      = new TicTac(monitor, "Tic", true, 200, 10);
    var t2      = new TicTac(monitor, "Tac", false, 200, 1);

    service.execute(t1);
    service.execute(t2);
    
    service.shutdown();
  }

}
