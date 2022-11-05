package Lab1;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;

public class Thread1 extends Thread{
    Semaphore semaphore;
//    MV,MC
    private static final int size = Data.getN();
    private int d1 = 0;

    public void run(){
        System.out.println("T1 started");
            Data.adjustMV(Write.writeMatrixByOne());
            Data.adjustMC(Write.writeMatrixByOne());
            System.out.println("MV" + Arrays.deepToString(Data.getMV()));
            System.out.println("MC" + Arrays.deepToString(Data.getMC()));


        try {
            Lab1.B1.await();
            int H = Data.getH();
            int[] Z = Data.getZ();
            int []quarterVector = Arrays.copyOfRange(Z,0,H);
            System.out.println("Quart#1 of Z: " + Arrays.toString(quarterVector));
            //Calculation#1
            d1 = Data.getMaxInQuarterVector(quarterVector);
            System.out.println("d1 is :" + d1);
            //Calculation#2
            Lab1.S11.acquire();
            Data.d.set(Data.maxScalarD(Data.d,d1));
            System.out.println("Saved max d in Data " + Data.d);
            Lab1.S11.release();

            // The signal to tasks about finishing calculation d.
            Lab1.S1.release(Data.getProcessors() - 1);
            // Wait for signals about finishing calculation d in tasks T2,T3,T4.
            Lab1.S2.acquire();
            Lab1.S3.acquire();
            Lab1.S4.acquire();

            Lab1.CS1.lock();
            int scalarE = Data.gete();
            Lab1.CS1.unlock();

            Lab1.CS2.lock();
            int[] vectorX = Data.getX();
            Lab1.CS2.unlock();

            Lab1.CS3.lock();
            int [][] matrixMM = Data.getMM();
            Lab1.CS3.unlock();

            Lab1.S12.acquire();
            int [] vectorB = Data.getB();
            Lab1.S12.release();



        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
