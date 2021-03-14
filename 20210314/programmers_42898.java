package programmers.level3;

public class programmers_42898 {

	public static void main(String[] args) {
		int m = 5;
		int n = 5;
		int[][] puddles = {{1, 3}, {4, 1}};
		System.out.println(solution(m, n, puddles));
	}

	public static int solution(int m, int n, int[][] puddles) {
		int[][] map = new int[n][m];

		// 웅덩이 좌표 삽입
		for (int i = 0; i < puddles.length; i++) {
			// puddles의 좌표가 1부터 시작이므로 -1로 보정
			map[puddles[i][1] - 1][puddles[i][0] - 1] = -1;
		}
		map[0][0] = 1;
		// 가로 세로 첫줄에 시작점 추가
		for (int i = 1; i < m; i++) {
			if (isRoad(map[0][i]) && isRoad(map[0][i - 1])) {
				map[0][i] += map[0][i - 1];
			}
		}
		for (int i = 1; i < n; i++) {
			if (isRoad(map[i][0]) && isRoad(map[i - 1][0])) {
				map[i][0] += map[i - 1][0];
			}
		}
		print(map);

		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				if (isRoad(map[i][j])) {
					if (isRoad(map[i - 1][j])) {
						map[i][j] += map[i - 1][j];
					}
					if (isRoad(map[i][j - 1])) {
						map[i][j] += map[i][j - 1];
					}
				}
				map[i][j] %= 1000000007;
				print(map);
			}
		}

		int answer = map[n - 1][m - 1] % 1000000007;
		return answer;
	}

	public static boolean isRoad(int point) {
		return point != -1;
	}

	public static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
