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
                return new DualPivotCount();
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

        File f = new File("C:\\Users\\micha\\Desktop\\Projekty\\4sem\\algo\\wyniki");
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
                for (int m = 0; m < k; m++) {
                    currSort = chooseSort(sort);

                    int[] rand = new int[(i+1)*100];
                    for (int j = 0; j < rand.length; j++)
                        rand[j] = MyUtilities.random();

                    long start = System.nanoTime();
                    currSort.sort(rand, 0, rand.length-1);
                    long elapsedTime = System.nanoTime() - start;

                    try {
                        assert writer != null;
                        writer.append(String.valueOf((i + 1) * 100));
                        writer.append("\n");
                        writer.append(String.valueOf(currSort.getComparisons()));
                        writer.append("\n");
                        writer.append(String.valueOf(currSort.getSwaps()));
                        writer.append("\n");
                        writer.append(String.valueOf(elapsedTime));
                        writer.append("\n");

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
