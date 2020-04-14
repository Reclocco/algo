import java.util.Arrays;
import java.util.jar.JarOutputStream;

public class RadixSort implements MySort {
    int comparisons = 0;
    int swaps = 0;

    int getMax(int[] arr, int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++) {
            comparisons++;
            if (arr[i] > mx)
                mx = arr[i];
        }
        return mx;
    }

    void countSort(int[] arr, int n, int exp) {
        int[] output = new int[n];
        int i;
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (i = 0; i < n; i++){
            count[(arr[i] / exp) % 10]++;
        }
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        for (i = 0; i < n; i++) {
            swaps++;
            arr[i] = output[i];
        }
    }

    public void sort(int[] arr, int ignore, int n) {
        int m = getMax(arr, n+1);


        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, n+1, exp);
    }

    @Override
    public int getComparisons() {
        return comparisons;
    }

    @Override
    public int getSwaps() {
        return swaps;
    }
}