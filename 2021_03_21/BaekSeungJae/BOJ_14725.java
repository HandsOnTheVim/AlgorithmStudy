/* 개미굴 */
package BOJ;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class BOJ_14725 {

	public static void main(String[] args) {
		System.out.println(solution());
	}

	public static String solution() {
		Scanner sc = new Scanner(System.in);
		String[][] data = getData(sc, sc.nextInt());

		Node<String> root = new Node<>(null);
		for (int i = 0; i < data.length; i++) {
			Node<String> child;
			if (root.childNode.get(data[i][0]) != null) {
				child = root.childNode.get(data[i][0]);
			} else {
				child = new Node<>(data[i][0]);
				root.childNode.put(data[i][0], child);
			}
			root.childNode.put(data[i][0], child);
			for (int j = 1; j < data[i].length; j++) {
				Node<String> grandChild;
				if (child.childNode.get(data[i][j]) != null) {
					grandChild = child.childNode.get(data[i][j]);
				} else {
					grandChild = new Node<>(data[i][j]);
					child.childNode.put(data[i][j], grandChild);
				}
				child = grandChild;
			}
		}

		BSTree<String> tree = new BSTree<>(root);
		tree.frontOrderTraverse();

		return "";
	}

	public static String[][] getData(Scanner sc, int length) {
		String[][] data = new String[length][];
		for (int i = 0; i < data.length; i++) {
			data[i] = new String[sc.nextInt()];
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = sc.next();
			}
		}
		return data;
	}

	public static void print(String[][] data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static class Node<T> {
		public T item;
		private TreeMap<String, Node<T>> childNode;

		public Node(T item) {
			this.item = item;
			this.childNode = new TreeMap<>();
		}
	}

	public static class BSTree<T extends Comparable<T>> {
		public Node<T> root;

		public BSTree(Node<T> root) {
			this.root = root;
		}

		public void frontOrderTraverse() {
			frontOrderTraverse(root, "");
		}

		private void frontOrderTraverse(Node<T> tree, String depth) {
			if (tree != null) {
				if (tree.item != null) {
					System.out.println(depth.replaceFirst("--", "") + tree.item);
				}

				for (Map.Entry<String, Node<T>> entry : tree.childNode.entrySet()) {
					frontOrderTraverse(entry.getValue(), depth + "--");
				}
			}
		}
	}
}
