import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Implementing singleton
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();


        // Checking if both instances are the same
        System.out.println("We are literally twins bro. <True or False?> : " + (singleton1 == singleton2));

        singleton1.showMessage();
        singleton2.showMessage();

        // Thread Safety
        Runnable thread1 = () -> {
            Singleton singletonThread1 = Singleton.getInstance();
            System.out.println("Thread 1: " + singletonThread1.hashCode());
        };

        Runnable thread2 = () -> {
            Singleton singletonThread2 = Singleton.getInstance();
            System.out.println("Thread 2: " + singletonThread2.hashCode());
        };

        // Creating and starting 2 treads
        Thread t1 = new Thread(thread1);
        Thread t2 = new Thread(thread2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

