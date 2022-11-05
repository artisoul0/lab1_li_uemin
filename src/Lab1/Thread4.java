package Lab1;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;

public class Thread4 extends Thread{

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
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task 4 finished");
    }
}
