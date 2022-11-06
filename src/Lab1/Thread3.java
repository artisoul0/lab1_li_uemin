package Lab1;

import java.util.Arrays;

public class Thread3 extends Thread{
    private int d3 = 0;
    private int e3;
    private int[] X3;
    private int[][] MM3;

    private int[] B3;
    public void run(){
        try {
        System.out.println("Task 3 started");
            Lab1.S1.acquire(1);
            Lab1.S2.acquire(1);
            Lab1.S4.acquire(1);

            int H = Data.getH();
            int[] Z = Data.getZ();
            //Reaching Z1
            int []quarterVector = new int[H];
            System.arraycopy(Z,H*2,quarterVector,0,H);
            System.out.println("Quart#3 of Z: " + Arrays.toString(quarterVector));
            //Calculation#1
            d3 = Data.getMaxInQuarterVector(quarterVector);
            System.out.println("d3 is :" + d3);
            //Calculation#2
            Data.d.set(Data.maxScalarD(Data.d, d3));

            Lab1.S7.release(3);
            Lab1.S5.acquire(1);
            Lab1.S6.acquire(1);
            Lab1.S8.acquire(1);

            synchronized (Lab1.CS1){
                e3 = Data.e;
            }

            Lab1.S11.acquire();
            d3 = Data.d.get();
            Lab1.S11.release();

            synchronized (Lab1.CS2){
                X3 = Data.X;
            }

            Lab1.S12.acquire();
            B3 = Data.B;
            Lab1.S12.release();


            synchronized (Lab1.CS3) {
                MM3 = Data.MM;
            }


            //Calculation Rн = d*(B * MVн) + е*Х*(MM * MCн)

            Data.setResultPartOfVectorR(d3,B3,Data.getMV(),e3,X3,MM3,Data.getMC(),H*2,H*3);
            Lab1.S9.release(1);
            System.out.println("T3 finished");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
