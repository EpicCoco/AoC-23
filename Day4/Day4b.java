package AdventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Day4b {

	public static void main(String[] args) {
		try {
            Scanner s = new Scanner(new File("///"));
            int total = 0;
            ArrayList<Integer> cards = new ArrayList<>();
            while (s.hasNextLine()) {
            	HashSet<Integer> hs = new HashSet<>();
            	String line = s.nextLine();
            	String have = line.substring(line.indexOf(":") + 2, line.indexOf("|") - 1);
            	String win = line.substring(line.indexOf("|") + 1);
            	Scanner sc1 = new Scanner(have);
            	while (sc1.hasNextInt()) {
            		hs.add(sc1.nextInt());
            	} //while
            	Scanner sc2 = new Scanner(win);
            	
            	int numCopies = 0;
            	while (sc2.hasNextInt()) {
            		if (!hs.add(sc2.nextInt())) {
            			numCopies++;
            		} //if
            	} //while
            	cards.add(numCopies);
            } //while
            
            ArrayList<Integer> winners = new ArrayList<>();
            for (int i = 0; i < cards.size(); i++) winners.add(1);
            System.out.println("cards: " + cards.toString());
            
            for (int i = 0; i < cards.size(); i++) {
            	for (int j = 1; j < cards.get(i)+1; j++) {
            		int idx = i + j;
            		//System.out.println(idx);
            		winners.set(idx, winners.get(i) + winners.get(idx));
            	} //for
            	total += winners.get(i);
            } //for
            System.out.println("winners: " + winners.toString());
            //System.out.println(cards.toString());
            //for (int i : winners) total += i;
            System.out.println("total: " + total);
		} catch (FileNotFoundException e) {
	        System.out.println(e);
	    } //catch
	} //main
} //Day4a
