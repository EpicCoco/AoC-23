import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Comparator;

public class Day9a {
	
	static HashMap<String, String> lookup = new HashMap<>();

	public static void main(String[] args) {

		try {
            Scanner s = new Scanner(new File("///"));
            int total = 0;
            while (s.hasNextLine()) {
            	String line = s.nextLine();
        		String[] delim = line.split(" ");
        		int[] temp = new int[delim.length];
        		for (int i = 0; i < delim.length; i++) temp[i] = Integer.parseInt(delim[i]);
            	total += evaluateLine(temp);
            } //while	
            System.out.println("total: " + total);
            
            
		} catch (FileNotFoundException e) {
	        System.out.println(e);
	    } //catch
	} //main

	private static int evaluateLine(int[] line) {
		ArrayList<int[]> hist = new ArrayList<>();
		hist.add(line);
		
		int num = line.length - 1;
		boolean allZeroes = false;
		while (!allZeroes) {
			int[] nextHist = new int[num];
			int[] oldHist = hist.get(hist.size() - 1);
			for (int i = 0; i < nextHist.length; i++) {
				nextHist[i] = (oldHist[i + 1] - oldHist[i]);
			} //for
			hist.add(nextHist);
			allZeroes = areAllZeroes(nextHist);
			
			num--;
		} //while
		//printArr(hist);
		
		int[] predictedValues = new int[hist.size()];
		predictedValues[0] = 0;
		for (int i = 1; i < predictedValues.length; i++) {
			int[] temp = hist.get(hist.size() - i - 1);
			predictedValues[i] = predictedValues[i - 1] + temp[temp.length - 1];
		} //for
		
		//System.out.println("Predicted values: " + Arrays.toString(predictedValues));
		
		return predictedValues[predictedValues.length - 1];
	} //evaluateLine
	
	private static boolean areAllZeroes(int[] arr) {
		for (int i : arr) if (i != 0) return false;
		return true;
	} //areAllZeroes
	
	
	private static void printArr(ArrayList<int[]> hist) {
		for (int[] i : hist) System.out.println("Line: " + Arrays.toString(i));
		System.out.println();
	} //printArr
	
} //Day9a