package AdventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day11b {
	
	static ArrayList<long[]> galaxies;
	
	public static void main(String[] args) {
		try {
            String file = "///";
			Scanner s1 = new Scanner(new File(file));
            int n = 1, m = s1.nextLine().length();
            while (s1.hasNextLine()) {
            	n++;
            	s1.nextLine();
            } //while
            s1.close();
            
            Scanner s = new Scanner(new File(file));
            int x = 0;
            char[][] space = new char[n][m];
            while (s.hasNextLine()) {
            	String line = s.nextLine();
        		for (int y = 0; y < line.length(); y++) {
        			space[x][y] = line.charAt(y);
        		} //for
        		x++;
            } //while
            //printArr(space);
            
            int[] xOffset = new int[space[0].length];
            int[] yOffset = new int[space.length];
            xOffset[0] = 0; yOffset[0] = 0;
            for (int i = 0; i < space[0].length; i++) {
            	boolean empty = true;
            	for (int j = 0; j < space.length; j++) {
            		if (space[i][j] == '#') {
            			empty = false;
            			break;
            		} //if
            	} //for
            	yOffset[i] = (empty ? 1 : 0);
            } //for
            for (int i = 1; i < yOffset.length; i++) yOffset[i] += yOffset[i - 1];
            for (int i = 0; i < space[0].length; i++) {
            	boolean empty = true;
            	for (int j = 0; j < space.length; j++) {
            		if (space[j][i] == '#') {
            			empty = false;
            			break;
            		} //if
            	} //for
            	xOffset[i] = (empty ? 1 : 0);
            } //for
            for (int i = 1; i < xOffset.length; i++) xOffset[i] += xOffset[i - 1]; 
            //System.out.println(Arrays.toString(xOffset));
            //System.out.println(Arrays.toString(yOffset));
            
            galaxies = new ArrayList<>();
            int expansionOffset = 1000000 - 1;
            for (int i = 0; i < space.length; i++) {
            	for (int j = 0; j < space[0].length; j++) {
            		if (space[i][j] == '#') {
            			galaxies.add(new long[] {i + (yOffset[i] * expansionOffset), j + (xOffset[j] * expansionOffset)});
            		} //if
            	} //for            	
            } //for	
            
            long total = 0;
            for (int i = 0; i < galaxies.size(); i++) {
            	//System.out.println(Arrays.toString(galaxies.get(i)));
            	long[] g1 = galaxies.get(i);
            	for (int j = i + 1; j < galaxies.size(); j++) {
            		long[] g2 = galaxies.get(j);
            		total += manhattanDist(g1[0], g1[1], g2[0], g2[1]);
            	} //for
            } //for
            System.out.println("total: " + total);
            
            
		} catch (FileNotFoundException e) {
	        System.out.println(e);
	    } //catch
	} //main	
	
	private static long manhattanDist(long a1, long a2, long b1, long b2) {
		return Math.abs(a2-b2) + Math.abs(a1-b1);
	} //manhattanDist
	
	private static void printArr(char[][] arr) {
		for (char[] line : arr) {
			System.out.println(String.valueOf(line));
		} //for
	} //printArr
	
} //Day10a