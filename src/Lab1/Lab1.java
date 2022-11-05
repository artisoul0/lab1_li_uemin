package Lab1;

import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static Lab1.Data.PROCESSORS;

public class Lab1 extends Thread{

    public static final Lock CS1 = new ReentrantLock();
    public static final Lock CS2 = new ReentrantLock();
    public static final Lock CS3 = new ReentrantLock();

    //    Initialize a barrier to control input from all processors
    public static final CyclicBarrier B1 = new CyclicBarrier(PROCESSORS, () -> System.out.println("All data saved. The next step in calculation"));

    //    Initialize a semaphore to synchronize T2,T3,T4 with ending calculation 'd' in T1
    public static final Semaphore S1 = new Semaphore(0);

    //    Initialize a semaphore to synchronize the access to the shared resource to d
    public static final Semaphore S11 = new Semaphore(0);

    //    Initialize a semaphore to synchronize the access to the shared resource to B
    public static final Semaphore S12 = new Semaphore(0);

    //    Initialize a semaphore to synchronize T1,T3,T4 with ending calculation 'd' in T2
    public static final Semaphore S2 = new Semaphore(0);

    //    Initialize a semaphore to synchronize T1,T2,T4 with ending calculation 'd' in T3
    public static final Semaphore S3 = new Semaphore(0);

    //    Initialize a semaphore to synchronize T1,T2,T3 with ending calculation 'd' in T4
    public static final Semaphore S4 = new Semaphore(0);

    //    Initialize a semaphore to synchronize T2 with ending RH of calculations in T1,T3,T4
    public static final Semaphore S5 = new Semaphore(1);

    public static void main (String[] args) throws InterruptedException {
        System.out.print("Lab1 started. Enter N:");
        Scanner scanner = new Scanner(System.in);
        Semaphore semaphore = new Semaphore(2);
        Data.setN(scanner.nextInt());
        int size = Data.getN();
        Data.setH();
        Thread1 T1 = new Thread1();
        Thread2 T2 = new Thread2();
        Thread3 T3 = new Thread3();
        Thread4 T4 = new Thread4();


        T1.start();
        T2.start();
        T3.start();
        T4.start();
//
        T1.join();
        T2.join();
        T3.join();
        T4.join();
        System.out.println("Lab1 finished.");
    }
}
