package Lab1;

import java.util.Arrays;


public class Thread4 extends Thread {

    public void run() {
        try {


            //Input values: B,X,Z,e
            System.out.println("Task 4 started");
            Data.adjustB(Write.writeVectorByOne());
            Data.adjustX(Write.writeVectorByOne());
            Data.adjustZ(Write.writeVectorByOne());
            Data.adjuste(Write.writeScalarByZero());

            Lab1.S4.release(3);
            Lab1.S1.acquire(1);
            Lab1.S2.acquire(1);

            int H = Data.getH();
            int[] Z = Data.getZ();
            //Reaching Z1
            int[] quarterVector = new int[H];
            System.arraycopy(Z, H * 3, quarterVector, 0, H);
            System.out.println("Quart#4 of Z: " + Arrays.toString(quarterVector));
            //Calculation#1
            int d4 = Data.getMaxInQuarterVector(quarterVector);
            System.out.println("d4 is :" + d4);

            //Calculation#2
            Data.d.accumulateAndGet(d4, Math::max);

            Lab1.S8.release(3);
            Lab1.S5.acquire(1);
            Lab1.S6.acquire(1);
            Lab1.S7.acquire(1);

            int e4;
            synchronized (Lab1.CS1) {
                e4 = Data.e;
            }

            Lab1.S11.acquire();
            d4 = Data.d.get();
            Lab1.S11.release();

            int[] x4;
            synchronized (Lab1.CS2) {
                x4 = Data.X;
            }

            Lab1.S12.acquire();
            int[] b4 = Data.B;
            Lab1.S12.release();


            int[][] MM4;
            synchronized (Lab1.CS3) {
                MM4 = Data.MM;
            }


            //Calculation Rн = d*(B * MVн) + е*Х*(MM * MCн)

            Data.setResultPartOfVectorR(d4, b4, Data.MV, e4, x4, MM4, Data.MC, H * 3, H * 4);
            Lab1.S9.release(1);

            System.out.println("T4 finished");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
