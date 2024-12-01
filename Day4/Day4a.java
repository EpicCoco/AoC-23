import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Day4a {

	public static void main(String[] args) {
		try {
            Scanner s = new Scanner(new File("///"));
            int total = 0;
            while (s.hasNextLine()) {
            	HashSet<Integer> hs = new HashSet<>();
            	String line = s.nextLine();
            	String have = line.substring(line.indexOf(":") + 2, line.indexOf("|") - 1);
            	String win = line.substring(line.indexOf("|") + 1);
            	Scanner sc1 = new Scanner(have);
            	while (sc1.hasNextInt()) {
            		hs.add(sc1.nextInt());
            	} //while
            	Scanner sc2 = new Scanner(win);
            	int mult = 0;
            	while (sc2.hasNextInt()) {
            		if (!hs.add(sc2.nextInt())) {
            			if (mult == 0) mult = 1;
            			else mult = 2*mult;
            		} //if
            	} //while
            	total += mult;
            } //while
            System.out.println(total);
		} catch (FileNotFoundException e) {
	        System.out.println(e);
	    } //catch
	} //main
} //Day4a
