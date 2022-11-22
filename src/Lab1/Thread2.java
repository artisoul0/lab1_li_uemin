package Lab1;

import java.util.Arrays;

public class Thread2 extends Thread {

    public void run() {
        try {
            System.out.println("Task 2 started");
            //No input

//            Lab1.S2.release(3);
//            Lab1.S1.acquire(1);
//            Lab1.S4.acquire(1);

            int H = Data.getH();
            int[] Z = Data.getZ();
            int d2 = Data.getd();
            int[] Z2 = Data.getZ();
            int [][] MX2 = Data.getMX();
            int p2 = Data.getp();

            int []Sh = Data.partOfSh(d2,Data.getB(),Z2,Data.getMM());

            Data.assignNewValueToS(Data.S,Sh,Data.H,Data.N/2);



//            Lab1.S6.release(3);
//            Lab1.S5.acquire(1);
//            Lab1.S7.acquire(1);
//            Lab1.S8.acquire(1);

            int e2;
//            synchronized (Lab1.CS1) {
//                e2 = Data.e;
//            }
//
//            Lab1.S11.acquire();
//            d2 = Data.d.get();
//            Lab1.S11.release();
//
//            int[] x2;
//            synchronized (Lab1.CS2) {
//                x2 = Data.X;
//            }
//
//            Lab1.S12.acquire();
//            int[] b2 = Data.B;
//            Lab1.S12.release();
//
//
//            int[][] MM2;
//            synchronized (Lab1.CS3) {
//                MM2 = Data.MM;
//            }

//            Lab1.S9.acquire(3);
            //Output result
//            System.out.println("R = " + Arrays.toString(Data.getR()));
            System.out.println("T2 finished");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
