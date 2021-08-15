package TestQuestions;
//import java.util.*;
//// A Java solution for longest palindrome
public class Polindrom {
    //
//        // Function to print a subString str[low..high]
//        static void printSubStr(String str, int low, int high)
//        {
//            for (int i = low; i <= high; ++i)
//                System.out.print(str.charAt(i));
//        }
//
//        // This function prints the
//// longest palindrome subString
//// It also returns the length
//// of the longest palindrome
//        static int longestPalSubstr(String str)
//        {
//            // get length of input String
//            int n = str.length();
//
//            // All subStrings of length 1
//            // are palindromes
//            int maxLength = 1, start = 0;
//
//            // Nested loop to mark start and end index
//            for (int i = 0; i < str.length(); i++) {
//                for (int j = i; j < str.length(); j++) {
//                    int flag = 1;
//
//                    // Check palindrome
//                    for (int k = 0; k < (j - i + 1) / 2; k++)
//                        if (str.charAt(i + k) != str.charAt(j - k))
//                            flag = 0;
//
//                    // Palindrome
//                    if (flag!=0 && (j - i + 1) > maxLength) {
//                        start = i;
//                        maxLength = j - i + 1;
//                    }
//                }
//            }
//
//            System.out.print("Longest palindrome subString is: ");
//            printSubStr(str, start, start + maxLength - 1);
//
//            // return length of LPS
//            return maxLength;
//        }
//
//        // Driver Code
//        public static void main(String[] args)
//        {
//            String str = "alfalfa";
//            System.out.print("\nLength is: "
//                    + longestPalSubstr(str));
//        }
//    }

    // test question - polindrom - read a string from the end to the start and this is a same original string
    public static String lps(String s) {
        if (s.length() == 0) {
            return "nothing to see";
        }
        String s2 = transpos(s);
        return lcs(s, s2);


    }

    private static String transpos(String s) {
        int n = s.length();
        String s2 = "";
        for (int i = n - 1; i >= 0; i--) {
            s2 += s.charAt(i);

        }
        return s2;

    }

    public static String lcs(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] mat = new int[n + 1][m + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    mat[i][j] = 1 + mat[i - 1][j - 1];
                } else {
                    mat[i][j] = Math.max(mat[i - 1][j], mat[i][j - 1]);
                }
            }
        }
        String ans = "";
        int i = n;
        int j = m;
        while (mat[i][j] != 0) {

            if (mat[i - 1][j] == mat[i][j - 1]) {
                ans = s1.charAt(i - 1) + ans;
                i--;
                j--;
            } else if (mat[i - 1][j] >= mat[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return ans;


    }
}