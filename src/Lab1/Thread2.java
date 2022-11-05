package Lab1;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;

public class Thread2 extends Thread{


    public void run(){
        //input values: MM,R
        System.out.println("Task 2 started");
        Data.adjustMM(Write.writeMatrixByOne());
        Data.adjustR(Write.writeVectorByOne());
        System.out.println("MM" + Arrays.deepToString(Data.getMM()));
        System.out.println("R" + Arrays.toString(Data.getR()));
        try {
            Lab1.B1.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
