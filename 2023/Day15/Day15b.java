import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Day15b {
	
	public static void main(String[] args) {
		try {
            String file = "C:/Users/Codey/Desktop/input.txt";            
            Scanner s2 = new Scanner(new File(file));
            String[] input = s2.nextLine().split(",");
            ArrayList<LinkedList<String>> list = new ArrayList<>();
            for (int i = 0; i < 256; i++) list.add(new LinkedList<String>());
            for (String line : input) {
            	boolean type = line.contains("="); //false means remove, true means add/replace
            	int split = type ? line.indexOf('=') : line.indexOf('-');
            	String lens = line.substring(0, split);
            	int idx = runAlgo(lens);
            	LinkedList<String> item = list.get(idx);
            	if (type) {
            		//add/replace
            		int lensNum = Integer.parseInt(line.substring(split + 1));
            		String newLens = lens + " " + lensNum;
            		if (contains(item, lens)) {
            			for (int i = 0; i < item.size(); i++) {
                			String temp = item.get(i);
                			if (temp.substring(0, temp.indexOf(" ")).equals(lens)) {
                				item.set(i, newLens);
                				break;
                			} //if
                		} //for
            		} else {
            			item.add(newLens);
            		} //if
            	} else {
            		//remove
            		for (int i = 0; i < item.size(); i++) {
            			String temp = item.get(i);
            			if (temp.substring(0, temp.indexOf(" ")).equals(lens)) {
            				item.remove(i);
            				break;
            			} //if
            		} //for
            	} //if
            } //for
            
            int total = 0;
            //find focusing power
            for (int i = 0; i < list.size(); i++) {
            	LinkedList<String> item = list.get(i);
            	for (int j = 0; j < item.size(); j++) {
            		String slot = item.get(j);
            		int intensity = Integer.parseInt(slot.substring(slot.indexOf(" ") + 1));
            		total += (i + 1) * (j + 1) * intensity;
            	} //for
            } //for
            System.out.println("total: " + total);
		} catch (FileNotFoundException e) {
	        System.out.println(e);
	    } //catch
	} //main
	
	private static boolean contains(LinkedList<String> item, String search) {
		for (int i = 0; i < item.size(); i++) {
			String temp = item.get(i);
			if (temp.substring(0, temp.indexOf(" ")).equals(search)) {
				return true;
			} //if
		} //for
		return false;
	} //contains
	
	private static int runAlgo(String line) {
		int val = 0;
		for (char c : line.toCharArray()) {
			val += (int)c;
			val *= 17;
			val %= 256;
		} //for
		return val;
	} //runAlgo

} //Day15b