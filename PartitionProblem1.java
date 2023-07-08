import java.util.ArrayList;
import java.util.List;

public class PartitionProblem1 {
    
    /**
      * Metode ini memecahkan masalah partisi dengan melakukan pencarian lengkap.
      * Diberikan array bilangan bulat positif, itu membagi array menjadi dua himpunan bagian yang terpisah
      * sehingga jumlah nilai di setiap subset sama.
      *
      * @param nums Array bilangan bulat positif
      * @return Daftar dua himpunan bagian terpisah dengan jumlah yang sama, atau nol jika tidak ada solusi
      */
    public static List<List<Integer>> partition(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        // Periksa apakah jumlah totalnya ganjil, yang berarti tidak ada solusi
        if (totalSum % 2 != 0) {
            return null;
        }
        
        int targetSum = totalSum / 2;
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> currentSubset = new ArrayList<>();
        
        // Mulai pencarian lengkap
        generateSubsets(nums, 0, targetSum, currentSubset, subsets);
        
        return subsets;
    }
    
    /**
      * Metode pembantu rekursif untuk menghasilkan himpunan bagian dan menemukan solusi untuk masalah partisi.
      *
      * @param nums Array bilangan bulat positif
      * @param index Indeks saat ini dalam array
      * @param targetSum Jumlah target untuk setiap subset
      * @param currentSubset Subset saat ini sedang dibuat
      * @param himpunan bagian Daftar himpunan bagian dengan jumlah yang sama
      */
    private static void generateSubsets(int[] nums, int index, int targetSum, List<Integer> currentSubset, List<List<Integer>> subsets) {
        // Kasus dasar: jika jumlah target tercapai, tambahkan subset saat ini ke daftar subset
        if (targetSum == 0) {
            subsets.add(new ArrayList<>(currentSubset));
            return;
        }
        
        // Kasus dasar: jika semua elemen telah diproses atau jumlah target menjadi negatif, hentikan rekursi
        if (index >= nums.length || targetSum < 0) {
            return;
        }
        
        // Sertakan elemen saat ini di subset saat ini
        currentSubset.add(nums[index]);
        generateSubsets(nums, index + 1, targetSum - nums[index], currentSubset, subsets);
        currentSubset.remove(currentSubset.size() - 1);
        
        // Kecualikan elemen saat ini dari subset saat ini
        generateSubsets(nums, index + 1, targetSum, currentSubset, subsets);
    }
    
    public static void main(String[] args) {
        int[] nums = {3, 8, 4, 6, 1, 2};
        List<List<Integer>> subsets = partition(nums);
        
        if (subsets != null) {
            System.out.println("Dua himpunan bagian terpisah dengan jumlah yang sama:");
            for (List<Integer> subset : subsets) {
                System.out.println(subset);
            }
        } else {
            System.out.println("Tidak ada solusi.");
        }
    }
}