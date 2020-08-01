package exercise_1;

public class ThreadStatePrinter {

    private static final Object FLAG = new Object();

    public static void main(String[] args) throws InterruptedException {
        ThreadStateSwitcher switcher = new ThreadStateSwitcher();
        Thread t = new Thread(switcher);
        printstate( t );
        t.start();
        printstate( t );
        Thread.currentThread().sleep( 1000 );
        printstate( t );
        synchronized (FLAG){
            FLAG.notify();
        }
        printstate( t );

        Thread.currentThread().sleep( 1000 );
        printstate( t );
        Thread.currentThread().sleep( 5000 );
        printstate( t );

    }

    public static void printstate (Thread thread){
        System.out.println(thread.getName() +": "+ thread.getState());
    }

    static class ThreadStateSwitcher implements Runnable {
        @Override
        public void run() {
            synchronized (FLAG) {
                try {
                    FLAG.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep( 5000 );

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
