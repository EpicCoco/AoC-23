import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Day10b {

	static char[][] map;
	static boolean[][] loop, potentialH, potentialV, result;
	static int[] start, pos;
	static HashMap<String, Character> pipes;
	static HashMap<Character, int[]> dirs;
	static char prevDir;
	
	public static void main(String[] args) {
		initializeMaps();
		parseInput();
		//System.out.println();
		while (pos[0] != start[0] || pos[1] != start[1]) {
			//System.out.println("pos: " + pos[0] + " " + pos[1] + " " + map[pos[0]][pos[1]]);
			pos = findNext(pos[0], pos[1]);
			loop[pos[0]][pos[1]] = true;
		} //while
		loop[113][18] = true; map[113][18] = '|';
		
		for (int i = 0; i < loop.length; i++) {
			for (int j = 0; j < loop[0].length; j++) {
				if (!loop[i][j]) map[i][j] = '.';
			} //for
		} //for
		//printArr(map);
		
		checkHorizontal();
		//printArr(potentialH);
		checkVertical();
		//printArr(potentialV);
		
		mergeHandVinResult();
		//printArr(result);
		
		System.out.println("total: " + totalTrue(result));
	} //main
	
	private static void checkHorizontal() {
		for (int i = 0; i < map.length; i++) {
			int loopCount = 0;
			boolean tracking = false;
			int trackDir = 0; //tracking direction (1 = down, 2 = up, 3 = bar)
			
			for (int j = 0; j < map[0].length; j++) {
				char c = map[i][j];
				
				//has potential but needs vertical pass to be sure
				if (c == '.' && loopCount % 2 == 1) potentialH[i][j] = true;
				else if (c == '|') loopCount++;
				//else means need to update loopCount
				else {
					//tracking; need to see if stop tracking / update loopCount
					if (tracking) {
						switch (c) {
						case '7':
							tracking = false;
							if (trackDir == 2) loopCount++;
							break;
						case 'J':
							tracking = false;
							if (trackDir == 1) loopCount++;
							break;
						default:
							
						} //switch
					} 
					//not tracking; need to see whether to track + direction
					else {
						switch (c) {
						case 'F':
							tracking = true;
							trackDir = 1;
							break;
						case 'L':
							tracking = true;
							trackDir = 2;
							break;

						default:
							
						} //switch
					} //if
				} //if
			} //for
		} //for
	} //checkHorizontal
	
	private static void checkVertical() {
		for (int i = 0; i < map[0].length; i++) {
			int loopCount = 0;
			boolean tracking = false;
			int trackDir = 0; //tracking direction (1 = left, 2 = right, 3 = dash)
			
			for (int j = 0; j < map.length; j++) {
				char c = map[j][i];
				//has potential but needs vertical pass to be sure
				if (c == '.' && loopCount % 2 == 1) potentialV[j][i] = true;
				else if (c == '-') loopCount++;
				//else means need to update loopCount
				else {
					//tracking; need to see if stop tracking / update loopCount
					if (tracking) {
						switch (c) {
						case 'J':
							tracking = false;
							if (trackDir == 2) loopCount++;
							break;
						case 'L':
							tracking = false;
							if (trackDir == 1) loopCount++;
							break;

						default:
							
						} //switch
					} 
					//not tracking; need to see whether to track + direction
					else {
						switch (c) {
						case '7':
							tracking = true;
							trackDir = 1;
							break;
						case 'F':
							tracking = true;
							trackDir = 2;
							break;

						default:
							
						} //switch
					} //if
				} //if
			} //for
		} //for
	} //checkHorizontal
	
	
	
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
            loop = new boolean[n][m];
            potentialH = new boolean[n][m];
            potentialV = new boolean[n][m];
            result = new boolean[n][m];
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
            		map[start[0] + 1][start[1]] + "d",
            		map[start[0] - 1][start[1]] + "u"            		
            };
            for (String option : options) {
            	if (pipes.containsKey(option)) {
            		prevDir = option.charAt(1);
            		int[] offset = dirs.get(prevDir);
            		pos = new int[] {start[0] + offset[0], start[1] + offset[1]};
	            	loop[pos[0]][pos[1]] = true;
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
	
	private static String space(int i) {
		if (i < 10) return "  ";
		if (i < 100) return " ";
		return "";
	} //space
	
	private static void printArr(char[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			char[] line = arr[i];
			System.out.println(i + space(i) + String.valueOf(line));
		} //for
		System.out.println();
	} //printArr
	
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
	
	private static void mergeHandVinResult() {
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[0].length; j++) {
				result[i][j] = potentialV[i][j] && potentialH[i][j];
			} //for
		} //for
	} //mergeHandVinResult
	
	private static int totalTrue(boolean[][] arr) {
		int total = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j]) total++;
			} //for
		} //for
		return total;
	} //totalTrue
	
} //Day10a