package LIS;

import java.util.Arrays;

public class LIS {
	// to find the size of seires
	public static int lisSize(int[] arr) { // only size - O(nlog(n)) - binar search
		int n = arr.length;
		int[] lis = new int[n];
		lis[0] = arr[0]; // init
		int k = 1;
		for (int i = 1; i < n; i++) {
			int index = Arrays.binarySearch(lis, 0, k, arr[i]);    // O(nlog(n))
			if(index < 0) index = -index - 1; // fix java's results
			if(index == k) k++;
			lis[index] = arr[i];
		}
		System.out.println(Arrays.toString(lis));
		return k;
	}
	// lis for return the siears
	public static int[] lis(int[] arr) { // O(n^2)
		int n = arr.length;
		int[] lis = new int[n];
		int[][] mat = new int[n][n];
		lis[0] = mat[0][0] = arr[0];  // to save the alachson in list
		int k = 1;
		for (int i = 1; i < n; i++) {
			int index = Arrays.binarySearch(lis, 0, k, arr[i]);
			if(index < 0) index = -index - 1; // fix java's results
			if(index == k) k++;
			lis[index] = mat[index][index] = arr[i];
			for (int j = 0; j < index; j++) {     // copy the alachson to next line
				mat[index][j] = mat[index-1][j];
			}
		}
		return Arrays.copyOf(mat[k-1], k);
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(lis(new int[] {5,2,9,7,8,5,3,2,1,10,6})));
		System.out.println(Arrays.toString(lis(new int[] {1,9,10,5,6})));
	}
}
