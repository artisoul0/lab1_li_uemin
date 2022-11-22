package Lab1;

import java.util.Arrays;


public class Thread4 extends Thread {

    public void run() {
        try {

            System.out.println("Task 4 started");
            //Recording data: A,Z,MT,d
            Data.adjustA(Write.writeVectorByOne());
            Data.adjustZ(Write.writeVectorByOne());
            Data.adjustMT(Write.writeMatrixByOne());
            Data.adjustd(Write.writeScalarByZero());
//            Lab1.S4.release(3);
//            Lab1.S1.acquire(1);
//            Lab1.S2.acquire(1);

            int H = Data.getH();
            int[] Z = Data.getZ();

            int d4 = Data.getd();
            int[] Z4 = Data.getZ();
            int [][] MX4 = Data.getMX();
            int p4 = Data.getp();

            int []Sh = Data.partOfSh(d4,Data.getB(),Z4,Data.getMM());

            Data.assignNewValueToS(Data.S, Sh, Data.N - Data.N/4, Data.N);


//            Lab1.S8.release(3);
//            Lab1.S5.acquire(1);
//            Lab1.S6.acquire(1);
//            Lab1.S7.acquire(1);

            int e4;
//            synchronized (Lab1.CS1) {
//                e4 = Data.e;
//            }

//            Lab1.S11.acquire();
////            d4 = Data.d.get();
//            Lab1.S11.release();

//            int[] x4;
//            synchronized (Lab1.CS2) {
//                x4 = Data.X;
//            }
//
//            Lab1.S12.acquire();
//            int[] b4 = Data.B;
//            Lab1.S12.release();
//
//
//            int[][] MM4;
//            synchronized (Lab1.CS3) {
//                MM4 = Data.MM;
//            }
//
//
//            //Calculation Rн = d*(B * MVн) + е*Х*(MM * MCн)
//
//            Data.setResultPartOfVectorR(d4, b4, Data.MV, e4, x4, MM4, Data.MC, H * 3, H * 4);
//            Lab1.S9.release(1);

            System.out.println("T4 finished");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
