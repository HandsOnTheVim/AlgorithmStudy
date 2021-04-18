package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2533 {

	private static final int INFLUENCER = 1;
	private static final int NOT_INFLUENCER = 0;

	public static void main(String[] args) {
		System.out.println(solution());
	}

	public static int solution() {

		int[][] dfsGraph = getData();
		int length = dfsGraph.length - 1;

		print(dfsGraph);
		boolean[] visitArr = visitArr(length);
		int answer = Math.min(
				dfs(1, dfsGraph, visitArr, true, 1)
				, dfs(1, dfsGraph, visitArr, false, 0)
		);

		return answer;
	}

	public static void print(int[][] dfsGraph) {
		for (int i = 1; i < dfsGraph.length; i++) {
			for (int j = 1; j < dfsGraph[i].length; j++) {
				System.out.print(dfsGraph[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static int[][] getData() {
		int[][] dfsGraph = null;
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int length = Integer.parseInt(stringTokenizer.nextToken());
			dfsGraph = graph(length);
			while (bufferedReader.ready()) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				put(dfsGraph, Integer.parseInt(stringTokenizer.nextToken()),
						Integer.parseInt(stringTokenizer.nextToken()));
			}
		} catch (IOException e) {

		}
		return dfsGraph;
	}

	// 초기화
	public static int[][] graph(int initSize) {
		int[][] arrGraph;
		arrGraph = new int[initSize + 1][];
		for (int i = 0; i < initSize + 1; i++) {
			arrGraph[i] = new int[i + 1];
		}
		return arrGraph;
	}

	// 방문여부 초기화
	public static boolean[] visitArr(int nV) {
		boolean[] visitArr = new boolean[nV + 1];
		return visitArr;
	}

	// 양방향
	public static void put(int[][] arrGraph, int x, int y) {
		arrGraph[y][x] = 1;
	}

	public static int dfs(int idx, int[][] dfsGraph, boolean[] visitArr, boolean isInfluencer, int influencerCount) {

		if (influencerCount != 0) {
			return influencerCount;
		}

		int cnt = isInfluencer ? INFLUENCER : NOT_INFLUENCER;
		visitArr[idx] = true;

		if (isInfluencer) {
			for (int i = idx; i < dfsGraph[idx].length; i++) {
				if (dfsGraph[i][idx] == 1) {
					if (visitArr[idx]) {
						continue;
					}
					cnt += Math.min(dfs(i, dfsGraph, visitArr, false, cnt)
							, dfs(i, dfsGraph, visitArr, true, cnt + 1));
				}
			}
		} else {
			for (int i = idx; i < dfsGraph[idx].length; i++) {
				if (dfsGraph[i][idx] == 1) {
					if (visitArr[idx]) {
						continue;
					}
					cnt += dfs(i, dfsGraph, visitArr, true, cnt + 1);

				}
			}
		}
		visitArr[idx] = false;
		return cnt;
	}

}
