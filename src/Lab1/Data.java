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

    public static int[] S = {0,0,0,0,0,0,0,0};


    //Shared resources
    public static int p;
    public static int[][] MX;
    public static int[] Z;
    public static int q = 0;


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

    public static void setS(int [] S){
        Data.S = S;
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

    public static void setq(int q) {
        Data.q = q;
    }



    //Method to multiply All Vector and SubMatrix



    public static int[] vectorOnConstantMultiply(int[] vector, int b) {
        int[] resultVector = new int[Data.N /4];
        int j = 0;

        for (int i = Data.N - Data.N/4; i < Data.N; i++) {
            resultVector[j] = vector[i] * b;
            j++;
        }
        return resultVector;
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

        for (int i = Data.N - Data.N/4; i < Data.N; i++) {
            result[j] = A[j] + B[j];
            j++;
        }

        return result;
    }



    private static void vectorSort(int[] A) {
        Arrays.sort(A);
    }


    public static void assignNewValueToS(int[] Sh, int from, int to) {
        int j = 0;

        for (int i = from; i < to; i++) {
            // записує частини в S
            Data.S[i] = Sh[j];
            j++;
        }
    }


    public static void assignSortedValueToS(int[] S2h, int from, int to) {
        int j = 0;

        for (int i = from; i < to; i++) {
            Data.S[i] = S2h[j];
            j++;
        }
    }

    public static int[] firstSortS2h() {
        int[] S2h = new int[Data.N / 2];
        System.arraycopy(Data.S, 0, S2h, 0, S2h.length);
        Arrays.sort(S2h);
        System.out.println("T1 sort S2h " + Arrays.toString(S2h));
        return S2h;
    }

    public static void sortS4H() {
        Arrays.sort(Data.S);
    }

    public static int[] partOfSh(int d, int[] B, int[] Z, int[][] MM) {
        int[] resultVector = vectorSum(vectorOnConstantMultiply(B,d), matrixOnVectorMultiply(MM,Z));
        vectorSort(resultVector);
        System.out.println(Arrays.toString(resultVector));

        return resultVector;
    }

    private static int[] matrixOnVectorMultiply(int[][] MA, int[] A) {
        int[] result = new int[Data.N/4];
        int k = 0;

        for (int i = Data.N - Data.N/4; i < Data.N; i++) {
            for (int j = 0; j < Data.N; j++) {
                result[k] += MA[k][j] * A[j];
            }
            k++;
        }

        return result;
    }


//    public static int[] multiplyVectorBySubMatrix(int[] B, int[][] MV, int
//            start, int end) {
//        int[] K = new int[N];
//        for (int i = 0; i < end - start; i++) {
//            for (int j = 0; j < N; j++) {
//                for (int k = 0; k < MV[j].length; k++) {
//                    K[k] += B[j] * MV[j][k];
//                }
//            }
//        }
//        return K;
//    }

    public static int[] secondSortS2h() {
        int[] S2h = new int[Data.N / 2];
        int j = 0;

        for (int i = Data.N / 2; i < Data.N; i++) {
            S2h[j] = Data.S[i];
            j++;
        }
        return S2h;
    }



    public static int multiplySubVectorBySubVector(int [] B, int [] Z, int start, int end){
        int resultScalar = 0;
        for (int i = start; i < end; i++) {
            resultScalar += B[i] * Z[i];
        }
        return resultScalar;

    }

}







