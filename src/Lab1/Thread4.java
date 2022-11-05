package Lab1;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;


public class Thread4 extends Thread{
int d4 = 0;
    public void run(){
        //Input values: B,X,Z,e
        System.out.println("Task 4 started");
        Data.adjustB(Write.writeVectorByOne());
        Data.adjustX(Write.writeVectorByOne());
        Data.adjustZ(Write.writeVectorByOne());
        Data.adjuste(Write.writeScalarByOne());


        System.out.println("B" + Arrays.toString(Data.getB()));
        System.out.println("X" + Arrays.toString(Data.getX()));
        System.out.println("Z" + Arrays.toString(Data.getZ()));
        System.out.println("e" + Data.gete());
        try {
            Lab1.B1.await();
            int H = Data.getH();
            int[] Z = Data.getZ();
            //Reaching Z1
            int []quarterVector = Arrays.copyOfRange(Z,H*3,H*4);
            System.out.println("Quart#4 of Z: " + Arrays.toString(quarterVector));
            //Calculation#1
            d4 = Data.getMaxInQuarterVector(quarterVector);
            System.out.println("d4 is :" + d4);
            //Calculation#2
            Lab1.S11.acquire();
            Data.d.set(Data.maxScalarD(Data.d,d4));
            System.out.println("Saved max d in Data " + Data.d);
            Lab1.S11.release();

            // The signal to tasks about finishing calculation d.
            Lab1.S4.release(Data.getProcessors() - 1);

            // Wait for signals about finishing calculation d in tasks T2,T3,T4.
            Lab1.S1.acquire();
            Lab1.S2.acquire();
            Lab1.S3.acquire();

            //Copy e4 = e
            Lab1.CS1.lock();
            int e4 = Data.gete();
            Lab1.CS1.unlock();

            //Copy X4 = X
            Lab1.CS2.lock();
            int[] X4 = Data.getX();
            Lab1.CS2.unlock();

            //Copy MM4 = MM
            Lab1.CS3.lock();
            int [][] MM4 = Data.getMM();
            Lab1.CS3.unlock();

            //Copy B4 = B
            Lab1.S12.acquire();
            int [] B4 = Data.getB();
            Lab1.S12.release();
            System.out.println(Arrays.toString(B4));
            //Calculation Rн = d*(B * MVн) + е*Х*(MM * MCн)
            Data.setResultPartOfVectorR(d4,B4,Data.getMV(),e4,Data.getX(),MM4,Data.getMC(),H*3,H*4);

            // The signal of calculation XH in T4.
            Lab1.S5.release();

            System.out.println("T4 finished");
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
