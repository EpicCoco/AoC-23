package AdventOfCode;

public class Day6b {

	//static int time = 71530;
	//static int distance = 940200;
	static long time = 48938595L;
	static long distance = 296192812361391L;
	
	public static void main(String[] args) {		
		System.out.println(calculateWins());
	} //main
	
	private static int calculateWins() {
		int total = 0;
		for (int i = 0; i < time; i++) {
			long remainingTime = time - i;
			if (i * remainingTime > distance) total++;
		} //for
		return total;		
	} //calculateWins
} //Day4a








