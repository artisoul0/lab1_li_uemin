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
            //Reaching Z4
            int []quarterVector = Arrays.copyOfRange(Z,H*3,H*4);
            System.out.println("Quart#3 of Z: " + Arrays.toString(quarterVector));
            //Calculation#1
            d4 = Data.getMaxInQuarterVector(quarterVector);
            System.out.println("d3 is :" + d4);
            //Calculation#2
            Data.d.set(Data.maxScalarD(Data.d,d4));
            System.out.println("Saved max d in Data " + Data.d);
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task 4 finished");
    }
}
