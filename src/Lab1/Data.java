package Lab1;

import java.util.concurrent.atomic.AtomicInteger;

public class Data {
    public static int N; //size of vectors and matrices
    public static final int PROCESSORS = 4;
    public static int H; //size of quarter

    //Create an atomic
    public static AtomicInteger d = new AtomicInteger();

public static int getProcessors(){
    return PROCESSORS;
}

    //Set quarter size
    public static void setH(){
        H = N/PROCESSORS;
    }

    public static int getH(){
        return H;
    }

    public static void setN(int n){
        Data.N = n;
    }

    public static int getN(){
        return N;
    }

    //Create vectors and matrices
    public static int[][] MV;
    public static int[][] MC;
    private static int[][] MM;

    //Create a result vector with size N
    private static int[] R = new int[Data.N];
    private static int[] B;
    private static int[] X;
    private static int e;
    private static int[] Z;


    //Getters for all vectors and matrices
    public static int[][] getMV(){
        return MV;
    }

    public static int[][] getMC(){
        return MC;
    }

    public static int[][] getMM(){
        return MM;
    }

    public static int[] getR(){
        return R;
    }

    public static int[] getB(){
        return B;
    }

    public static int[] getX(){
        return X;
    }

    public static int gete(){
        return e;
    }

    public static int[] getZ(){
        return Z;
    }

    //Setting the vectors and matrices

    public static void adjustMV(int [][] MV){
        Data.MV = MV;
    }

    public static void adjustMC(int [][] MC){
        Data.MC = MC;
    }

    public static void adjustMM(int [][] MM){
        Data.MM = MM;
    }

    public static void adjustR(int [] R){
        Data.R = R;
    }

    public static void adjustB(int [] B){
        Data.B = B;
    }

    public static void adjustX(int [] X){
        Data.X = X;
    }

    public static void adjuste(int e){
        Data.e = e;
    }

    public static void adjustZ(int [] Z){
        Data.Z = Z;
    }




    public static int getMaxInQuarterVector(int []Vector){
        int scalarMaxValue = 0;
        for (int j : Vector) {
            if (scalarMaxValue < j) {
                scalarMaxValue = j;
            }
        }
        return scalarMaxValue;
    }

    //The method to compare in calculation: d = max(d,di)
    public static boolean firstSet = true;
    public static int maxScalarD(AtomicInteger d, int di) {
        if (firstSet) {
            firstSet = false;
            return di;
        }
        return Math.max(d.get(), di);
    }


    //Calculation Rн = d*(B * MVн) + е*Х*(MM * MCн)

//    public static int [] setQuarterVectorR (int[] vectorR, int begin, int end, int d, int[] B, int [][] MVH, int e, int [] X, int[][] MM, int[][] MCH){
////        int[] resultVectorR = Arrays.copyOfRange(Data.getR(),begin,end);
//        int [] resultVectorR = Data.getR();
//        for (int i = 0; i < Data.getN(); i++) {
//            for (int j = begin; j < end; j++) {
////                resultVectorR[i] = d * (multiplyVectorMatrix(B,MVH)
//            }
//        }
//
//    }

    public static int[] multiplyVectorMatrix(int[] Vector, int[][] Matrix) {
        int N = Data.N;
        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result[i] += Vector[j] * Matrix[j][i];
            }
        }
        return result;
    }
    public static int[] addTwoVectors(int[] oneVector, int[] twoVector) {
        int N = Data.N;
        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            result[i] = oneVector[i] + twoVector[i];
        }
        return result;
    }
    public static int[] multiplyVectors(int[] oneVector, int[] twoVector) {
        int N = Data.N;
        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            result[i] = oneVector[i] * twoVector[i];
        }
        return result;
    }
    public static int[][] multiplyMatrix(int[][] oneMatrix, int[][]
            twoMatrix) {
        int N = Data.N;
        int[][] result = new int[N][N];
        for (int i = 0; i <N; i++) {
            for (int j = 0; j <N; j++) {
                for (int k = 0; k <N; k++) {
                    result[i][j] += oneMatrix[i][k] * twoMatrix[k][j];
                }
            }
        }
        return result;
    }




}
