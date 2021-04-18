package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_1948 {
	public static void main(String[] args) {
		System.out.println(solution());
	}

	public static int maxTime = 0;
	public static Set<String> repetitionCheckSet = new HashSet<>();

	public static String solution() {
		Data data = getData();

		int[][] graph = graph(data.cityCnt);

		for (int i = 0; i < data.map.length; i++) {
			put(graph, data.map[i][0], data.map[i][1], data.map[i][2]);
		}

		ArrayList<Integer> visitList = new ArrayList<>(data.cityCnt);
		visitList.add(data.origin);

		dfs(visitList, graph, data.origin, data.destination, 0);
		return maxTime + "\n" + repetitionCheckSet.size();
	}

	public static Data getData() {
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int cityCnt = Integer.parseInt(stringTokenizer.nextToken());
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int roadCnt = Integer.parseInt(stringTokenizer.nextToken());
			int[][] map = new int[roadCnt][3];
			for (int i = 0; i < roadCnt; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				map[i][0] = Integer.parseInt(stringTokenizer.nextToken());
				map[i][1] = Integer.parseInt(stringTokenizer.nextToken());
				map[i][2] = Integer.parseInt(stringTokenizer.nextToken());
			}
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int origin = Integer.parseInt(stringTokenizer.nextToken());
			int destination = Integer.parseInt(stringTokenizer.nextToken());
			return new Data(cityCnt, roadCnt, map, origin, destination);
		} catch (IOException ignored) {
			System.out.println("IOE");
		}

		return new Data();
	}

	public static int[][] graph(int initSize) {
		return new int[initSize + 1][initSize + 1];
	}

	// 양방향 w는 가중치
	public static void put(int[][] arrGraph, int y, int x, int weight) {
		// arrGraph[y][x] = arrGraph[x][y] = weight;
		arrGraph[y][x] = weight;
	}

	public static void dfs(ArrayList<Integer> visitList, int[][] dfsGraph, int vIdx, int nV,
			int time) {

		if (vIdx == nV) {
			if (time >= maxTime) {
				if (time != maxTime) {
					repetitionCheckSet.clear();
				}
				maxTime = time;
				for (int i = 1; i < visitList.size(); i++) {
					repetitionCheckSet.add(new StringBuilder()
							.append(visitList.get(i - 1))
							.append(',')
							.append(visitList.get(i))
							.toString());
				}
			}
			return;
		}

		for (int i = 1; i <= nV; i++) {
			if (dfsGraph[vIdx][i] != 0) {
				ArrayList<Integer> nextVisitList = (ArrayList<Integer>)visitList.clone();
				if (nextVisitList.contains(i) == false) {
					nextVisitList.add(i);
					dfs(nextVisitList, dfsGraph, i, nV, time + dfsGraph[vIdx][i]); // dfs() 재귀호출
				}
			}
		}
	}

	static class Data {
		int cityCnt;
		int roadCnt;
		int[][] map;
		int origin;
		int destination;

		public Data() {
		}

		public Data(int cityCnt, int roadCnt, int[][] map, int origin, int destination) {
			this.cityCnt = cityCnt;
			this.roadCnt = roadCnt;
			this.map = map;
			this.origin = origin;
			this.destination = destination;
		}
	}
}
