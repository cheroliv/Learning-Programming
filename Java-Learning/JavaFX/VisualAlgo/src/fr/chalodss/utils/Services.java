package fr.chalodss.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Charles T.
 * 
 */
public final class Services {

  private static ExecutorService svc;

  private Services() {

  }

  public static void shutdownAndAwaitTermination() {
    if (svc != null) {
      svc.shutdown();
      try {
        if (!svc.awaitTermination(10, TimeUnit.MILLISECONDS)) {
          svc.shutdownNow();
          if (!svc.awaitTermination(10, TimeUnit.MILLISECONDS))
            System.err.println("Svc did not terminate");
        }
      } catch (InterruptedException e) {
        svc.shutdownNow();
        Thread.currentThread().interrupt();
      }
    }
  }

  public static ExecutorService getSvc() {
    return svc;
  }

  public static void setSvc(ExecutorService svc) {
    Services.svc = svc;
  }

}
