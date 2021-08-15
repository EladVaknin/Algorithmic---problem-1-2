package LCS;
//
public class LcsShortesString {

    public static String longestContains(String s1,String s2){
        String lcs = lcs(s1,s2);
        String ans = "";
        int i = 0 , j = 0 , k = 0;
        while(k < lcs.length() && i<s1.length() && j<s2.length()){
            if(s1.charAt(i)==s2.charAt(j)&&s2.charAt(j) == lcs.charAt(k)){
                ans+= s1.charAt(i);
                i++;
                j++;
                k++;
            }
            else{
                if(s1.charAt(i) == lcs.charAt(k)){
                    ans += s2.charAt(j++);

                }
                else{
                    if(s2.charAt(j) == lcs.charAt(k)){
                        ans += s1.charAt(i++);
                    }
                    else{
                        ans += s1.charAt(i++);
                        ans += s2.charAt(j++);}

                }
            }
        }

        while(i < s1.length()){
            ans += s1.charAt(i++);
        }
        while(j < s2.length()){
            ans+= s2.charAt(j++);
        }
        return ans;
    }

    public static String lcs(String s1, String s2) {
        // build matrix
        int n = s1.length();
        int m = s2.length();
        int[][] f = new int[n+1][m+1]; // incloud 0 place in the string
        //init the matrix
        for (int i = 0; i < n+1; i++) {f[i][0] = 0;}
        for (int j = 0; j < m+1; j++) {f[0][j] = 0;}
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) f[i][j] = 1 + f[i-1][j-1]; // if we have a mach
                else f[i][j] = Math.max(f[i][j-1], f[i-1][j]);
            }
        }
        // build String ans - and i start from the end
        int i = n, j = m;
        String ans = "";
        // f[i][j] = the lenght of the string that i found
        while(f[i][j] != 0) {
            if(s1.charAt(i-1) == s2.charAt(j-1)) { // i have a mach
                ans = s1.charAt(i-1) + ans;
                i--; j--;
            }
            else {
                if(f[i][j-1] > f[i-1][j]) j--;
                else i--;
            }
        }
        return ans;
    }
}
