package com.dzc.learn.letcode;

public class UniquePathsII {


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0)
            return 0;

        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 如果是障碍物 , 置 0
                if (obstacleGrid[i][j] == 1)
                    obstacleGrid[i][j] = 0;
                else if (i == 0 && j == 0)
                    obstacleGrid[i][j] = 1;
                else if (i == 0)
                    obstacleGrid[0][j] = obstacleGrid[0][j - 1] * 1; // 当前行, 是否可以到达左边的块；
                else if (j == 0)
                    obstacleGrid[i][0] = obstacleGrid[i - 1][0] * 1; // 当前列, 是否可以到达上方的块
                else
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
            }
        }
        return obstacleGrid[row - 1][col - 1];
    }


    public static void main(String[] args) {
        UniquePathsII uniquePathsII = new UniquePathsII();
        System.out.println(uniquePathsII.uniquePathsWithObstacles(new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}}));

    }
}
