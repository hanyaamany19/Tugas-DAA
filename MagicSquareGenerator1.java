import java.util.Arrays;

public class MagicSquareGenerator1 {
    /**
     * Generates a magic square of order n using the exhaustive search algorithm.
     *
     * @param n The order of the magic square
     * @return A 2D array representing the magic square
     */
    public static int[][] generateMagicSquare(int n) {
        int[][] magicSquare = new int[n][n];
        int maxNum = n * n;
        int[] numbers = new int[maxNum];
        for (int i = 0; i < maxNum; i++) {
            numbers[i] = i + 1;
        }
        boolean[] used = new boolean[maxNum];
        Arrays.fill(used, false);
        generateMagicSquareHelper(magicSquare, numbers, used, 0, 0, n);
        return magicSquare;
    }

    private static boolean generateMagicSquareHelper(int[][] magicSquare, int[] numbers, boolean[] used, int row, int col, int n) {
        if (row == n) {
            return isMagicSquare(magicSquare, n);
        }
        for (int i = 0; i < numbers.length; i++) {
            if (!used[i]) {
                magicSquare[row][col] = numbers[i];
                used[i] = true;
                int nextCol = (col + 1) % n;
                int nextRow = (nextCol == 0) ? row + 1 : row;
                if (generateMagicSquareHelper(magicSquare, numbers, used, nextRow, nextCol, n)) {
                    return true;
                }
                used[i] = false;
            }
        }
        return false;
    }

    private static boolean isMagicSquare(int[][] magicSquare, int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += magicSquare[0][i];
        }
        // Check rows
        for (int i = 1; i < n; i++) {
            int rowSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += magicSquare[i][j];
            }
            if (rowSum != sum) {
                return false;
            }
        }
        // Check columns
        for (int i = 0; i < n; i++) {
            int colSum = 0;
            for (int j = 0; j < n; j++) {
                colSum += magicSquare[j][i];
            }
            if (colSum != sum) {
                return false;
            }
        }
        // Check diagonals
        int diagSum1 = 0;
        int diagSum2 = 0;
        for (int i = 0; i < n; i++) {
            diagSum1 += magicSquare[i][i];
            diagSum2 += magicSquare[i][n - 1 - i];
        }
        return diagSum1 == sum && diagSum2 == sum;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] magicSquare = generateMagicSquare(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(magicSquare[i][j] + " ");
            }
            System.out.println();
        }
    }
}