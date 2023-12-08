package AdventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Comparator;

public class Day8b {
	
	static HashMap<String, String> lookup = new HashMap<>();
	static String directions;

	public static void main(String[] args) {

		try {
            Scanner s = new Scanner(new File("///"));
            directions = s.nextLine();
            s.nextLine();
            ArrayList<String> starts = new ArrayList<>();
            while (s.hasNextLine()) {
            	String line = s.nextLine();
            	String dest = line.substring(0, 3);
            	String l = line.substring(7, 10);
            	String r = line.substring(12, 15);
            	//System.out.println(l+r);
            	lookup.put(dest, l + r);
            	if (dest.charAt(2) == 'A') starts.add(dest);
            } //while
            
            HashSet<Integer> nums = new HashSet<>();
            for (String line : starts) {
            	int score = runSim(line);
            	System.out.println(score);
            	nums.add(score);
            } //for
            int[] arr = new int[nums.size()];
            Iterator<Integer> it = nums.iterator();
            for (int i = 0; i < arr.length; i++) {
            	arr[i] = it.next();
            } //for
            
            //lcm method taken from GeeksForGeeks
            long lcm = lcm_of_array_elements(arr);
            
            System.out.println("total: " + lcm);
            
            //System.out.println("total: " + total);
		} catch (FileNotFoundException e) {
	        System.out.println(e);
	    } //catch

	} //main

	private static int runSim(String input) {
		String line = lookup.get(input);
		int total = 0, step = 0;
		//System.out.println("line:" + line);
		while (true) {
        	//System.out.println(line + " " + step);
        	String next = (directions.charAt(step) == 'R') ? line.substring(3) : line.substring(0, 3);
        	//System.out.println(next);
        	line = lookup.get(next);
        	total++;
        	if (next.charAt(2) == 'Z') return total;
        	step++;
        	if (step >= directions.length()) step = 0;
        } //while
		//return total;
	} //runSim
	
	//code taken from GeeksForGeeks
	//https://www.geeksforgeeks.org/lcm-of-given-array-elements/
	public static long lcm_of_array_elements(int[] element_array) {
        long lcm_of_array_elements = 1;
        int divisor = 2;
         
        while (true) {
            int counter = 0;
            boolean divisible = false;
             
            for (int i = 0; i < element_array.length; i++) {
 
                // lcm_of_array_elements (n1, n2, ... 0) = 0.
                // For negative number we convert into
                // positive and calculate lcm_of_array_elements.
 
                if (element_array[i] == 0) {
                    return 0;
                }
                else if (element_array[i] < 0) {
                    element_array[i] = element_array[i] * (-1);
                }
                if (element_array[i] == 1) {
                    counter++;
                }
 
                // Divide element_array by devisor if complete
                // division i.e. without remainder then replace
                // number with quotient; used for find next factor
                if (element_array[i] % divisor == 0) {
                    divisible = true;
                    element_array[i] = element_array[i] / divisor;
                }
            }
 
            // If divisor able to completely divide any number
            // from array multiply with lcm_of_array_elements
            // and store into lcm_of_array_elements and continue
            // to same divisor for next factor finding.
            // else increment divisor
            if (divisible) {
                lcm_of_array_elements = lcm_of_array_elements * divisor;
            }
            else {
                divisor++;
            }
 
            // Check if all element_array is 1 indicate 
            // we found all factors and terminate while loop.
            if (counter == element_array.length) {
                return lcm_of_array_elements;
            }
        }
    }
	
	
	
	
	
	
} //Day7a