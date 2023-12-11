package AdventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Comparator;

public class Day10a {

	static char[][] map;
	static int[] start, pos;
	static HashMap<String, Character> pipes;
	static HashMap<Character, int[]> dirs;
	static char prevDir;
	
	public static void main(String[] args) {
		initializeMaps();
		parseInput();
		//printArr(map);
		//System.out.println();
		int total = 1;
		while (pos[0] != start[0] || pos[1] != start[1]) {
			//System.out.println("pos: " + pos[0] + " " + pos[1] + " " + map[pos[0]][pos[1]]);
			pos = findNext(pos[0], pos[1]);
			total++;
		} //while
		total /= 2;
		System.out.println("total: " + total);
		
	} //main
	
	private static int[] findNext(int x, int y) {
		char pipe = map[x][y];
		//System.out.println("attempt:" + pipe + "" + prevDir);
		char nextDir = pipes.get(pipe + "" + prevDir);
		int[] offset = dirs.get(nextDir);
		prevDir = nextDir;
		int[] nextPos = new int[] {pos[0] + offset[0], pos[1] + offset[1]};
		return nextPos;
	} //findNext
	
	private static void parseInput() {
		
		try {
			String file = "///";
            Scanner s1 = new Scanner(new File(file));
            String line1 = s1.nextLine();
            int n = 1, m = line1.length();
            while (s1.hasNextLine()) {
            	s1.nextLine();
            	n++;
            } //while
            s1.close();
            map = new char[n][m];
            Scanner s2 = new Scanner(new File(file));
            int i = 0;
            //create map, find start
            while (s2.hasNextLine()) {
            	String line = s2.nextLine();
            	for (int j = 0; j < m; j++) {
            		map[i][j] = line.charAt(j);
            		if (map[i][j] == 'S') start = new int[] {i, j};
            	} //for
            	i++;
            } //while	
            //now initialize pos
            String[] options = new String[] {
            		map[start[0]][start[1] - 1] + "l",
            		map[start[0]][start[1] + 1] + "r",
            		map[start[0] - 1][start[1]] + "u",
            		map[start[0] + 1][start[1]] + "d"
            };
            for (String option : options) {
            	if (pipes.containsKey(option)) {
            		prevDir = option.charAt(1);
            		int[] offset = dirs.get(prevDir);
            		pos = new int[] {start[0] + offset[0], start[1] + offset[1]};
            		//System.out.println("generated pos: [" + pos[0] + "," + pos[1] + "] " + map[pos[0]][pos[1]]);
            		return;
            	} //if
            } //for
            
            
		} catch (FileNotFoundException e) {
	        System.out.println(e);
	    } //catch
	} //parseInput
	
	private static void initializeMaps() {
		pipes = new HashMap<>();
		//want to give new directions based on old ones
		pipes.put("7u", 'l');
		pipes.put("7r", 'd');
		pipes.put("Jd", 'l');
		pipes.put("Jr", 'u');
		pipes.put("Ld", 'r');
		pipes.put("Ll", 'u');
		pipes.put("Fu", 'r');
		pipes.put("Fl", 'd');
		pipes.put("-r", 'r');
		pipes.put("-l", 'l');
		pipes.put("|u", 'u');
		pipes.put("|d", 'd');
			
		dirs = new HashMap<>();
		//directions based on character
		dirs.put('u', new int[] {-1, 0});
		dirs.put('d', new int[] {1, 0});
		dirs.put('l', new int[] {0, -1});
		dirs.put('r', new int[] {0, 1});
	} //initializeMaps
	
	private static void printArr(char[][] arr) {
		for (char[] line : arr) {
			System.out.println(String.valueOf(line));
		} //for
	} //printArr
	
} //Day10a