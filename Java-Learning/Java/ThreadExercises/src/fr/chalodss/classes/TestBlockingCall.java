package fr.chalodss.classes;

import java.io.IOException;
import java.util.concurrent.Executors;

/**
 * @author Charles T.
 * 
 */
public final class TestBlockingCall {

  private static class TicTac implements Runnable {
    private int     cpt;
    private boolean stop;

    private TicTac() {
      this.cpt  = 0;
      this.stop = false;
    }

    @Override
    public void run() {
      while (!stop) {
        try {
          if (cpt % 10 == 0) {
            System.out.println();
          }
          System.out.printf("%-10d", cpt);
          cpt++;
          Thread.sleep(100);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    }
  }
  
  private TestBlockingCall() {

  }

  public static void testBlockingCall() {
    var service = Executors.newSingleThreadExecutor();
    var tictac  = new TicTac();

    System.out.println("Test Blocking Call.");
    service.execute(tictac);
    try {
      System.in.read();
      tictac.stop = true;
      service.shutdown();
      System.out.println("\nDone.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
