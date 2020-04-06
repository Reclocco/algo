import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        /**
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
                    case "dualpivot":
                        type = 4;
                        break;
                    default:
                        System.out.println("wrong input!");
                        break label;
                }
            }

            if (comp == 0) {
                switch (x) {
                    case "'>='":
                        comp = 1;
                        break;
                    case "'<='":
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

        if (stat != 1) {
            Scanner in = new Scanner(System.in);
            String size = in.nextLine();
            int n = Integer.parseInt(size);

            String unsplit = in.nextLine();
            String[] split = unsplit.split(" ");

            int[] arg = new int[n];
            for (int i = 0; i < n; i++) {
                arg[i] = Integer.parseInt(split[i]);
            }

            MyUtilities.choice(type, comp, arg);

            System.out.println(Arrays.toString(arg));

        } else {
            MyFileWriter myFileWriter = null;
            try {
                System.out.println("Write!");
                myFileWriter = new MyFileWriter(file, k);
                myFileWriter.test();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
