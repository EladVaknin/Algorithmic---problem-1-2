package Fibo;

public class Fibo {
    private static final int[][] F = {{1,1},{1,0}};

    /**
     * returns the nth element in fibonacci series
     * Complexity: O(log n)
     */
    public static int fibo(int n) {
        int[][] ans = F;
        int[][] A = F;
        while(n != 0) {
            if(n % 2 == 1) ans = mulMat(ans,A);
            A = mulMat(A,A);
            n /=2;
        }
        return ans[1][1];
    }

    /**
     * multiple two matrix 2x2
     * Complexity: O(1)
     */
    public static int[][] mulMat(int[][] m1, int[][] m2) {
        int[][] ans = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                ans[i][j] = m1[i][0]*m2[0][j] + m1[i][1]*m2[1][j];
            }
        }
        return ans;
    }

    /**
     * returns the nth element in fibonacci series - recursion
     * Complexity: O(log n)
     */
    public static int fiboRec(int n) {
        return fiboRec(F,n)[1][1];
    }
    public static int[][] fiboRec(int[][] A,int n) {
        if(n == 0) return F;
        if(n % 2 == 1) return mulMat(fiboRec(mulMat(A,A),n/2),A);
        return fiboRec(mulMat(A,A),n/2);
    }
}