package exercise_4;

import java.time.LocalTime;

public class DaemonTimer {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = getDaemon( 1000 );
        thread.start();
        System.out.println("main thread starts working..");
        for (int i = 0; i < 10; i++) {
             Thread.currentThread().sleep( 1000 );
        }
        System.out.println("main thread has stopped");
    }


    public static Thread getDaemon(long interval){
        Runnable task = ()->{

           while (true){
               try {
                   System.out.println("Current time is: "+ LocalTime.now() );
                   Thread.currentThread().sleep( interval );
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        };

        Thread thread = new Thread(task);
        thread.setDaemon( true );
       return thread  ;
    }


}
