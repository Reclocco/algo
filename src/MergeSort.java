public class MergeSort implements MySort {
    int comparisons = 0;
    int swaps = 0;

    public void merge(int[] arr, int beg, int mid, int end) {
        int i, j, k;
        int n1 = mid-beg+1;
        int n2 = end-mid;

        int[] tmp1, tmp2;
        tmp1 = new int[n1];
        tmp2 = new int[n2];

        for (i = 0; i < n1; i++) {
            tmp1[i] = arr[beg + i];
            swaps++;
        }
        for (j = 0; j < n2; j++) {
            tmp2[j] = arr[mid + 1 + j];
            swaps++;
        }

        i = 0;
        j = 0;
        k = beg;
        while (i < n1 && j < n2) {
            if (tmp1[i] <= tmp2[j]) {
                arr[k] = tmp1[i];
                i++;
                comparisons++;
            } else {
                arr[k] = tmp2[j];
                j++;
                comparisons++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = tmp1[i];
            i++;
            k++;
            comparisons++;
        }

        while (j < n2) {
            arr[k] = tmp2[j];
            j++;
            k++;
            comparisons++;
        }
    }
    public void sort(int[] arr, int beg, int end) {
        comparisons++;
        if (beg < end) {
            int m = beg + (end - beg) / 2;

            sort(arr, beg, m);
            sort(arr, m + 1, end);

            merge(arr, beg, m, end);
        }
    }

    public int getComparisons(){
        comparisons = 0;
        return comparisons;
    }
    public int getSwaps(){
        swaps = 0;
        return swaps;
    }
}
