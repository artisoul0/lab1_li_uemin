package Lab1;

import java.util.Arrays;

public class Thread2 extends Thread {

    public void run() {
        try {
            //input values: MM, out: R
            System.out.println("Task 2 started");
            Data.adjustMM(Write.writeMatrixByOne());
            Data.adjustR(Write.writeVectorByOne());

            Lab1.S2.release(3);
            Lab1.S1.acquire(1);
            Lab1.S4.acquire(1);

            int H = Data.getH();
            int[] Z = Data.getZ();
            //Reaching Z1
            int[] quarterVector = new int[H];
            System.arraycopy(Z, H, quarterVector, 0, H);
            System.out.println("Quart#2 of Z: " + Arrays.toString(quarterVector));
            //Calculation#1
            int d2 = Data.getMaxInQuarterVector(quarterVector);
            System.out.println("d2 is :" + d2);

            //Calculation#2
            Data.d.accumulateAndGet(d2, Math::max);

            Lab1.S6.release(3);
            Lab1.S5.acquire(1);
            Lab1.S7.acquire(1);
            Lab1.S8.acquire(1);

            int e2;
            synchronized (Lab1.CS1) {
                e2 = Data.e;
            }

            Lab1.S11.acquire();
            d2 = Data.d.get();
            Lab1.S11.release();

            int[] x2;
            synchronized (Lab1.CS2) {
                x2 = Data.X;
            }

            Lab1.S12.acquire();
            int[] b2 = Data.B;
            Lab1.S12.release();


            int[][] MM2;
            synchronized (Lab1.CS3) {
                MM2 = Data.MM;
            }


            //Calculation Rн = d*(B * MVн) + е*Х*(MM * MCн)
            Data.setResultPartOfVectorR(d2, b2, Data.MV, e2, x2, MM2, Data.MC, H, H * 2);
            Lab1.S9.acquire(3);
            //Output result
            System.out.println("R = " + Arrays.toString(Data.getR()));
            System.out.println("T2 finished");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
