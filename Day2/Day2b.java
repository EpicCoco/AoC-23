package adventofcode.Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2b {

	static int red = 12;
	static int green = 13;
	static int blue = 14;
	
	public static void main(String[] args) {
		try {
            Scanner s = new Scanner(new File("///"));
            int sum = 0;
            while (s.hasNextLine()) {
            	String line = s.nextLine();
            	int temp = parseLineB(line);
            	if (temp == 0) System.out.println("FAILED");
            	sum += temp;
            	System.out.println();
            } //while
            System.out.println("Sum: " + sum);
		} catch (FileNotFoundException e) {
	        System.out.println(e);
	    } //catch
	} //main


	private static int parseLineB(String line) {
    		int firstColon = line.indexOf(":");
    		int id = Integer.parseInt(line.substring(5, firstColon));
    		String[] games = line.substring(firstColon + 2).split(" ");
    		int maxGreen = 0, maxRed = 0, maxBlue = 0;
    		for (int i = 0; i < games.length; i += 2) {
    			//System.out.println(games[i]);
    			int num = Integer.parseInt(games[i]);
    			System.out.println(num + " " + games[i+1].substring(0, games[i+1].length() - 1));
    			if ((games[i+1].equals("green,") || games[i+1].equals("green;") || games[i+1].equals("green"))  && num > maxGreen) maxGreen = num;
    			else if ((games[i+1].equals("red,") || games[i+1].equals("red;") || games[i+1].equals("red")) && num > maxRed) maxRed = num;
    			else if ((games[i+1].equals("blue,") || games[i+1].equals("blue;") || games[i+1].equals("blue")) && num > maxBlue) maxBlue = num;
    		} //for
    		System.out.println(id + " was successful");
    		return maxGreen * maxRed * maxBlue;
	} //parseLine

} //Day2b