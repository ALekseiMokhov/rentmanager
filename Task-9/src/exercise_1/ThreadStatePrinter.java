package exercise_1;

public class ThreadStatePrinter {

    static Thread t1 = new Thread( new ThreadGate() );
    static Thread t2 = new Thread( new ThreadGate() );
    static Thread t3 = new Thread( new ThreadWaiter() );
    static Thread t4 = new Thread( new ThreadBlocker() );
    static Thread t5 = new Thread();

    public static void main(String[] args) throws InterruptedException {


        /*NEW*/
        System.out.println( t1.getState() );
        t1.start();
        /*RUNNABLE*/
        System.out.println( t1.getState() );
        t2.start();

        Thread.sleep( 2000 );
        /*BLOCKED*/
        System.out.println( t2.getState() );

        t3.start();
        Thread.sleep( 1000 );
        /*TIMED_WAITING*/
        System.out.println( t3.getState() );

        /*WAITING*/
        t4.start();
        Thread.sleep( 1000 );
        System.out.println( t4.getState() );

        /*TERMINATED*/
        t5.start();
        Thread.sleep( 1000 );
        System.out.println( t5.getState() );
        System.exit( 0 );
    }
}

class ThreadGate implements Runnable {
    @Override
    public void run() {
        commonResource();
    }

    public static synchronized void commonResource() {
        while (true) {
            /*simulates blocking resource for thread t2*/
        }

    }
}

/*allows achieve time-waiting state*/
class ThreadWaiter implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep( 10000 );

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

/*allows achieve waiting state*/
class ThreadBlocker implements Runnable {
    @Override
    public void run() {
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
