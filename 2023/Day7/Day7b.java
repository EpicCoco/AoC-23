import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Comparator;

public class Day7b {
	
	static HashMap<Character, Integer> charToInt = new HashMap<>();

	public static void main(String[] args) {
		charToInt.put('A', 12);
		charToInt.put('K', 11);
		charToInt.put('Q', 10);
		charToInt.put('T', 9);
		charToInt.put('9', 8);
		charToInt.put('8', 7);
		charToInt.put('7', 6);
		charToInt.put('6', 5);
		charToInt.put('5', 4);
		charToInt.put('4', 3);
		charToInt.put('3', 2);
		charToInt.put('2', 1);
		charToInt.put('J', 0);
		try {
            Scanner s = new Scanner(new File("///"));
            ArrayList<Long> hands = new ArrayList<>();
            while (s.hasNextLine()) {
            	String line = s.nextLine();
            	String hand = line.substring(0, 5);
            	int bid = Integer.parseInt(line.substring(6));
            	long score = findType(hand);
            	//System.out.println(hand + " " + score);
            	for (int i = 0; i < 5; i++) {
            		score *= 100;
            		score += charToInt.get(hand.charAt(i));
            	} //for
            	score *= 10000;
            	score += bid;
            	hands.add(score);
            } //while
            hands.sort(Comparator.naturalOrder());
            int total = 0;
            for (int i = 0; i < hands.size(); i++) {
            	long score = hands.get(i);
            	int bid = (int) (score - ((score / 10000) * 10000));
            	bid *= i + 1;
            	total += bid;
            } //for
            System.out.println("total: " + total);
		} catch (FileNotFoundException e) {
	        System.out.println(e);
	    } //catch
	} //main
	
	//convert hand into int representing hand type (bigger = better)
	private static int findType(String hand) {
		char[] handArr = hand.toCharArray();
		int[] freq = new int[13];
		int numJokers = 0;
		for (char c : handArr) {
			if (c != 'J') freq[charToInt.get(c)]++;
			else numJokers++;
		} //for
		Arrays.sort(freq);
		freq[12] += numJokers; //add jokers to most freq card
		if (freq[12] == 5) return 7; //5 of a kind
		if (freq[12] == 4) return 6; //4 of a kind
		if (freq[12] == 3 && freq[11] == 2) return 5; //full house
		if (freq[12] == 3) return 4; //3 of a kind
		if (freq[12] == 2 && freq[11] == 2) return 3; //2 pair
		if (freq[12] == 2) return 2; //1 pair
		return 1; //high card
	} //findType
} //Day7b
