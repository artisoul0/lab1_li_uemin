package Lab1;

public class Data {
    public static int N; //size
    public static final int PROCESSORS = 4;
    public static int H; //size of quarter


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

    public static int[][] MV;
    public static int[][] MC;
    private static int[][] MM;
    private static int[] R;
    private static int[] B;
    private static int[] X;
    private static int e;
    private static int[] Z;

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

}
