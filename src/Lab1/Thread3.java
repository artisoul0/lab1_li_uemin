package Lab1;

import java.util.Arrays;

public class Thread3 extends Thread {

    public void run() {
        try {
            System.out.println("Task 3 started");
            //Recording data: p
            Data.adjustp(Write.writeScalarByZero());
//            Lab1.S1.acquire(1);
//            Lab1.S2.acquire(1);
//            Lab1.S4.acquire(1);

            int H = Data.getH();
            int[] Z = Data.getZ();

//            Lab1.S7.release(3);
//            Lab1.S5.acquire(1);
//            Lab1.S6.acquire(1);
//            Lab1.S8.acquire(1);

//            int e3;
//            synchronized (Lab1.CS1) {
//                e3 = Data.e;
//            }
//
//            Lab1.S11.acquire();
//            d3 = Data.d.get();
//            Lab1.S11.release();
//
//            int[] x3;
//            synchronized (Lab1.CS2) {
//                x3 = Data.X;
//            }
//
//            Lab1.S12.acquire();
//            int[] b3 = Data.B;
//            Lab1.S12.release();
//
//
//            int[][] MM3;
//            synchronized (Lab1.CS3) {
//                MM3 = Data.MM;
//            }


            //Calculation Rн = d*(B * MVн) + е*Х*(MM * MCн)

//            Data.setResultPartOfVectorR(d3, b3, Data.MV, e3, x3, MM3, Data.MC, H * 2, H * 3);
//            Lab1.S9.release(1);
            System.out.println("T3 finished");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
