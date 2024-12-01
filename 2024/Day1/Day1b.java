import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day1b {

        static ArrayList<Integer> vals = new ArrayList<>();
        static int freq[] = new int[100000];

    
        public static void main(String[] args) {
    
            try {
                Scanner scanner = new Scanner(new File("///"));
    
                while (scanner.hasNextInt()) {
                    int a = scanner.nextInt();
                    int b = scanner.nextInt();

                    vals.add(a);
                    freq[b]++;
                } // while

                int total = 0;

                for (int i = 0; i < vals.size(); i++) {
                    int val = vals.get(i);
                    total += val * freq[val];
                } // for

                System.out.println(total);
            
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } // try

    } // main

} // Day1