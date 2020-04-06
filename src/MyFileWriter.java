import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

class MyFileWriter {
    String file_name;
    int k;
    MySort currSort;

    public MySort chooseSort(int i){
        switch (i){
            case 1:
                return new DualPivot();
            case 2:
                return new MergeSort();
            case 3:
                return new InsertSort();
            case 4:
                return new QuickSort();
        }
        return null;
    }

    public MyFileWriter(String file, int k) throws IOException {
        file_name = file;
        this.k = k;

        File f = new File("C:\\Users\\micha\\Desktop\\Projekty\\4sem\\algo");
        if (f.exists() && f.isFile())
        {
            f.delete();
        }
        f.createNewFile();
    }

    public void test(){
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new java.io.FileWriter(file_name, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 100; i++) {
            for (int sort = 1; sort < 5; sort++) {
                currSort = chooseSort(sort);

                for (int m = 0; m < k; m++) {

                    int[] rand = new int[10000];
                    for (int j = 0; j < rand.length; j++)
                        rand[j] = MyUtilities.random();

                    long start = System.nanoTime();
                    currSort.sort(rand, 0, 99999);
                    long elapsedTime = System.nanoTime() - start;

                    try {
                        assert writer != null;
                        writer.append(String.valueOf((i + 1) * 100));
                        writer.append(" ");
                        writer.append(String.valueOf(currSort.getComparisons()));
                        writer.append(" ");
                        writer.append(String.valueOf(currSort.getSwaps()));
                        writer.append(" ");
                        writer.append(String.valueOf(elapsedTime));
                        writer.append(" ");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        try {
            assert writer != null;
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
