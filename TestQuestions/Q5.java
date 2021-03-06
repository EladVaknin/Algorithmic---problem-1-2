package TestQuestions;

import java.awt.Point;

class DoubleNode {
	double price,x,y;
	
	public DoubleNode(double x, double y) {
		this.x = x;
		this.y = y;
	}
}
public class Q5 {
	
	public static int minPriceWuthDeadArea(Node[][] mat, Point p1, Point p2) {
		DoubleNode[][] mat2 = createNewMatrix(mat, p1, p2);
		return minPrice(mat2);
	}
	
	private static DoubleNode[][] createNewMatrix(Node[][] mat, Point p1, Point p2) {
		int n = mat.length;
		int m = mat[0].length;
		DoubleNode[][] newMat = new DoubleNode[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(i >= p1.y && i <= p2.y && j >=p1.x && j <= p2.x) {
					newMat[i][j] = new DoubleNode(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
				}
				else {
					newMat[i][j] = new DoubleNode(mat[i][j].x, mat[i][j].y);
				}
			}
		}
		return newMat;
	}

	public static int minPrice(DoubleNode[][] mat) {
		int n = mat.length, m = mat[0].length;
		mat[0][0].price = 0;
		for (int i = 1; i < n; i++) { mat[i][0].price = mat[i-1][0].price + mat[i-1][0].y;}
		for (int i = 1; i < m; i++) { mat[0][i].price = mat[0][i-1].price + mat[0][i-1].x;}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				mat[i][j].price = Math.min(mat[i-1][j].price + mat[i-1][j].y, mat[i][j-1].price + mat[i][j-1].x);
			}
		}
		if(mat[n-1][m-1].price == Double.POSITIVE_INFINITY) return -1;
		return (int) mat[n-1][m-1].price;
	}
	
	public static void main(String[] args) {
		Node[][] mat = {
				{new Node(1,5),new Node(4,1),new Node(0,6)},
				{new Node(4,7),new Node(2,5),new Node(0,3)},
				{new Node(1,0),new Node(2,0),new Node(0,0)}};
			System.out.println(minPriceWuthDeadArea(mat,new Point(0,0),new Point(2,1)));
	}
}
