/* 트리 순회 */
package BOJ;

import java.util.HashMap;
import java.util.Scanner;

public class BOJ_1191 {

	public static void main(String[] args) {
		System.out.println(solution());
	}

	public static String solution() {
		Scanner sc = new Scanner(System.in);
		String[][] data = getData(sc, sc.nextInt());

		HashMap<String, BSTNode<String>> nodeMap = new HashMap<>();
		nodeMap.put(data[0][0], new BSTNode<>(data[0][0]));
		for (int i = 0; i < data.length; i++) {
			BSTNode<String> parent = nodeMap.get(data[i][0]);

			BSTNode<String> leftChild = ".".equals(data[i][1]) ? null : new BSTNode<>(data[i][1]);
			nodeMap.put(data[i][1], leftChild);
			parent.setLeft(leftChild);

			BSTNode<String> rightChild = ".".equals(data[i][2]) ? null : new BSTNode<>(data[i][2]);
			nodeMap.put(data[i][2], rightChild);
			parent.setRight(rightChild);
		}

		BSTree<String> tree = new BSTree<>(nodeMap.get(data[0][0]));
		tree.frontOrderTraverse();
		tree.middleOrderTraverse();
		tree.backOrderTraverse();
		return "";
	}

	public static String[][] getData(Scanner sc, int length) {
		String[][] data = new String[length][3];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = sc.next();
			}
		}
		return data;
	}

	public static class BSTNode<T> {
		private T item;
		private BSTNode<T> left;
		private BSTNode<T> right;

		public BSTNode(T item) {
			this.item = item;
			left = null;
			right = null;
		}

		public void setLeft(BSTNode<T> left) {
			this.left = left;
		}

		public void setRight(BSTNode<T> right) {
			this.right = right;
		}
	}

	public static class BSTree<T extends Comparable<T>> {
		public BSTNode<T> root;

		public BSTree(BSTNode<T> root) {
			this.root = root;
		}

		public void frontOrderTraverse() {
			frontOrderTraverse(root);
			System.out.println();
		}

		private void frontOrderTraverse(BSTNode<T> tree) {
			if (tree != null) {
				System.out.print(tree.item);
				frontOrderTraverse(tree.left);
				frontOrderTraverse(tree.right);
			}
		}

		public void middleOrderTraverse() {
			middleOrderTraverse(root);
			System.out.println();
		}

		private void middleOrderTraverse(BSTNode<T> tree) {
			if (tree != null) {
				middleOrderTraverse(tree.left);
				System.out.print(tree.item);
				middleOrderTraverse(tree.right);
			}
		}

		public void backOrderTraverse() {
			backOrderTraverse(root);
			System.out.println();
		}

		private void backOrderTraverse(BSTNode<T> tree) {
			if (tree != null) {
				backOrderTraverse(tree.left);
				backOrderTraverse(tree.right);
				System.out.print(tree.item);
			}
		}
	}
}
