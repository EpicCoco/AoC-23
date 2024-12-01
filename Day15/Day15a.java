import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Comparator;

public class Day15a {
	
	public static void main(String[] args) {
		try {
            String file = "C:/Users/Codey/Desktop/input.txt";            
            Scanner s2 = new Scanner(new File(file));
            String[] input = s2.nextLine().split(",");
            int total = 0;
            for (String line : input) {
            	total += runAlgo(line);
            } //for

            System.out.println("total: " + total);
		} catch (FileNotFoundException e) {
	        System.out.println(e);
	    } //catch
	} //main
	
	private static int runAlgo(String line) {
		int val = 0;
		for (char c : line.toCharArray()) {
			val += (int)c;
			val *= 17;
			val %= 256;
		} //for
		return val;
	} //runAlgo

} //Day15a