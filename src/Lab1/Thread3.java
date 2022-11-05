package Lab1;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;

public class Thread3 extends Thread{
    int d3 = 0;
    public void run(){
        System.out.println("Task 3 started");
        try {
            Lab1.B1.await();
            int H = Data.getH();
            int[] Z = Data.getZ();
            int []quarterVector = Arrays.copyOfRange(Z,H*2,H*3);
            System.out.println("Quart#3 of Z: " + Arrays.toString(quarterVector));
            //Calculation#1
             d3 = Data.getMaxInQuarterVector(quarterVector);
            System.out.println("d3 is :" + d3);
            //Calculation#2
            Data.d.set(Data.maxScalarD(Data.d,d3));
            System.out.println("Saved max d in Data " + Data.d);
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
