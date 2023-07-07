import java.util.ArrayList;
import java.util.List;

public class PartitionProblem1 {
    
    /**
     * This method solves the partition problem by performing an exhaustive search.
     * Given an array of positive integers, it divides the array into two disjoint subsets
     * such that the sum of values in each subset is equal.
     * 
     * @param nums The array of positive integers
     * @return A list of two disjoint subsets with equal sum, or null if no solution exists
     */
    public static List<List<Integer>> partition(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        // Check if the total sum is odd, which means no solution exists
        if (totalSum % 2 != 0) {
            return null;
        }
        
        int targetSum = totalSum / 2;
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> currentSubset = new ArrayList<>();
        
        // Start the exhaustive search
        generateSubsets(nums, 0, targetSum, currentSubset, subsets);
        
        return subsets;
    }
    
    /**
     * Recursive helper method to generate subsets and find a solution to the partition problem.
     * 
     * @param nums The array of positive integers
     * @param index The current index in the array
     * @param targetSum The target sum for each subset
     * @param currentSubset The current subset being generated
     * @param subsets The list of subsets with equal sum
     */
    private static void generateSubsets(int[] nums, int index, int targetSum, List<Integer> currentSubset, List<List<Integer>> subsets) {
        // Base case: if the target sum is reached, add the current subset to the list of subsets
        if (targetSum == 0) {
            subsets.add(new ArrayList<>(currentSubset));
            return;
        }
        
        // Base case: if all elements have been processed or the target sum becomes negative, stop the recursion
        if (index >= nums.length || targetSum < 0) {
            return;
        }
        
        // Include the current element in the current subset
        currentSubset.add(nums[index]);
        generateSubsets(nums, index + 1, targetSum - nums[index], currentSubset, subsets);
        currentSubset.remove(currentSubset.size() - 1);
        
        // Exclude the current element from the current subset
        generateSubsets(nums, index + 1, targetSum, currentSubset, subsets);
    }
    
    public static void main(String[] args) {
        int[] nums = {3, 8, 4, 6, 1, 2};
        List<List<Integer>> subsets = partition(nums);
        
        if (subsets != null) {
            System.out.println("Two disjoint subsets with equal sum:");
            for (List<Integer> subset : subsets) {
                System.out.println(subset);
            }
        } else {
            System.out.println("No solution exists.");
        }
    }
}