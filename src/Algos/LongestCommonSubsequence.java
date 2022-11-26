package Algos;

public class LongestCommonSubsequence {
    public LongestCommonSubsequence() {}

    // Returns the length of the subsequence
    public int algorithm(String s1, String s2) {
        // Create a 2 dimensional dp store

        // The +1 is for the base case (when there's no chars left, 
        //  we want to return a 0)
        // Note: Initialised arrays are default 0
        int[][] store = new int[s1.length() + 1][s2.length() + 1];

        // We want to do a bottom up approach
        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s2.length() - 1; j >= 0; j--) {
                
                // If the 2 chars are equal, we compare the subsequence of
                //  the rest of the word for both words
                if (s1.charAt(i) == s2.charAt(j)) {
                    store[i][j] = 1 + store[i+1][j+1];
                } else {
                    // If 2 chars are not equal, we compare the subsequence
                    //  of words removing 1 letter from first word, and another 
                    //  removing the 1st letter from the second word
                    store[i][j] = Integer.min(store[i+1][j], store[i][j+1]);
                }
            }
        }

        return store[0][0];
    }
  
}