package exercise_4;

import java.time.LocalTime;

public class DaemonTimer {
    public static void main(String[] args) {
        Thread thread = getDaemon( 1000 );
        thread.start();
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

       return new Thread(task);
    }


}
