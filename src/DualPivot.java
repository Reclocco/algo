public class DualPivot implements MySort {
    int comparisons = 0;
    int swaps = 0;

    public int[] partitionDP(int[] arr, int beg, int end) {
        int tmp;
        int lowcount=0;
        int highcount=0;

        comparisons++;
        if(arr[beg]>arr[end]){
            tmp=arr[beg];
            arr[beg]=arr[end];
            arr[end]=tmp;
        }

        int j=beg+1, g=end-1;
        int k=beg+1;
        int pivot1=arr[beg], pivot2=arr[end];

        while(k <= g){
            comparisons++;
            comparisons++;
            if(lowcount>highcount) {
                if (arr[k] < pivot1) {
                    comparisons++;
                    swaps+=1;
                    lowcount++;
                    tmp = arr[k];
                    arr[k] = arr[j];
                    arr[j] = tmp;
                    j++;
                } else if (arr[k] >= pivot2) {
                    comparisons++;
                    comparisons++;
                    highcount++;
                    while (arr[g] > pivot2 && k < g) {
                        comparisons++;
                        g--;
                    }

                    swaps+=1;
                    tmp = arr[k];
                    arr[k] = arr[g];
                    arr[g] = tmp;
                    g--;

                    comparisons++;
                    if (arr[k] < pivot1) {
                        swaps+=1;
                        lowcount++;
                        tmp = arr[k];
                        arr[k] = arr[j];
                        arr[j] = tmp;
                        j++;
                    }
                }
            } else {
                if (arr[k] >= pivot2) {
                    comparisons++;
                    highcount++;
                    while (arr[g] > pivot2 && k < g) {
                        comparisons++;
                        g--;
                    }

                    swaps+=3;
                    tmp = arr[k];
                    arr[k] = arr[g];
                    arr[g] = tmp;
                    g--;

                    comparisons++;
                    if (arr[k] < pivot1) {
                        swaps+=1;
                        lowcount++;
                        tmp = arr[k];
                        arr[k] = arr[j];
                        arr[j] = tmp;
                        j++;
                    }
                } else if (arr[k] < pivot1) {
                    comparisons++;
                    comparisons++;
                    swaps+=1;
                    lowcount++;
                    tmp = arr[k];
                    arr[k] = arr[j];
                    arr[j] = tmp;
                    j++;
                }
            }
            k++;
        }
        comparisons++;
        j--;
        g++;

        swaps+=2;
        tmp=arr[beg];
        arr[beg]=arr[j];
        arr[j]=tmp;

        tmp=arr[end];
        arr[end]=arr[g];
        arr[g]=tmp;

        return new int[] {j, g};
    }
    @Override
    public void sort(int[] arr, int beg, int end) {
        comparisons++;
        if(beg < end) {
            int[] part = partitionDP(arr, beg, end);

            sort(arr, beg, part[0] - 1);
            sort(arr, part[0] + 1, part[1] - 1);
            sort(arr, part[1] + 1, end);
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
