public class HybridSort {
    int comparisons = 0;
    int swaps = 0;

    public void merge_obj(Comparable[] arr, int beg, int mid, int end){
        int i, j, k;
        int n1 = mid-beg+1;
        int n2 = end-mid;

        Comparable[] tmp1, tmp2;
        tmp1 = new Comparable[n1];
        tmp2 = new Comparable[n2];

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
            if (tmp1[i].compareTo(tmp2[j])<1) {
                arr[k] = tmp1[i];
                i++;
            } else {
                arr[k] = tmp2[j];
                j++;
            }
            comparisons++;
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
    public Comparable[] sort(Comparable[] arr, int beg, int end){
        if (end - beg > 40) {
            int m = beg + (end - beg) / 2;

            sort(arr, beg, m);
            sort(arr, m + 1, end);

            merge_obj(arr, beg, m, end);
        } else {
            int q = 1;
            int p = 0;
            Comparable tmp;
            while (q < arr.length) {
                comparisons++;
                while (q - 1 - p >= 0 && arr[q - 1 - p].compareTo(arr[q - p])>0) {
                    comparisons++;
                    swaps+=1;
                    tmp = arr[q - p];
                    arr[q - p] = arr[q - p - 1];
                    arr[q - p - 1] = tmp;
                    p++;
                }
                q++;
                p = 0;
            }
        }
        return arr;
    }

    public int getComparisons(){
        return comparisons;
    }
    public int getSwaps(){
        return swaps;
    }
}
