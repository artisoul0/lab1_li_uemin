package Lab1;

import java.util.Arrays;

public class Thread1 extends Thread {

    public void run() {
        try {
            System.out.println("T1 started");
            //Recording data MV,MC
            Data.adjustMV(Write.writeMatrixByOne());
            Data.adjustMC(Write.writeMatrixByOne());
            Lab1.S1.release(3);
            Lab1.S2.acquire(1);
            Lab1.S4.acquire(1);
            int H = Data.getH();
            int[] Z = Data.getZ();
            //Reaching Z1
            int[] quarterVector = new int[H];
            System.arraycopy(Z, 0, quarterVector, 0, H);
            System.out.println("Quart#1 of Z: " + Arrays.toString(quarterVector));
            //Calculation#1
            //    MV,MC
            int d1 = Data.getMaxInQuarterVector(quarterVector);
            System.out.println("d1 is :" + d1);

            //Calculation#2
            Data.d.accumulateAndGet(d1, Math::max);

            Lab1.S5.release(3);
            Lab1.S6.acquire(1);
            Lab1.S7.acquire(1);
            Lab1.S8.acquire(1);


            int e1;
            synchronized (Lab1.CS1) {
                e1 = Data.e;
            }


            Lab1.S11.acquire();
            d1 = Data.d.get();
            Lab1.S11.release();


            int[] x1;
            synchronized (Lab1.CS2) {
                x1 = Data.X;
            }


            Lab1.S12.acquire();
            int[] b1 = Data.B;
            Lab1.S12.release();


            int[][] MM1;
            synchronized (Lab1.CS3) {
                MM1 = Data.MM;
            }


            //Calculation Rн = d*(B * MVн) + е*Х*(MM * MCн)
            Data.setResultPartOfVectorR(d1, b1, Data.MV, e1, x1, MM1, Data.MC, 0, H);
            Lab1.S9.release(1);
            System.out.println("T1 finished");


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
