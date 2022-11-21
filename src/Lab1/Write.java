package Lab1;

public class Write {
    public static final int size = Data.getN();

    public static int[][] writeMatrixByOne() {
        int[][] Matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Matrix[i][j] = 1;
            }
        }
        return Matrix;
    }

    public static int[] writeVectorByOne() {
        int[] Vector = new int[size];
        for (int i = 0; i < size; i++) {
            Vector[i] = 1;
        }
        return Vector;
    }

    public static int writeScalarByZero() {
        return 0;
    }

    public static void writeToResult(int[] Vector, int start, int end) {
        if (end - start >= 0) {
//            System.arraycopy(Vector, start, Data.R, start, end - start);
        }
    }
}
