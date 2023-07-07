public class MagicSquare {
    public static void main(String[] args) {
        int n = 3;
        int[][] magicSquare = generateMagicSquare(n);
        printMagicSquare(magicSquare);
    }

    public static int[][] generateMagicSquare(int n) {
        int[][] magicSquare = new int[n][n];
        int num = 1;
        int row = 0;
        int col = n / 2;

        while (num <= n * n) {
            magicSquare[row][col] = num;
            num++;
            row--;
            col++;

            if (row < 0) {
                row = n - 1;
            }
            if (col == n) {
                col = 0;
            }
            if (magicSquare[row][col] != 0) {
                row++;
                col--;
                if (row == n) {
                    row = 0;
                }
                if (col < 0) {
                    col = n - 1;
                }
            }
        }

        return magicSquare;
    }

    public static void printMagicSquare(int[][] magicSquare) {
        int n = magicSquare.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(magicSquare[i][j] + " ");
            }
            System.out.println();
        }
    }
}
