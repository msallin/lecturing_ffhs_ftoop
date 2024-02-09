package ffhs.lecturing.jpl.concurrency;

import static java.lang.System.out;

class Example {

  public static void main(String[] args) throws Exception {
    final Object resource1 = new Object();
    final Object resource2 = new Object();
    Thread t1 = new Thread() {

      public void run() {
        synchronized (resource1) {
          out.println("1");

          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }

          synchronized (resource2) {
            out.println("2");
          }
        }
      }
    };

    Thread t2 = new Thread() {
      public void run() {
        synchronized (resource2) {
          out.println("3");

          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }

          synchronized (resource1) {
            out.println("4");
          }
        }
      }
    };

    t1.start();
    t2.start();
  }
}
