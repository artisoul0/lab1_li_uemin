package Lab1;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Data {
    public static int N; //size of vectors and matrices
    public static final int PROCESSORS = 4;
    public static int H; //size of quarter

    //Create an atomic integer d
    public static AtomicInteger d = new AtomicInteger();

    //Set quarter size
    public static void setH() {
        H = N / PROCESSORS;
    }

    public static int getH() {
        return H;
    }

    public static void setN(int n) {
        Data.N = n;
    }

    public static int getN() {
        return N;
    }

    //Create vectors and matrices
    public static int[][] MM;
    public static int[][] MX;
    public static int[][] MT;

    public static int[] A;
    public static int[] Z;
    public static int[] B;
    public static int p;


    //Getters for all vectors and matrices
    public static int[][] getMM() {
        return MM;
    }

    public static int[][] getMX() {
        return MX;
    }

    public static int[][] getMT() {
        return MT;
    }

    public static int[] getA() {
        return A;
    }

    public static int[] getB() {
        return B;
    }

    public static int[] getZ() {
        return Z;
    }


    public static int getp() {
        return p;
    }

    //Setting the vectors and matrices

    public static void adjustMM(int[][] MM) {
        Data.MM = MM;
    }

    public static void adjustA(int[] A) {
        Data.A = A;
    }

    public static void adjustMX(int[][] MX) {
        Data.MX = MX;
    }

    public static void adjustMT(int[][] MT) {
        Data.MT = MT;
    }

    public static void adjustB(int[] B) {
        Data.B = B;
    }

    public static void adjustZ(int[] Z) {
        Data.Z = Z;
    }

    public static void adjustp(int p) {
        Data.p = p;
    }


    //Method to multiply All Vector and SubMatrix

    public static int[] multiplyVectorBySubMatrix(int[] B, int[][] MV, int
            start, int end) {
        int[] K = new int[N];
        for (int i = 0; i < end - start; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < MV[j].length; k++) {
                    K[k] += B[j] * MV[j][k];
                }
            }
        }
        return K;
    }

    //Method to multiply constant by SubVector
    private static int[] multiplySubVectorByConstant(int a, int[] C, int
            start, int end) {
        for (int i = start; i < end; i++) {
            C[i] *= a;
        }
        return C;
    }


    //Method to multiply all Matrix and SubMatrix
    public static int[][] multiplyMatrixAndSubMatrix(int[][] MM, int[][] MC,
                                                     int start, int end) {
        int[][] MT = new int[N][end - start];
        for (int i = 0; i < N; i++) {
            int g = 0;
            for (int j = start; j < end; j++) {
                MT[i][g] = 0;
                for (int k = 0; k < N; k++) {
                    MT[i][g] += MM[i][k] * MC[k][j];

                }
                g++;
            }
        }
        return MT;
    }
}
