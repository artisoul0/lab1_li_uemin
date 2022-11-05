package Lab1;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;

public class Thread1 extends Thread{
    Semaphore semaphore;
//    MV,MC
    private static final int size = Data.getN();

    public void run(){
        System.out.println("T1 started");
            Data.adjustMV(Write.writeMatrixByOne());
            Data.adjustMC(Write.writeMatrixByOne());
            System.out.println("MV" + Arrays.deepToString(Data.getMV()));
            System.out.println("MC" + Arrays.deepToString(Data.getMC()));
        try {
            Lab1.B1.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
