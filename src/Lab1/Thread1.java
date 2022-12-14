package Lab1;

import java.util.Arrays;

public class Thread1 extends Thread {

    public void run() {
        try {
            System.out.println("T1 started");
            //Recording data: MM,B,MX
            Data.adjustMM(Write.writeMatrixByOne());
            Data.adjustMX(Write.writeMatrixByOne());
            Data.adjustB(Write.writeVectorByOne());

            Lab1.S1.release(3);
            Lab1.S3.acquire(1);
            Lab1.S4.acquire(1);
            System.out.println("Data of T1 successfully entered");


            int d1 = Data.getd();
            int[] Z1 = Data.getZ();
            int [][] MX1 = Data.getMX();
            int p1 = Data.getp();
            System.out.print(d1 + " d1");
            System.out.println(Arrays.toString(Z1) + " Z1");
            System.out.println(Arrays.deepToString(Data.getMM()) + " MM");
            System.out.println(p1 + " p1");

            int []Sh = Data.partOfSh(d1,Data.getB(),Z1,Data.getMM());
            System.out.println(Arrays.toString(Sh) + " Sorted Sh in T1");
            //Записати нові відсортовані частини до S
            Data.assignNewValueToS(Sh,0,Data.H);

            Lab1.S5.acquire();
            Data.assignSortedValueToS(Data.firstSortS2h(),0,Data.N/4);
            Lab1.S7.acquire();
            Data.sortS4H();
            Lab1.S8.release(3);

            //Обчислення q1
            int q = Data.q;
            q += Data.multiplySubVectorBySubVector(Data.getB(),Z1,0,Data.H);
            Data.setq(q);

            Lab1.S14.release(3);
            Lab1.S15.acquire(1);
            Lab1.S16.acquire(1);
            Lab1.S17.acquire(1);

            int[] Ah = Data.calculateAh(p1,Data.S,MX1,Data.getMT(),Data.q,Z1,0,Data.H);

            System.out.println(Arrays.toString(Ah) + " T1 Ah");






//            int e1;
//            synchronized (Lab1.CS1) {
////                e1 = Data.e;
//            }
//
//
//            Lab1.S11.acquire();
////            d1 = Data.d.get();
//            Lab1.S11.release();
//
//
//            int[] x1;
//            synchronized (Lab1.CS2) {
////                x1 = Data.X;
//            }
//
//
//            Lab1.S12.acquire();
//            int[] b1 = Data.B;
//            Lab1.S12.release();
//
//
//            int[][] MM1;
//            synchronized (Lab1.CS3) {
//                MM1 = Data.MM;
//            }


            //Calculation Rн = d*(B * MVн) + е*Х*(MM * MCн)
//            Data.setResultPartOfVectorR(d1, b1, Data.MV, e1, x1, MM1, Data.MC, 0, H);
//            Lab1.S9.release(1);
            System.out.println("T1 finished");


//        catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
