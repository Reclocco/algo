import java.util.Random;

public class MyUtilities {
    static void choice(int type, int comp, int[] arr) {
        switch (type) {
            case 1:
                InsertSort insertsort = new InsertSort();
                insertsort.sort(arr, 0, 0);
                break;
            case 2:
                MergeSort mergeSort = new MergeSort();
                mergeSort.sort(arr, 0, arr.length - 1);
                break;
            case 3:
                QuickSort quickSort = new QuickSort();
                quickSort.sort(arr, 0, arr.length - 1);
                break;
            case 4:
                DualPivotCount dualPivotCount = new DualPivotCount();
                dualPivotCount.sort(arr, 0, arr.length - 1);
                break;
            case 5:
                RadixSort radixSort = new RadixSort();
                radixSort.sort(arr, 0, arr.length - 1);
                break;
        }

        if (comp == 1)
            flip(arr);
    }
    static void flip(int[] arr) {
        int tmp;
        int p = 0;
        int q = arr.length - 1;

        for (int i = 0; i < arr.length / 2; i++) {
            tmp = arr[p + i];
            arr[p + i] = arr[q - i];
            arr[q - i] = tmp;
        }
    }
    static int random() {
        Random r = new Random();
        return r.nextInt(1000000);
    }
}
