/* BOJ_2632 피자판매 */
package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_2632 {

	public static void main(String[] args) {
		System.out.println(solution());
	}

	public static long solution() {

		Data data = getData();
		int[] pizzaA = data.pizzaA;

		int[] pizzaB = data.pizzaB;
		int sizePizza = data.sizePizza;

		HashMap<Integer, Integer> pieceMapA = new HashMap<>();
		HashMap<Integer, Integer> pieceMapB = new HashMap<>();

		getCombination(pizzaA, pieceMapA);
		getCombination(pizzaB, pieceMapB);

		long answer = 0;
		for (Integer sumA : pieceMapA.keySet()) {
			int count = pieceMapB.getOrDefault(sizePizza - sumA, -1);
			if (count != -1) {
				answer += count * pieceMapA.get(sumA);
			}
		}

		return answer;
	}

	private static void getCombination(int[] pizza, HashMap<Integer, Integer> pieceMap) {
		int length = pizza.length;

		for (int i = 0; i < length; i++) {
			int sum = 0;
			int index = i;
			while (true) {
				sum += pizza[index % length];
				pieceMap.putIfAbsent(sum, 0);
				pieceMap.replace(sum, pieceMap.get(sum) + 1);
				index++;

				if (index - i + 1 >= length) {
					break;
				}
			}
		}

		pieceMap.put(0, 1);
		pieceMap.put(getPizzaSize(pizza), 1);

		return;
	}

	public static int getPizzaSize(int[] pizza) {
		int sum = 0;
		for (int i = 0; i < pizza.length; i++) {
			sum += pizza[i];
		}
		return sum;
	}

	public static Data getData() {
		int[] pizzaA = null;
		int[] pizzaB = null;
		int pizzaPiece = 0;
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			pizzaPiece = Integer.parseInt(stringTokenizer.nextToken());

			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int lengthPizzaA = Integer.parseInt(stringTokenizer.nextToken());
			int lengthPizzaB = Integer.parseInt(stringTokenizer.nextToken());
			pizzaA = new int[lengthPizzaA];
			pizzaB = new int[lengthPizzaB];
			for (int i = 0; i < lengthPizzaA; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				pizzaA[i] = Integer.parseInt(stringTokenizer.nextToken());
			}
			for (int i = 0; i < lengthPizzaB; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				pizzaB[i] = Integer.parseInt(stringTokenizer.nextToken());
			}
		} catch (IOException e) {
		}

		return new Data(pizzaA, pizzaB, pizzaPiece);
	}

	static class Data {
		int[] pizzaA;
		int[] pizzaB;
		int sizePizza;

		public Data(int[] pizzaA, int[] pizzaB, int sizePizza) {
			this.pizzaA = pizzaA;
			this.pizzaB = pizzaB;
			this.sizePizza = sizePizza;
		}
	}
}
