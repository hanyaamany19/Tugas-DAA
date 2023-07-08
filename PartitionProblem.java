import java.util.ArrayList;
import java.util.List;

public class PartitionProblem {
    public static void main(String[] args) {
        int[] nums = {3, 8, 4, 6, 1, 2};
        // int n = nums.length;

        List<Integer> partition1 = new ArrayList<>();
        List<Integer> partition2 = new ArrayList<>();

        boolean hasSolution = findPartition(nums, 0, 0, 0, partition1, partition2);

        if (hasSolution) {
            System.out.println("Solusi ditemukan:");
            System.out.println("Himpunan Bagian 1: " + partition1);
            System.out.println("Himpunan Bagian 2: " + partition2);
        } else {
            System.out.println("Tidak ada solusi");
        }
    }

    public static boolean findPartition(int[] nums, int index, int sum1, int sum2, List<Integer> partition1, List<Integer> partition2) {
        if (index == nums.length) {
            return sum1 == sum2;
        }

        // Coba tambahkan nums[index] ke himpunan bagian 1
        partition1.add(nums[index]);
        boolean found = findPartition(nums, index + 1, sum1 + nums[index], sum2, partition1, partition2);
        if (found) {
            return true;
        }
        partition1.remove(partition1.size() - 1);

        // Coba tambahkan nums[index] ke himpunan bagian 2
        partition2.add(nums[index]);
        found = findPartition(nums, index + 1, sum1, sum2 + nums[index], partition1, partition2);
        if (found) {
            return true;
        }
        partition2.remove(partition2.size() - 1);

        return false;
    }
}
