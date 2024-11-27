package org.example.other;

/**
 * We stack glasses in a pyramid, where the first row has 1 glass, the second
 * row has 2 glasses, and so on until the 100th row. Each glass holds one cup
 * of champagne.
 */
public class ChampagneTower {

        public double champagneTower(int poured, int query_row, int query_glass) {
            double[][] dp = new double[101][101];
            dp[0][0] = poured;
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j <= i; j++) {
                    if (dp[i][j] > 1) {
                        dp[i + 1][j] += (dp[i][j] - 1) / 2.0;
                        dp[i + 1][j + 1] += (dp[i][j] - 1) / 2.0;
                        dp[i][j] = 1;
                    }
                }
            }
            return dp[query_row][query_glass];
        }

        public static void main(String[] args) {
            ChampagneTower champagneTower = new ChampagneTower();
            System.out.println(champagneTower.champagneTower(1, 1, 1));
            System.out.println(champagneTower.champagneTower(2, 1, 1));
            System.out.println(champagneTower.champagneTower(100000009, 33, 17));
        }
}
