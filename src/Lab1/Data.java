package Lab1;


import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Data {
    public static int N; //size of vectors and matrices
    public static final int PROCESSORS = 4;
    public static int H; //size of quarter

    //Create an atomic integer d
    public static int d;

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
    public static int[][] MT;

    public static int[] A;
    public static int[] B;

    public static int[] S;


    //Shared resources
    public static int p;
    public static int[][] MX;
    public static int[] Z;
    public static int q;


    //Getters for all vectors and matrices
    public static int[][] getMM() {
        return MM;
    }

    public static int[][] getMT() {
        return MT;
    }

    public static int[][] getMX() {
        return MX;
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

    public static int getq() {
        return q;
    }

    public static int getp() {
        return p;
    }

    public static int getd() {
        return d;
    }


    //Setting the vectors and matrices

    public static void adjustMM(int[][] MM) {
        Data.MM = MM;
    }

    public static void adjustA(int[] A) {
        Data.A = A;
    }

    public static void adjustMT(int[][] MT) {
        Data.MT = MT;
    }

    public static void adjustMX(int[][] MX) {
        Data.MX = MX;
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

    public static void adjustd(int d) {
        Data.d = d;
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


    //Set the part of the vector R

//    public static void setResultPartOfVectorR(int d, int[] B, int[][] MV, int e, int[] X, int[][] MM, int[][] MC, int start, int end) {
//        int[] finalVector = sumVector(multiplySubVectorByConstant(d, multiplyVectorBySubMatrix(B, MV, start, end), start, end), multiplyVectorBySubMatrix(multiplyScalarAndVector(e, X), multiplyMatrixAndSubMatrix(MM, MC, start, end), start, end), start, end);
//        Write.writeToResult(finalVector, start, end);
//    }

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

    public static int[] vectorOnConstantMultiply(int[] vector, int b) {
        int[] resultVector = new int[Data.H];
        int j = 0;

        for (int i = Data.N - Data.H; i < Data.N; i++) {
            resultVector[j] = vector[i] * b;
            j++;
        }
        return resultVector;
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

    public static int[] vectorSum(int[] A, int[] B) {
        int[] result = new int[A.length];
        int j = 0;

        for (int i = Data.getN() - Data.getH(); i < Data.getN(); i++) {
            result[j] = A[j] + B[j];
            j++;
        }

        return result;
    }

    private static int[] matrixOnVectorMultiply(int[][] MA, int[] A) {
        int[] result = new int[Data.H];
        int k = 0;

        for (int i = Data.N - Data.H; i < Data.N; i++) {
            for (int j = 0; j < Data.N; j++) {
                result[k] += MA[k][j] * A[j];
            }
            k++;
        }

        return result;
    }

    private static void vectorSort(int[] A) {
        Arrays.sort(A);
    }


    public static synchronized void assignNewValueToS(int[] S, int[] Sh, int from, int to) {
        int j = 0;

        for (int i = from; i < to; i++) {
            S[i] = Sh[j];
            j++;
        }
    }


    public static synchronized void assignSortedValueToS(int[] S, int[] S2h, int from, int to) {
        int j = 0;

        for (int i = from; i < to; i++) {
            S[i] = S2h[j];
            j++;
        }
    }

    public static int[] firstSortS2h() {
        int[] S2h = new int[Data.N / 2];
        System.arraycopy(Data.S, 0, S2h, 0, S2h.length);
        Arrays.sort(S2h);
        System.out.println("T1 sort G2h " + Arrays.toString(S2h));
        return S2h;
    }

    public static void sortS4H() {
        Arrays.sort(Data.S);
    }

    public static int[] partOfSh(int d, int[] B, int[] Z, int[][] MM) {
        int[] resultVector = vectorSum(vectorOnConstantMultiply(B, d),
                matrixOnVectorMultiply(MM, Z));
        vectorSort(resultVector);
        System.out.println(Arrays.toString(resultVector) + " T1 sorted Sh");

        return resultVector;
    }

    public static int[] secondSortS2h() {
        int[] S2h = new int[Data.N / 2];
        int j = 0;

        for (int i = Data.N / 2; i < Data.N; i++) {
            S2h[j] = Data.S[i];
            j++;
        }
        return S2h;
    }
}






