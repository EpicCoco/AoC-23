import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Comparator;

public class Day8a {
	
	static HashMap<String, String> lookup = new HashMap<>();

	public static void main(String[] args) {

		try {
            Scanner s = new Scanner(new File("///"));
            String directions = s.nextLine();
            s.nextLine();
            while (s.hasNextLine()) {
            	String line = s.nextLine();
            	String dest = line.substring(0, 3);
            	String l = line.substring(7, 10);
            	String r = line.substring(12, 15);
            	//System.out.println(l+r);
            	lookup.put(dest, l + r);
            } //while
            
            int step = 0, total = 0;
            String line = lookup.get("AAA");
            while (true) {
            	//System.out.println(line);
            	String next = (directions.charAt(step) == 'R') ? line.substring(3) : line.substring(0, 3);
            	//System.out.println(next);
            	line = lookup.get(next);
            	total++;
            	if (next.equals("ZZZ")) break;
            	step++;
            	if (step >= directions.length()) step = 0;
            } //while
            System.out.println("total: " + total);
            
            //System.out.println("total: " + total);
		} catch (FileNotFoundException e) {
	        System.out.println(e);
	    } //catch
	} //main

	
	
	
	
	
	
	
	
	
} //Day7a