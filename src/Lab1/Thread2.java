package Lab1;

import java.util.Arrays;

public class Thread2 extends Thread {

    public void run() {
        try {
            System.out.println("Task 2 started");
            //No input

            Lab1.S1.acquire(1);
            Lab1.S4.acquire(1);
            Lab1.S3.acquire(1);

            int H = Data.getH();
            int[] Z = Data.getZ();
            int d2 = Data.getd();
            int[] Z2 = Data.getZ();
            int [][] MX2 = Data.getMX();
            int p2 = Data.getp();
            int []Sh = Data.partOfSh(d2,Data.getB(),Z2,Data.getMM());
            System.out.println(Arrays.toString(Sh) + " Sorted Sh in T2");
            Data.assignNewValueToS(Sh,Data.H,Data.H*2);
            Lab1.S5.release();
            Lab1.S8.acquire();


            //Обчислення q2
            int q = Data.q;
            q += Data.multiplySubVectorBySubVector(Data.getB(),Z2,Data.H,Data.H*2);
            Data.setq(q);

            Lab1.S15.release(3);
            Lab1.S14.acquire(1);
            Lab1.S16.acquire(1);
            Lab1.S17.acquire(1);

            int[] Ah = Data.calculateAh(p2,Data.S,MX2,Data.getMT(),Data.q,Z2,Data.H,Data.H*2);

            System.out.println(Arrays.toString(Ah) + " T2 Ah");



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
