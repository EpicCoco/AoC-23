package AdventOfCode;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.HashMap;

public class Day1 {

    private static HashMap <String, Integer> l2n = new HashMap <> ();

    public static void main (String [] args) {
    l2n.put("one", 1);
    l2n.put("two", 2);
    l2n.put("three", 3);
    l2n.put("four", 4);
    l2n.put("five", 5);
    l2n.put("six", 6);
    l2n.put("seven", 7);
    l2n.put("eight", 8);
    l2n.put("nine", 9);


    try {
               Scanner s = new Scanner(new File("///"));
        int sum = 0;

        while(s.hasNextLine()) {
        int res = parseLine(s.nextLine());
        System.out.println(res);
        sum += res;
        } //while

        System.out.println(sum);
    } catch (FileNotFoundException e) {
        System.out.println(e);
    } //catch

    } //main

    private static int parseLine(String line) {
    int n = line.length(), first = -1, firsti = n, last = -1, lasti = -1;

    for (String key : l2n.keySet()) {
        int firstOccurence = line.indexOf(key), lastOccurence = line.lastIndexOf(key);

        if (firstOccurence != -1 && firstOccurence < firsti) {
        first = l2n.get(key);
        firsti = firstOccurence;
        } //if

        if (lastOccurence != -1 && lastOccurence > lasti) {
        last = l2n.get(key);
        lasti = lastOccurence;
        } //if
    } //for

    for (int i = 0; i < firsti; i++) {
        if (Character.isDigit(line.charAt(i))) {
        first = Integer.parseInt(line.charAt(i) + "");
        break;
        } //if
    } //for

    for (int i = n - 1; i >= lasti; i--) {
        if (Character.isDigit(line.charAt(i))) {
        last = Integer.parseInt(line.charAt(i) + "");
        break;
        } //if
    } //for

    return first * 10 + last;
    } //parseLine
} //p1
