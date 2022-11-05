package Lab1;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;

public class Thread2 extends Thread{
int d2 = 0;

    public void run(){
        //input values: MM,R
        System.out.println("Task 2 started");
        Data.adjustMM(Write.writeMatrixByOne());
        System.out.println("MM" + Arrays.deepToString(Data.getMM()));


        try {
            Lab1.B1.await();
            int H = Data.getH();
            int[] Z = Data.getZ();
            //Reaching Z1
            int []quarterVector = Arrays.copyOfRange(Z,H,H*2);
            System.out.println("Quart#2 of Z: " + Arrays.toString(quarterVector));
            //Calculation#1
            d2 = Data.getMaxInQuarterVector(quarterVector);
            System.out.println("d2 is :" + d2);
            //Calculation#2
            Lab1.S11.acquire();
            Data.d.set(Data.maxScalarD(Data.d,d2));
            System.out.println("Saved max d in Data " + Data.d);
            Lab1.S11.release();

            // The signal to tasks about finishing calculation d.
            Lab1.S2.release(Data.getProcessors() - 1);

            // Wait for signals about finishing calculation d in tasks T1,T3,T4.
            Lab1.S1.acquire();
            Lab1.S3.acquire();
            Lab1.S4.acquire();

            //Copy e2 = e
            Lab1.CS1.lock();
            int e2 = Data.gete();
            Lab1.CS1.unlock();

            //Copy X2 = X
            Lab1.CS2.lock();
            int[] X2 = Data.getX();
            Lab1.CS2.unlock();

            //Copy MM2 = MM
            Lab1.CS3.lock();
            int [][] MM2 = Data.getMM();
            Lab1.CS3.unlock();

            //Copy B2 = B
            Lab1.S12.acquire();
            int [] B2 = Data.getB();
            Lab1.S12.release();

            //Calculation Rн = d*(B * MVн) + е*Х*(MM * MCн)
            Data.setResultPartOfVectorR(d2,B2,Data.getMV(),e2,Data.getX(),MM2,Data.getMC(),H,H*2);
            // Wait for a signal of calculation XH in tasks T1, T3, T4.
            Lab1.S5.acquire(Data.getProcessors() - 1);

            System.out.println("R: " + Arrays.toString(Data.getR()));
            System.out.println("T2 finished");

        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
