package exercise_2;

public class SyncronisedPrinter {
    public static Object flag = new Object();

    public static void main(String[] args) {
        Runnable task = () -> {
            synchronized (flag) {
                while (true) {
                    flag.notify();
                    System.out.println( Thread.currentThread().getName() );
                    try {
                        Thread.currentThread().sleep( 300 );
                        flag.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        Thread thread1 = new Thread( task );
        Thread thread2 = new Thread( task );
        thread1.start();
        thread2.start();


    }


}

