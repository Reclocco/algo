public class QuickSort implements MySort {
    int comparisons = 0;
    int swaps = 0;

    public int partition(int[] arr, int beg, int end) {
        int pivot = arr[end];
        int tmp;

        for (int i = beg; i < end; i++) {
            comparisons++;
            if (arr[i] < pivot) {
                swaps ++;
                tmp = arr[i];
                arr[i] = arr[beg];
                arr[beg] = tmp;
                beg++;
            }
        }
        swaps ++;
        tmp = arr[beg];
        arr[beg] = pivot;
        arr[end] = tmp;

        return beg;
    }
    public void sort(int[] arr, int beg, int end) {

        int part = partition(arr, beg, end);

        if (part - 1 > beg) {
            comparisons++;
            sort(arr, beg, part - 1);
        }
        if (part + 1 < end) {
            comparisons++;
            sort(arr, part + 1, end);
        }
    }

    public int getComparisons(){
        return comparisons;
    }
    public int getSwaps(){
        return swaps;
    }
}
