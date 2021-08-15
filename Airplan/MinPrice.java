package MiniPrice;

import java.awt.Point;

class Node {
	int x, y, price, numOfPaths;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}


public class MinPrice {

	public static int minPriceSimple (Node[][] mat) {   // to return the price.
		int n = mat.length, m = mat[0].length;  //
		mat[0][0].price = 0;    // start condions
		for (int i = 1; i < n; i++) {
			mat[i][0].price = mat[i - 1][0].price + mat[i - 1][0].y;
		}      // the function
		for (int i = 1; i < m; i++) {
			mat[0][i].price = mat[0][i - 1].price + mat[0][i - 1].x;
		}          // init the amodot
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				mat[i][j].price = Math.min(mat[i - 1][j].price + mat[i - 1][j].y, mat[i][j - 1].price + mat[i][j - 1].x);
			}
		}
		return mat [n-1][m-1].price;
	}

	// if i want to retuen the path i will use this code and i will return the path


//			return path(mat);
//}
//
//	private static String path(Node[][] mat) {
//		int i = mat.length-1, j = mat[0].length-1; // init
//		String ans = "";
//		while(i != 0 && j != 0) {  // the condion to come back
//			if(mat[i-1][j].price + mat[i-1][j].y < mat[i][j-1].price + mat[i][j-1].x) {   // from where i comiong
//				ans = "1" + ans;
//				i--;
//			}
//			else {
//				ans = "0" + ans;
//				j--;
//			}
//		}
//		while(i != 0) {
//			ans = "1" + ans;
//			i--;
//		}
//		while(j != 0) {
//			ans = "0" + ans;
//			j--;
//		}
//		return ans;
//	}

     // to return the way if p1 ,p2 on the ways or the return the way
	public static String minPrice(Node[][] mat) {    // to return the way
		int n = mat.length, m = mat[0].length;  //
		mat[0][0].price = 0;    // start condions
		mat[0][0].numOfPaths = 1;
		for (int i = 1; i < n; i++) {
			mat[i][0].price = mat[i-1][0].price + mat[i-1][0].y; mat[i][0].numOfPaths = 1;}      // the function
		for (int i = 1; i < m; i++) {
			mat[0][i].price = mat[0][i-1].price + mat[0][i-1].x; mat[0][i].numOfPaths = 1;}          // init the amodot
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				int fromDown = mat[i-1][j].price + mat[i-1][j].y;
				int fromLeft = mat[i][j-1].price + mat[i][j-1].x;
				mat[i][j].price = Math.min(fromDown,fromLeft);
				if(fromDown < fromLeft) {mat[i][j].numOfPaths = mat[i-1][j].numOfPaths;}
				else if(fromDown > fromLeft) {mat[i][j].numOfPaths = mat[i][j-1].numOfPaths;}
				else mat[i][j].numOfPaths = mat[i-1][j].numOfPaths + mat[i][j-1].numOfPaths;
			}
		}
		return path(mat);
	}
	
	private static String path(Node[][] mat) {
		int i = mat.length-1, j = mat[0].length-1; // init
		String ans = "";
		while(i != 0 && j != 0) {  // the condion to come back
			if(mat[i-1][j].price + mat[i-1][j].y < mat[i][j-1].price + mat[i][j-1].x) {   // from where i comiong
				ans = "1" + ans;
				i--;
			}
			else {
				ans = "0" + ans;
				j--;
			}
		}
		while(i != 0) {
			ans = "1" + ans;
			i--;
		}
		while(j != 0) {
			ans = "0" + ans;
			j--;
		}
		return ans;
	}
    // test q - if the points is on the short way.
	public static int minPriceBetween(Node[][] mat, Point p1, Point p2) {
		int n = p2.y - p1.y + 1, m = p2.x - p1.x + 1;  //the lenght of the matrix is the difference between the lines.
		mat[p1.y][p1.x].price = 0;
		for (int i = p1.y + 1; i < p1.y + n; i++) {
			mat[i][p1.x].price = mat[i-1][p1.x].price + mat[i-1][p1.x].y;}
		for (int i = p1.x + 1; i < p1.x + m; i++) {
			mat[p1.y][i].price = mat[p1.y][i-1].price + mat[p1.y][i-1].x;}
		for (int i = p1.y + 1; i < p1.y + n; i++) {
			for (int j = p1.x + 1; j < p1.x + m; j++) {
				mat[i][j].price = Math.min(mat[i-1][j].price + mat[i-1][j].y, mat[i][j-1].price + mat[i][j-1].x);
			}
		}
		return mat[p2.y][p2.x].price;
	}
	// the main fucnion
	public static boolean isOnMinPath(Node[][] mat, Point p1, Point p2) {
		if(p2.x <= p1.x && p2.y <= p1.y) {  // that say p2 is the firs point
			Point t = p1; //swap
			p1 = p2;
			p2 = t;
		}
		if(p1.x <= p2.x && p1.y <= p2.y) {
			int allPrice = minPriceBetween(mat, new Point(0,0), new Point(mat.length-1,mat[0].length-1));  // thats the pach
			int toP1 = minPriceBetween(mat, new Point(0,0), p1); // from (0,0) to p1
			int p1toP2 = minPriceBetween(mat, p1, p2); // from p1 to p2
			int p2to = minPriceBetween(mat, p2, new Point(mat.length-1,mat[0].length-1)); // from p2 to the end
			if(allPrice == toP1 + p1toP2 + p2to) return true;  // if the pach = to the way between (0,0) to p1 + p1 to p2 + p2 to the end
			else return false;
		}
		else return false;
	}
	
	public static void main(String[] args) {
		Node[][] mat = {
			{new Node(1,5),new Node(4,1),new Node(0,6)},
			{new Node(4,7),new Node(2,5),new Node(0,3)},
			{new Node(1,0),new Node(2,0),new Node(0,0)}};
		System.out.println(isOnMinPath(mat,new Point(1,1),new Point(1,2)));
	}
}
