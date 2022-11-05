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
        Data.adjustR(Write.writeVectorByOne());
        System.out.println("MM" + Arrays.deepToString(Data.getMM()));
        System.out.println("R" + Arrays.toString(Data.getR()));
//        System.out.println("Quarter of vector R:" + Arrays.toString(Data.quarterVector(Data.getR(), 0, Data.getH())));
//        System.out.println("Quarter of vector#2 R:" + Arrays.toString(Data.quarterVector(Data.getR(), Data.getH(), Data.getH()+Data.getH())));

        try {
            Lab1.B1.await();

            int H = Data.getH();
            int[] Z = Data.getZ();

            //Reaching Z2
            int []quarterVector = Arrays.copyOfRange(Z,H,H*2);
            System.out.println("Quart#2 of Z: " + Arrays.toString(quarterVector));
            //Calculation#1
            d2 = Data.getMaxInQuarterVector(quarterVector);
            System.out.println("d2 is :" + d2);
            //Calculation#2
            Data.d.set(Data.maxScalarD(Data.d,d2));
            System.out.println("Saved max d in Data " + Data.d);
            Lab1.S1.acquire();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
