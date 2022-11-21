package Lab1;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

//Лабораторна робота №ЛР1
//Виконав студент групи ІО-06 Лі Юемін
// Мій варіант завдання: 16
// Завдання: A = p*sort(d*B+Z*MM) * (MX*MT) + (B*Z)*Z
public class Lab1 extends Thread {

    //Синхронизация T2,T3,T4 с введением данных в T1
    public static Semaphore S1 = new Semaphore(0, true);

    //Синхронизация T1,T3,T4 с введением данных в T2
    public static Semaphore S2 = new Semaphore(0, true);

    //Синхронизация T1,T2,T3 с введением данных в T4
    public static Semaphore S4 = new Semaphore(0, true);

    //Синхронизация T2,T3,T4 с вычислением d в T1

    public static Semaphore S5 = new Semaphore(0, true);

    //Синхронизация T1,T3,T4 с вычислением d в T2

    public static Semaphore S6 = new Semaphore(0, true);


    //Синхронизация T1,T2,T4 с вычислением d в T3
    public static Semaphore S7 = new Semaphore(0, true);

    //Синхронизация T1,T2,T3 с вычислением d в T4
    public static Semaphore S8 = new Semaphore(0, true);
    //Синхронизация T2 из завершением вычислений в потоках T2,T3,T4
    public static Semaphore S9 = new Semaphore(0, true);

    //For shared resource

    //B
    public static Semaphore S11 = new Semaphore(1, true);

    //d
    public static Semaphore S12 = new Semaphore(1, true);


    //Critical sections
    //For p
    public static final Object CS1 = new Object();
    //For d
    public static final Object CS2 = new Object();
    //For Z
    public static final Object CS3 = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.print("Lab1 started. Enter N:");
        Scanner scanner = new Scanner(System.in);
        Data.setN(scanner.nextInt());
        Data.setH();
        Thread1 T1 = new Thread1();
        Thread2 T2 = new Thread2();
        Thread3 T3 = new Thread3();
        Thread4 T4 = new Thread4();

        T1.start();
        T2.start();
        T3.start();
        T4.start();

        T1.join();
        T2.join();
        T3.join();
        T4.join();
        System.out.println("Lab1 finished.");
    }
}
