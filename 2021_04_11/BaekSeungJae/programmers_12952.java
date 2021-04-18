package programmers.level3;

public class programmers_12952 {

	public static void main(String[] args) {
		int n = 4;
		System.out.println(solution(n));
	}

	public static int answer;

	public static int solution(int n) {
		answer = 0;

		for (int i = 0; i < n; ++i) {
			int[] col = new int[n];
			col[0] = i;
			backtracking(n, 1, col);
		}

		return answer;
	}

	private static void backtracking(int max, int row, int[] col) {
		if (row == max) {
			answer++;
			col[row - 1] = 0;
			return;
		}

		for (int i = 0; i < max; ++i) {
			col[row] = i;
			if (isPossible(row, col)) {
				backtracking(max, row + 1, col);
			} else {
				col[row] = 0;
			}
		}
		col[row] = 0;
	}

	private static boolean isPossible(int row, int[] col) {
		for (int i = 0; i < row; ++i) {
			if (col[i] == col[row])
				return false;
			if (Math.abs(row - i) == Math.abs(col[row] - col[i]))
				return false;
		}
		return true;
	}
}
