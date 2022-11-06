package Lab1;

import java.util.Arrays;

public class Thread1 extends Thread{
//    MV,MC
    private int d1 = 0;
    private int e1;
    private int[] X1;
    private int[][] MM1;

    private int[] B1;
    public void run(){
        try {
        System.out.println("T1 started");
        //Recording data
            Data.adjustMV(Write.writeMatrixByOne());
            Data.adjustMC(Write.writeMatrixByOne());
            System.out.println("MV" + Arrays.deepToString(Data.getMV()));
            System.out.println("MC" + Arrays.deepToString(Data.getMC()));

            Lab1.S1.release(3);
            Lab1.S2.acquire(1);
//            Lab1.S3.acquire(1); не потрібно, оскільки Т3 не вводить дані
            Lab1.S4.acquire(1);

            int H = Data.getH();
            int[] Z = Data.getZ();
            //Reaching Z1
            int []quarterVector = new int[H];
            System.arraycopy(Z,0,quarterVector,0,H);
            System.out.println("Quart#1 of Z: " + Arrays.toString(quarterVector));
            //Calculation#1
            d1 = Data.getMaxInQuarterVector(quarterVector);
            System.out.println("d1 is :" + d1);

            //Calculation#2
//            Data.d.set(Data.maxScalarD(Data.d, d1)); неправильно, бо виконується в два етапи
            Data.d.accumulateAndGet(d1, Math::max);

            Lab1.S5.release(3);
            Lab1.S6.acquire(1);
            Lab1.S7.acquire(1);
            Lab1.S8.acquire(1);

            synchronized (Lab1.CS1){
                e1 = Data.e;
            }

            Lab1.S11.acquire();
            d1 = Data.d.get();
            Lab1.S11.release();

            synchronized (Lab1.CS2){
                X1 = Data.X;
            }

            Lab1.S12.acquire();
            B1 = Data.B;
            Lab1.S12.release();


            synchronized (Lab1.CS3) {
                MM1 = Data.MM;
            }

            //Calculation Rн = d*(B * MVн) + е*Х*(MM * MCн)
//            Data.setResultPartOfVectorR(d1,B1,Data.getMV(),e1,X1,MM1,Data.getMC(),0,Data.H);
            Lab1.S9.release(1);
            System.out.println("T1 finished");


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
