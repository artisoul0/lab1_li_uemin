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
    public static int[][] MM;

    public static int[] R = new int[Data.getN()];
    public static int[] B;
    public static int[] X;
    public static int e;
    public static int[] Z;


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

    public static void setResultPartOfVectorR (int d, int []B, int [][]MV, int e, int []X, int [][]MM, int [][]MC, int start, int end ){
        int [] finalVector = addTwoVectors(multiplyScalarAndVector(d,multiplyVectorBySubMatrix(B,MV,start,end)),multiplyVectorMatrix(multiplyScalarAndVector(e,X),multiplyMatrixAndSubMatrix(MM,MC,start,end)));
        Write.writeToResult(finalVector,start,end);
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

    public static int [] multiplyScalarAndVector(int scalar, int []Vector){
        int []result = new int[Data.N];
        for (int i = 0; i < Data.N; i++) {
            result[i] = scalar*Vector[i];
        }
        return result;
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


}
