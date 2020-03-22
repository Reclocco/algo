import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    static long timeElapsed;
    static int comparisons;
    static int swaps;
    static long startTime, endTime;

    //Misc
    private static void choice(int type, int comp, int[] arr) {
        switch (type) {
            case 1:
                startTime = System.nanoTime();
                insertsort(arr);
                endTime = System.nanoTime();
                timeElapsed = endTime - startTime;
                break;
            case 2:
                startTime = System.nanoTime();
                mergesort(arr, 0, arr.length - 1);
                endTime = System.nanoTime();
                timeElapsed = endTime - startTime;
                break;
            case 3:
                startTime = System.nanoTime();
                quicksort(arr, 0, arr.length - 1);
                endTime = System.nanoTime();
                timeElapsed = endTime - startTime;
                break;
        }

        if (comp == 1)
            flip(arr);
    }
    public static void flip(int[] arr) {
        int tmp;
        int p = 0;
        int q = arr.length - 1;

        for (int i = 0; i < arr.length / 2; i++) {
            tmp = arr[p + i];
            arr[p + i] = arr[q - i];
            arr[q - i] = tmp;
        }
    }
    private static int random() {
        Random r = new Random();
        return r.nextInt(2000000) - 1000000;
    }

    //Dual pivot
    public static int[] partitionDP(int[] arr, int beg, int end) {
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
                    swaps+=3;
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

                    swaps+=3;
                    tmp = arr[k];
                    arr[k] = arr[g];
                    arr[g] = tmp;
                    g--;

                    comparisons++;
                    if (arr[k] < pivot1) {
                        swaps+=3;
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
                        swaps+=3;
                        lowcount++;
                        tmp = arr[k];
                        arr[k] = arr[j];
                        arr[j] = tmp;
                        j++;
                    }
                } else if (arr[k] < pivot1) {
                    comparisons++;
                    comparisons++;
                    swaps+=3;
                    lowcount++;
                    tmp = arr[k];
                    arr[k] = arr[j];
                    arr[j] = tmp;
                    j++;
                }
            }
            k++;
        }
        j--;
        g++;

        swaps+=6;
        tmp=arr[beg];
        arr[beg]=arr[j];
        arr[j]=tmp;

        tmp=arr[end];
        arr[end]=arr[g];
        arr[g]=tmp;

        return new int[] {j, g};
    }
    public static void quicksortDP(int[] arr, int beg, int end) {
        comparisons++;
        if(beg < end) {
            int[] part = partitionDP(arr, beg, end);

            quicksortDP(arr, beg, part[0] - 1);
            quicksortDP(arr, part[0] + 1, part[1] - 1);
            quicksortDP(arr, part[1] + 1, end);
        }
    }

    //Quick
    public static int partition(int[] arr, int beg, int end) {
        int pivot = arr[end];
        int tmp;

        for (int i = beg; i < end; i++) {
            comparisons++;
            if (arr[i] < pivot) {
                swaps += 3;
                tmp = arr[i];
                arr[i] = arr[beg];
                arr[beg] = tmp;
                beg++;
            }
        }
        swaps += 3;
        tmp = arr[beg];
        arr[beg] = pivot;
        arr[end] = tmp;

        return beg;
    }
    public static void quicksort(int[] arr, int beg, int end) {

        int part = partition(arr, beg, end);

        if (part - 1 > beg) {
            comparisons++;
            quicksort(arr, beg, part - 1);
        }
        if (part + 1 < end) {
            comparisons++;
            quicksort(arr, part + 1, end);
        }
    }

    //Insert
    public static void insertsort(int[] arr) {
        int q = 1;
        int p = 0;
        int tmp;
        while (q < arr.length) {
            comparisons++;
            while (q - 1 - p >= 0 && arr[q - 1 - p] > arr[q - p]) {
                comparisons++;
                swaps+=3;
                tmp = arr[q - p];
                arr[q - p] = arr[q - p - 1];
                arr[q - p - 1] = tmp;
                p++;
            }
            q++;
            p = 0;
        }
    }

    //Merge
    public static void merge(int[] arr, int beg, int mid, int end) {
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
    public static void mergesort(int[] arr, int beg, int end) {
        if (beg < end) {
            int m = beg + (end - beg) / 2;

            mergesort(arr, beg, m);
            mergesort(arr, m + 1, end);

            merge(arr, beg, m, end);
        }
    }

    //Main
    public static void main(String[] args) {
        /*
            -1 -> wrong action
            0 -> considering for given argument
            1 -> insert / >=
            2 -> merge / <=
            3 -> quick
         */
        int type = -1;
        int comp = -1;
        int stat = -1;
        String file = "";
        int k = 0;

        label:
        for (String x : args) {
            if (stat == 1) {
                k = Integer.parseInt(x);
            }

            if (stat == 0) {
                file = x;
                stat++;
            }

            if (type == 0) {
                switch (x) {
                    case "insert":
                        type = 1;
                        break;
                    case "merge":
                        type = 2;
                        break;
                    case "quick":
                        type = 3;
                        break;
                    default:
                        System.out.println("wrong input!");
                        break label;
                }
            }

            if (comp == 0) {
                switch (x) {
                    case ">=":
                        comp = 1;
                        break;
                    case "<=":
                        comp = 2;
                        break;
                }
            }

            if (x.equals("--type"))
                type = 0;

            if (x.equals("--comp"))
                comp = 0;

            if (x.equals("--stat"))
                stat = 0;

        }
        System.out.println(type);
        System.out.println(comp);

        //Reading
        if (stat != 1) {
            Scanner in = new Scanner(System.in);
            String size = in.nextLine();
            int n = Integer.parseInt(size);
            System.out.println(n);

            String unsplit = in.nextLine();
            System.out.println(unsplit);
            String[] split = unsplit.split(" ");

            int[] arg = new int[n];
            for (int i = 0; i < n; i++) {
                arg[i] = Integer.parseInt(split[i]);
                System.out.println(arg[i]);
            }
            System.out.println(Arrays.toString(arg) + "\n");

            //Select appropriate condition
            choice(type, comp, arg);

        } else {
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(file, true));
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 100; i++) {
                for (int m = 0; m < k; m++) {
                    swaps=0;
                    comparisons=0;

                    int[] rand = new int[(i + 1) * 100];
                    for (int j = 0; j < rand.length; j++)
                        rand[j] = random();

                    choice(type, comp, rand);

                    try {
                        writer.append(String.valueOf((i+1)*100));
                        writer.append("   ");
                        writer.append(String.valueOf(comparisons));
                        writer.append("   ");
                        writer.append(String.valueOf(swaps));
                        writer.append("   ");
                        writer.append(String.valueOf(timeElapsed));
                        writer.append("\n");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Sorting testing
        int[] arg = new int[7];
        arg[0] = 6;
        arg[1] = 1;
        arg[2] = 453;
        arg[3] = 3;
        arg[4] = 64;
        arg[5] = 4;
        arg[6] = 63;
        System.out.println(Arrays.toString(arg));
        quicksortDP(arg, 0, arg.length-1);
        System.out.println(Arrays.toString(arg));
//        quicksort(arg, 0, arg.length - 1);
//        System.out.println(Arrays.toString(arg));
//        flip(arg);
//        System.out.println(Arrays.toString(arg));

//        System.out.println(Arrays.toString(arg));
//        insertsort(arg);
//        System.out.println(Arrays.toString(arg));
//
//        merge(arg, arg2);
    }
}
