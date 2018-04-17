package sortieren;

public class BubbleSort {

	public static int[] Sort(int[] input) {
		
		for (int x = 0; x < input.length - 1; x++) {			
			for (int y = 0; y < x; y++) {
				if (input[y] > input[y+1]) {
					int temp = input[y];
					input[y] = input[y+1];
					input[y+1] = temp;
				}
			}		
		}
		
		return input;		
	}

}