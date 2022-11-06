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
    public static int[][] MV;
    public static int[][] MC;
    public static int[][] MM;

    public static int[] R;
    public static int[] B;
    public static int[] X;
    public static int e;
    public static int[] Z;


    //Getters for all vectors and matrices
    public static int[][] getMV() {
        return MV;
    }

    public static int[][] getMC() {
        return MC;
    }

    public static int[][] getMM() {
        return MM;
    }

    public static int[] getR() {
        return R;
    }

    public static int[] getB() {
        return B;
    }

    public static int[] getX() {
        return X;
    }

    public static int gete() {
        return e;
    }

    public static int[] getZ() {
        return Z;
    }

    //Setting the vectors and matrices

    public static void adjustMV(int[][] MV) {
        Data.MV = MV;
    }

    public static void adjustR(int[] R) {
        Data.R = R;
    }

    public static void adjustMC(int[][] MC) {
        Data.MC = MC;
    }

    public static void adjustMM(int[][] MM) {
        Data.MM = MM;
    }

    public static void adjustB(int[] B) {
        Data.B = B;
    }

    public static void adjustX(int[] X) {
        Data.X = X;
    }

    public static void adjuste(int e) {
        Data.e = e;
    }

    public static void adjustZ(int[] Z) {
        Data.Z = Z;
    }


    //The method to get a max element in vector Z
    public static int getMaxInQuarterVector(int[] Vector) {
        int scalarMaxValue = 0;
        for (int j : Vector) {
            if (scalarMaxValue < j) {
                scalarMaxValue = j;
            }
        }
        return scalarMaxValue;
    }

    //The method to compare in calculation: d = max(d,di)
    public static boolean firstPair = true;

    public static int maxScalarD(AtomicInteger d, int di) {
        if (firstPair) {
            firstPair = false;
            return di;
        }
        return Math.max(d.get(), di);
    }
    //Calculation Rн = d*(B * MVн) + е*Х*(MM * MCн)

    //Set the part of the vector R

    public static void setResultPartOfVectorR(int d, int[] B, int[][] MV, int e, int[] X, int[][] MM, int[][] MC, int start, int end) {
        int[] finalVector = sumVector(multiplySubVectorByConstant(d, multiplyVectorBySubMatrix(B, MV, start, end), start, end), multiplyVectorBySubMatrix(multiplyScalarAndVector(e, X), multiplyMatrixAndSubMatrix(MM, MC, start, end), start, end), start, end);
        Write.writeToResult(finalVector, start, end);
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

    private static int[] multiplySubVectorByConstant(int a, int[] C, int
            start, int end) {
        for (int i = start; i < end; i++) {
            C[i] *= a;
        }
        return C;
    }

    public static int[] multiplyScalarAndVector(int scalar, int[] Vector) {
        int[] result = new int[Data.N];
        for (int i = 0; i < Data.N; i++) {
            result[i] = scalar * Vector[i];
        }
        return result;
    }

    private static int[] sumVector(int[] X, int[] Y, int start, int end) {
        for (int i = start; i < end; i++) {
            X[i] = X[i] + Y[i];
        }
        return X;
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
