package Lab1;

import java.util.Scanner;

public class Write {
    private static final int size = Data.getN();

    public static int [][] writeMatrixByOne(){
        int [][] Matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Matrix[i][j] = 1;
            }
        }
        return Matrix;
    }

    public static int[] writeVectorByOne(){
        int [] Vector = new int[size];
        for (int i = 0; i < size; i++) {
         Vector[i] = 1;
        }
        return Vector;
    }

    public static int[][] writeMatrixByHand(){
        Scanner scanner = new Scanner(System.in);
        int [][] Matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Matrix[i][j] = scanner.nextInt();
            }
        }
        return Matrix;
    }

    public static int [] writeVectorByHand (){
        Scanner scanner = new Scanner(System.in);
        int [] Vector = new int[size];
        for (int i = 0; i < size; i++) {
            Vector[i] = scanner.nextInt();
        }
        return Vector;
    }

    public static int writeScalarByOne(){
        int scalar = 1;
        return scalar;
    }


}
