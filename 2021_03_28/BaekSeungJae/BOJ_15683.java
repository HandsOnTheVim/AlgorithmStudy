package BOJ;
/*
 * 백준 : 감시
 * 링크 : https://www.acmicpc.net/problem/14502
 * 주요내용 : 구현, dfs
 */
import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_15683 {
	/* 문제에서 주어진 조건에서 답이 될 수 있는 최댓값 */
	static int answer = 64;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int col = sc.nextInt();
		int row = sc.nextInt();
		char[][] map = new char[col][row];
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				map[i][j] = sc.next().charAt(0);
			}
		}
		System.out.println(solution(map));

	}
	
	public static int solution(char[][] map) {
		/* cctv의 좌표 */
		ArrayList<ArrayList<Integer>> cctv = cctv(map);
		dfs_recursion(map, cctv, 0);
		return answer;
	}

	public static ArrayList<ArrayList<Integer>> cctv(char[][] map) { // cctv가 설치된 위치 좌표 저장
		ArrayList<ArrayList<Integer>> cctv = new ArrayList<>();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] != '0' && map[i][j] != '6') {
					/* cctv의 좌표 i,j를 배열에 저장 문제의 조건대로라면 최대 8개 */
					ArrayList<Integer> coordinate = new ArrayList<Integer>();
					coordinate.add(i);
					coordinate.add(j);
					cctv.add(coordinate);
				}
			}
		}
		return cctv;
	}

	public static char[][] detecting(char[][] map, int[] dirs, int y, int x) { // 감시가능한 타일을 #으로 변환
		// 지도 복사 , 원본을 건드리지 말아야 함
		char[][] copyedMap = map.clone();
		for (int i = 0; i < copyedMap.length; i++) {
			copyedMap[i] = map[i].clone();
		}
		/* 0:위 1:오른쪽 2:아래 3:왼쪽 */
		for (int i = 0; i < dirs.length; i++) {
			switch (dirs[i]) {
			/* dirs[i]는 감시하는 방향, 벽(6)이면 종료, cctv면 넘기고 진행, 둘다 아니면 #으로 변환 */
			case 0:
				for (int j = y; j >= 0; j--) {
					if (copyedMap[j][x] == '6') {
						break;
					} else if (copyedMap[j][x] == '0') {
						copyedMap[j][x] = '#';
					} else {
						continue;
					}
				}
				break;
			case 1:
				for (int j = x; j < copyedMap[0].length; j++) {
					if (copyedMap[y][j] == '6') {
						break;
					} else if (copyedMap[y][j] == '0') {
						copyedMap[y][j] = '#';
					} else {
						continue;
					}
				}
				break;
			case 2:
				for (int j = y; j < copyedMap.length; j++) {
					if (copyedMap[j][x] == '6') {
						break;
					} else if (copyedMap[j][x] == '0') {
						copyedMap[j][x] = '#';
					} else {
						continue;
					}
				}
				break;
			case 3:
				for (int j = x; j >= 0; j--) {
					if (copyedMap[y][j] == '6') {
						break;
					} else if (copyedMap[y][j] == '0') {
						copyedMap[y][j] = '#';
					} else {
						continue;
					}
				}
				break;
			}
		}
		return copyedMap;
	}

	public static void dfs_recursion(char[][] map, ArrayList<ArrayList<Integer>> cctv, int idx) {
		if (idx == cctv.size()) {
			// print(map);
			//System.out.println(answer);
			/* 최솟값 찾기 */
			answer = Math.min(answer, find_blind(map));
			return;
		}
		/* cctv좌표에서 y,x를 추출 */
		int y = cctv.get(idx).get(0);
		int x = cctv.get(idx).get(1);
		/* dirs는 cctv별 감시방향, map은 cctv 종류 */
		int[][] dirs = null;
		switch (map[y][x]) {
		case '1':
			dirs = new int[][] { { 0 }, { 1 }, { 2 }, { 3 } };
			for (int j = 0; j < dirs.length; j++) {
				char[][] nextMap = detecting(map, dirs[j], y, x);
				dfs_recursion(nextMap, cctv, idx + 1);
			}
			break;
		case '2':
			dirs = new int[][] { { 0, 2 }, { 1, 3 } };
			for (int j = 0; j < dirs.length; j++) {
				char[][] nextMap = detecting(map, dirs[j], y, x);
				dfs_recursion(nextMap, cctv, idx + 1);
			}
			break;
		case '3':
			dirs = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 } };
			for (int j = 0; j < dirs.length; j++) {
				char[][] nextMap = detecting(map, dirs[j], y, x);
				dfs_recursion(nextMap, cctv, idx + 1);
			}
			break;
		case '4':
			dirs = new int[][] { { 0, 1, 2 }, { 1, 2, 3 }, { 2, 3, 0 }, { 3, 0, 1 } };
			for (int j = 0; j < dirs.length; j++) {
				char[][] nextMap = detecting(map, dirs[j], y, x);
				dfs_recursion(nextMap, cctv, idx + 1);
			}
			break;
		case '5':
			dirs = new int[][] { { 0, 1, 2, 3 } };
			for (int j = 0; j < dirs.length; j++) {
				char[][] nextMap = detecting(map, dirs[j], y, x);
				dfs_recursion(nextMap, cctv, idx + 1);
			}
			break;
		}
	}

	public static void print(char[][] map) { // 지도값을 출력
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public static int find_blind(char[][] map) { // 사각지대를 카운트
		int blind = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == '0') {
					blind++;
				}
			}
		}
		return blind;
	}
}
