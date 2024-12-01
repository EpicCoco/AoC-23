import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Comparator;

public class Day19a {
	
	static HashMap<String, ArrayList<Instruction>> instructionSet;
	static ArrayList<Part> parts;
	
	public static void main(String[] args) {
		try {
            String file = "C:/Users/Codey/Desktop/input.txt";
            Scanner s2 = new Scanner(new File(file));
            parts = new ArrayList<>();
            instructionSet = new HashMap<>();
            boolean mode = true; //true for IS, false for parts
            while (s2.hasNextLine()) {
            	String line = s2.nextLine();
            	//System.out.println("test" + line);
            	if (line.length() == 0) {
            		mode = false;
            		line = s2.nextLine();
            	} //if
            	if (mode) {
            		String label = line.substring(0, line.indexOf('{'));
            		line = line.substring(line.indexOf('{') + 1, line.length() - 1);
            		String[] splitLine = line.split(",");
            		ArrayList<Instruction> toAdd = new ArrayList<>();
            		for (int i = 0; i < splitLine.length; i++) {
        				String l = splitLine[i];
            			if (i == splitLine.length - 1) {
            				toAdd.add(new Instruction(' ', true, 0, l));
            			} else {
                			char quality = l.charAt(0); 
                			boolean operation = l.charAt(1) == '>';
                			int value = Integer.parseInt(l.substring(2, l.indexOf(':')));
                			String next = l.substring(l.indexOf(':') + 1);
                			toAdd.add(new Instruction(quality, operation, value, next));
            			} //if
            			//System.out.println(toAdd.get(i));
            		} //for
            		instructionSet.put(label, toAdd);
            	} else {
            		line = line.substring(1, line.length() - 1);
            		String[] splitLine = line.split(",");
            		Part toAdd = new Part();
            		for (String l : splitLine) {
            			toAdd.addPart(l.charAt(0), Integer.parseInt(l.substring(2, l.length())));
            		} //for
            		parts.add(toAdd);
            	} //if
            } //while
            int total = 0;
            for (int i = 0; i < parts.size(); i++) {
            	Part part = parts.get(i);
            	ArrayList<Instruction> instSet = instructionSet.get("in");
            	String next = "";
            	
            	if (dfs(part, instSet, 0)) total += part.getTotal();
            	
            } //for
            
            System.out.println("total: " + total);
		} catch (FileNotFoundException e) {
	        System.out.println(e);
	    } //catch
	} //main
	
	private static boolean dfs(Part part, ArrayList<Instruction> instSet, int inst) {
		Instruction curr = instSet.get(inst);
		//System.out.println(part + ": " + curr);
		if (curr.quality == ' ') {
			if (curr.next.equals("A")) return true;
			if (curr.next.equals("R")) return false;
			return dfs(part, instructionSet.get(curr.next), 0);
		} else {
			int comp = part.partPieces.get(curr.quality);
			int threshhold = curr.value;
			boolean result = comp > threshhold;
			if (curr.operation) result = !result;
			if (result) {
				return dfs(part, instSet, inst + 1);
			} else {
				if (curr.next.equals("A")) return true;
				if (curr.next.equals("R")) return false;
				return dfs(part, instructionSet.get(curr.next), 0);
			} //if
		} //if		
	} //dfs
	
	static class Instruction {
		char quality;
		boolean operation; //true is greater than
		int value;
		String next;
		public Instruction(char quality, boolean operation, int value, String next) {
			this.quality = quality;
			this.operation = operation;
			this.value = value;
			this.next = next;
		} //Instruction
		public String toString() {
			return (quality + " " + (operation ? ">" : "<") + " " + value + " " + next);
		} //toString
	} //Workflow
	
	static class Part {
		HashMap<Character, Integer> partPieces;
		public Part() {
			partPieces = new HashMap<>();
		} //Part
		public void addPart(char quality, int value) {
			partPieces.put(quality, value);
		} //addPart
		public int getTotal() {
			int total = 0;
			for (int i : partPieces.values()) total += i;
			return total;
		} //getTotal
		public String toString() {
			return this.getTotal() + "";
		} //toString
	} //Part
} //Day19a

















