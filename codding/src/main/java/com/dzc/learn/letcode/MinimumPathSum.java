package com.dzc.learn.letcode;

/**
 * Description:
 * User: dzczyw
 * Date: 2019-01-13
 * Time: 14:15
 */
public class MinimumPathSum {

    public int minPathSum(int[][] grid) {

        if (grid.length == 0)
            return 0;

        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0) {
                } else if (i == 0)
                    grid[0][j] += grid[0][j - 1];
                else if (j == 0)
                    grid[i][0] += grid[i - 1][0];
                else
                    grid[i][j] = grid[i - 1][j] > grid[i][j - 1] ? grid[i][j] + grid[i][j - 1] : grid[i][j] + grid[i - 1][j];
            }
        }


        return grid[rows - 1][cols - 1];
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        MinimumPathSum minimumPathSum = new MinimumPathSum();
        minimumPathSum.minPathSum(grid);
    }
}
