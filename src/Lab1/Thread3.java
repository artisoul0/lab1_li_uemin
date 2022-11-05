package Lab1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;

public class Thread3 extends Thread{
    public void run(){
        System.out.println("Task 3 started");
        try {
            Lab1.B1.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
