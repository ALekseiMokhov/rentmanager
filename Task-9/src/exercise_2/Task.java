package exercise_2;

public class Task implements Runnable{
    private Object sybchroniser;
    
    public Task(Object synchroniser) {
        this.sybchroniser = synchroniser;
    }

    @Override
    public void run() {
        synchronized (sybchroniser) {
            while (true) {
                sybchroniser.notify();
                System.out.println( Thread.currentThread().getName() );
                try {
                    Thread.currentThread().sleep( 300 );
                    sybchroniser.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
