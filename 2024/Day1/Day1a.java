import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day1a {

    static PriorityQueue<Integer> a = new PriorityQueue<>();
    static PriorityQueue<Integer> b = new PriorityQueue<>();
    
        public static void main(String[] args) {
    
            try {
                Scanner scanner = new Scanner(new File("///"));
    
                while (scanner.hasNextInt()) {
                    a.add(scanner.nextInt());
                    b.add(scanner.nextInt());
                } // while

                int total = 0;
                while (!a.isEmpty()) {
                    total += Math.abs(a.poll() - b.poll());
                } // while
                System.out.println(total);
            
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } // try

    } // main

} // Day1