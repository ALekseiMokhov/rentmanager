package exercise_2;

public class SyncronisedPrinter {
    public static Object flag = new Object();

    public static void main(String[] args) {
        Task task = new Task( flag ) ;

        Thread thread1 = new Thread( task );
        Thread thread2 = new Thread( task );
        thread1.start();
        thread2.start();


    }


}

