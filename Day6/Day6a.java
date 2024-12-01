public class Day6a {

	//static int[] times = {7, 15, 30};
	//static int[] distances = {9, 40, 200};
	static int[] times = {48, 93, 85, 95};
	static int[] distances = {296, 1928, 1236, 1391};
	
	public static void main(String[] args) {
		int total = 1;
		for (int race = 0; race < times.length; race++) {
			total *= calculateWins(race);
		} //for
		System.out.println(total);
	} //main
	
	private static int calculateWins(int race) {
		int total = 0;
		int distance = distances[race], time = times[race];
		for (int i = 0; i < time; i++) {
			int remainingTime = time - i;
			if (i * remainingTime > distance) total++;
		} //for
		return total;		
	} //calculateWins
} //Day4a








