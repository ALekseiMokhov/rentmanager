package exercise_3;

import java.util.ArrayDeque;
import java.util.Queue;

public class App {

    private static final Queue <Integer> BUFFER = new ArrayDeque <>();
    private static final Integer MAX_SIZE = 10;

    public static void main(String[] args) {
        Thread thread1 = new Thread( new Producer() );
        Thread thread2 = new Thread( new Consumer() );
        thread1.start();
        thread2.start();
    }


    static class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {
                while (BUFFER.size() == MAX_SIZE) {
                    try {
                        Thread.currentThread().sleep( 1000 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int x = getRandom();
                BUFFER.add( x );
                System.out.println( "Integer produced: " + x );

            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {

                while (BUFFER.size() == 0) {
                    try {
                        Thread.currentThread().sleep( 1000 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    Thread.currentThread().sleep( getRandomLong() );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println( "Integer consumed: " + BUFFER.poll() );

            }
        }
    }

    private static int getRandom() {
        return (int) (Math.random() * 101);
    }

    private static long getRandomLong() {
        return (long) (Math.random() * 1000);
    }
}
