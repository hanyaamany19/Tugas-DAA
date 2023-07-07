import java.util.Arrays;

public class AssignmentProblem {
    
    /**
     * This function solves the assignment problem using the Hungarian algorithm.
     * It finds the assignment that minimizes the total cost.
     * 
     * @param costMatrix The cost matrix representing the assignment problem
     * @return An array of assignments, where assignments[i] represents the job assigned to person i
     */
    public static int[] solveAssignmentProblem(int[][] costMatrix) {
        int n = costMatrix.length;
        int m = costMatrix[0].length;
        
        // Step 1: Subtract the minimum value of each row from all elements in that row
        int[] rowMin = new int[n];
        Arrays.fill(rowMin, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rowMin[i] = Math.min(rowMin[i], costMatrix[i][j]);
            }
            for (int j = 0; j < m; j++) {
                costMatrix[i][j] -= rowMin[i];
            }
        }
        
        // Step 2: Subtract the minimum value of each column from all elements in that column
        int[] colMin = new int[m];
        Arrays.fill(colMin, Integer.MAX_VALUE);
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                colMin[j] = Math.min(colMin[j], costMatrix[i][j]);
            }
            for (int i = 0; i < n; i++) {
                costMatrix[i][j] -= colMin[j];
            }
        }
        
        // Step 3: Find the minimum number of lines needed to cover all zeros in the cost matrix
        int[] rowCover = new int[n];
        int[] colCover = new int[m];
        Arrays.fill(rowCover, 0);
        Arrays.fill(colCover, 0);
        int numLines = 0;
        while (numLines < n) {
            int[] zeroPos = findUncoveredZero(costMatrix, rowCover, colCover);
            if (zeroPos[0] == -1) {
                // No uncovered zero found, proceed to step 5
                break;
            } else {
                // Cover the row and column containing the zero
                rowCover[zeroPos[0]] = 1;
                colCover[zeroPos[1]] = 1;
                numLines++;
            }
        }
        
        if (numLines == n) {
            // Step 4: All zeros are covered, proceed to step 6
            return findAssignments(costMatrix, rowCover, colCover);
        } else {
            // Step 5: Construct a new cost matrix based on the uncovered zeros
            int[][] newCostMatrix = constructNewCostMatrix(costMatrix, rowCover, colCover);
            int[] assignments = solveAssignmentProblem(newCostMatrix);
            
            // Step 6: Update the assignments based on the uncovered zeros
            for (int i = 0; i < n; i++) {
                if (rowCover[i] == 1) {
                    assignments[i] = findAssignedJob(costMatrix, i);
                }
            }
            
            return assignments;
        }
    }
    
    /**
     * Helper function to find an uncovered zero in the cost matrix.
     * 
     * @param costMatrix The cost matrix representing the assignment problem
     * @param rowCover The row cover array
     * @param colCover The column cover array
     * @return An array containing the row and column indices of an uncovered zero, or [-1, -1] if no uncovered zero is found
     */
    private static int[] findUncoveredZero(int[][] costMatrix, int[] rowCover, int[] colCover) {
        int n = costMatrix.length;
        int m = costMatrix[0].length;
        int[] zeroPos = new int[2];
        zeroPos[0] = -1;
        zeroPos[1] = -1;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (costMatrix[i][j] == 0 && rowCover[i] == 0 && colCover[j] == 0) {
                    zeroPos[0] = i;
                    zeroPos[1] = j;
                    return zeroPos;
                }
            }
        }
        
        return zeroPos;
    }
    
    /**
     * Helper function to find the job assigned to a person.
     * 
     * @param costMatrix The cost matrix representing the assignment problem
     * @param person The index of the person
     * @return The index of the job assigned to the person
     */
    private static int findAssignedJob(int[][] costMatrix, int person) {
        int m = costMatrix[0].length;
        for (int j = 0; j < m; j++) {
            if (costMatrix[person][j] == 0) {
                return j;
            }
        }
        return -1;
    }
    
    /**
     * Helper function to construct a new cost matrix based on the uncovered zeros.
     * 
     * @param costMatrix The cost matrix representing the assignment problem
     * @param rowCover The row cover array
     * @param colCover The column cover array
     * @return The new cost matrix
     */
    private static int[][] constructNewCostMatrix(int[][] costMatrix, int[] rowCover, int[] colCover) {
        int n = costMatrix.length;
        int m = costMatrix[0].length;
        int[][] newCostMatrix = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rowCover[i] == 0 && colCover[j] == 0) {
                    newCostMatrix[i][j] = costMatrix[i][j];
                } else {
                    newCostMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        return newCostMatrix;
    }
    
    /**
     * Helper function to find the assignments based on the covered zeros.
     * 
     * @param costMatrix The cost matrix representing the assignment problem
     * @param rowCover The row cover array
     * @param colCover The column cover array
     * @return An array of assignments, where assignments[i] represents the job assigned to person i
     */
    private static int[] findAssignments(int[][] costMatrix, int[] rowCover, int[] colCover) {
        int n = costMatrix.length;
        int[] assignments = new int[n];
        
        for (int i = 0; i < n; i++) {
            assignments[i] = findAssignedJob(costMatrix, i);
        }
        
        return assignments;
    }
    
    public static void main(String[] args) {
        int[][] costMatrix = {
            {9, 2, 7},
            {6, 4, 3},
            {5, 8, 1}
        };
        
        int[] assignments = solveAssignmentProblem(costMatrix);
        
        System.out.println("Assignments:");
        for (int i = 0; i < assignments.length; i++) {
            System.out.println("Person " + i + " -> Job " + assignments[i]);
        }
    }
}