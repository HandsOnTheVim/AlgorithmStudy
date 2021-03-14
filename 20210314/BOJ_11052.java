package BOJ;

import java.util.Scanner;

public class BOJ_11052 {

	public static void main(String[] args) {
		System.out.println(solution());
	}

	public static int solution() {
		int[][] data = getData();

		int length = data[0][0];
		int[] prices = data[1];

		int[] dp = new int[length];

		dp[0] = prices[0];

		for (int i = 1; i < length; i++) {
			int max = 0;
			for (int j = 0; j < (i + 1) / 2; j++) {
				max = Math.max(max, dp[i - 1 - j] + dp[j]);
			}
			dp[i] = Math.max(prices[i], max);
		}
		return dp[length - 1];
	}

	public static int[][] getData() {
		Scanner sc = new Scanner(System.in);
		int length = sc.nextInt();
		int[][] data = new int[2][];
		data[0] = new int[1];
		data[0][0] = length;
		data[1] = new int[length];
		for (int i = 0; i < data[1].length; i++) {
			data[1][i] = sc.nextInt();
		}
		return data;
	}
}
