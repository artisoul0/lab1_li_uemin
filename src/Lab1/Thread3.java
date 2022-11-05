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
            //Reaching Z1
            int []quarterVector = Arrays.copyOfRange(Z,H*2,H*3);
            System.out.println("Quart#3 of Z: " + Arrays.toString(quarterVector));
            //Calculation#1
            d3 = Data.getMaxInQuarterVector(quarterVector);
            System.out.println("d3 is :" + d3);
            //Calculation#2
            Lab1.S11.acquire();
            Data.d.set(Data.maxScalarD(Data.d,d3));
            System.out.println("Saved max d in Data " + Data.d);
            Lab1.S11.release();

            // The signal to tasks about finishing calculation d.
            Lab1.S3.release(Data.getProcessors() - 1);

            // Wait for signals about finishing calculation d in tasks T1,T2,T4.
            Lab1.S1.acquire();
            Lab1.S2.acquire();
            Lab1.S4.acquire();

            //Copy e3 = e
            Lab1.CS1.lock();
            int e3 = Data.gete();
            Lab1.CS1.unlock();

            //Copy X3 = X
            Lab1.CS2.lock();
            int[] X3 = Data.getX();
            Lab1.CS2.unlock();

            //Copy MM3 = MM
            Lab1.CS3.lock();
            int [][] MM3 = Data.getMM();
            Lab1.CS3.unlock();

            //Copy B3 = B
            Lab1.S12.acquire();
            int [] B3 = Data.getB();
            Lab1.S12.release();

            //Calculation Rн = d*(B * MVн) + е*Х*(MM * MCн)
            Data.setResultPartOfVectorR(d3,B3,Data.getMV(),e3,Data.getX(),MM3,Data.getMC(),H*2,H*3);

            // The signal of calculation XH in T3.
            Lab1.S5.release();

            System.out.println("T3 finished");
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
