package AdventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Comparator;

public class Day16a {
	
	static char[][] map;
	static HashMap<Character, int[]> dir;
	static boolean[][] energized;
	static HashMap<String, Character> mirror;
	static HashSet<String> seen;
	
	public static void main(String[] args) {
		try {
            String file = "C:/Users/Codey/Desktop/input.txt";    
            
			Scanner s1 = new Scanner(new File(file));
            int n = 1, m = s1.nextLine().length();
            while (s1.hasNextLine()) {
            	n++;
            	s1.nextLine();
            } //while
            s1.close();
            map = new char[n][m];
            energized = new boolean[n][m];
            Scanner s2 = new Scanner(new File(file));
            for (int i = 0; i < n; i++) {
            	String line = s2.nextLine();
            	for (int j = 0; j < m; j++) {
            		map[i][j] = line.charAt(j);
            	} //for
            } //for
            makeHashmap();
            traverse(new int[] {0, 0}, 'r');
            //printArr(energized);
            System.out.println("total: " + countBoolArr(energized));
		} catch (FileNotFoundException e) {
	        System.out.println(e);
	    } //catch
	} //main
	
	private static void traverse(int[] coords, char direction) {
		
		int x = coords[1], y = coords[0];
		if (!inBounds(y, x)) return;
		//System.out.println(coords[0] + " " + coords[1] + " " + direction);
		//printArr(map, coords);
		if (!seen.add(coords[0] + " " + coords[1] + "" + direction)) return;
		energized[y][x] = true;
		
		switch (map[y][x]) {
		case '.':
			x += dir.get(direction)[1];
			y += dir.get(direction)[0];
			traverse(new int[] {y, x}, direction);
			break;
		case '/':
			char fs = mirror.get("/" + direction);
			x += dir.get(fs)[1];
			y += dir.get(fs)[0];
			traverse(new int[] {y, x}, fs);
			break;
		case '\\':
			char bs = mirror.get("\\" + direction);
			x += dir.get(bs)[1];
			y += dir.get(bs)[0];
			traverse(new int[] {y, x}, bs);
			break;
		case '|':
			if (direction == 'u' || direction == 'd') {
				x += dir.get(direction)[1];
				y += dir.get(direction)[0];
				traverse(new int[] {y, x}, direction);
			} else {
				traverse(new int[] {y + dir.get('u')[0], x}, 'u');
				traverse(new int[] {y + dir.get('d')[0], x}, 'd');
			} //if
			break;
		case '-':
			if (direction == 'l' || direction == 'r') {
				x += dir.get(direction)[1];
				y += dir.get(direction)[0];
				traverse(new int[] {y, x}, direction);
			} else {
				
				traverse(new int[] {y, x + dir.get('l')[1]}, 'l');
				traverse(new int[] {y, x + dir.get('r')[1]}, 'r');
			} //if
			break;
		} //switch

	} //traverse
	
	private static int countBoolArr(boolean[][] arr) {
		int total = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j]) total++;
			} //for
		} //for
		return total;
	} //countBoolArr
	
	private static boolean inBounds(int x, int y) { 
		return (x > -1 && y > -1 && x < map[0].length && y < map.length);
	} //inBounds

	private static void makeHashmap() {
		seen = new HashSet<>();
		dir = new HashMap<>();
		dir.put('r', new int[] {0, 1});
		dir.put('l', new int[] {0, -1});
		dir.put('u', new int[] {-1, 0});
		dir.put('d', new int[] {1, 0});
		mirror = new HashMap<>();
		mirror.put("/u", 'r');
		mirror.put("/l", 'd');
		mirror.put("/r", 'u');
		mirror.put("/d", 'l');
		mirror.put("\\d", 'r');
		mirror.put("\\l", 'u');
		mirror.put("\\r", 'd');
		mirror.put("\\u", 'l');
	} //makeHashmap
	
	private static void printArr(boolean[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			boolean[] line = arr[i];
			System.out.print(i + space(i));
			for (boolean bool : line) {
				System.out.print((bool ? '#' : '.'));
			} //for
			System.out.println();
		} //for
		System.out.println();
	} //printArr
	
	private static void printArr(char[][] arr, int[] coords) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (i == coords[0] && j == coords[1]) System.out.print('@');
				else System.out.print(arr[i][j]);
			} //for
			System.out.println();
		} //for
		System.out.println();
	} //printArr
	
	private static String space(int i) {
		if (i < 10) return "  ";
		if (i < 100) return " ";
		return "";
	} //space

	
	
} //Day16a