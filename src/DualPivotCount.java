public class DualPivotCount implements MySort {
    int comparisons = 0;
    int swaps = 0;

    public void sort(int[] arr, int beg, int end) {
        if (end <= beg) return;
        int tmp;
        int lowcount=0;
        int highcount=0;


        comparisons++;
        if(arr[beg]>arr[end]){
            tmp=arr[beg];
            arr[beg]=arr[end];
            arr[end]=tmp;
            swaps++;
        }

        int lt = beg + 1, gt = end - 1;
        int i = beg + 1;

        while (i <= gt) {
            comparisons++;
            if (arr[i] < arr[beg]){
                comparisons++;
                swaps++;
                tmp=arr[lt];
                arr[lt]=arr[i];
                arr[i]=tmp;
                i++;
                lt++;
            }
            else if (arr[end] < arr[i]){
                comparisons+=2;
                swaps++;
                tmp=arr[i];
                arr[i]=arr[gt];
                arr[gt]=tmp;
                gt--;
            }
            else i++;
        }

        swaps+=2;
        lt--;
        gt++;

        tmp=arr[beg];
        arr[beg]=arr[lt];
        arr[lt]=tmp;

        tmp=arr[end];
        arr[end]=arr[gt];
        arr[gt]=tmp;

        sort(arr, beg, lt - 1);

        if (arr[lt] < arr[gt]) sort(arr, lt + 1, gt - 1);
        sort(arr, gt + 1, end);
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