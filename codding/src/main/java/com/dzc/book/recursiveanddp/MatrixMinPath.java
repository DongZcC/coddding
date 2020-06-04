package com.dzc.book.recursiveanddp;

/**
 * @author Administrator
 * @date 2020-06-04 10:56
 * <p>
 * <p>
 * 矩阵的最小路径和
 * <p>
 * 给定一个矩阵 m 从左上角开始每次只能向右或者向下走， 最后到达右下角的位置。 路径上的所有数字累加起来就是路径和。 返回所有路径中最小的路径和
 */
public class MatrixMinPath {


    /**
     * 这个肯定是个dp 动态规划
     * <p>
     * 就到达右下角前一个， 要不就是 上边的一个节点， 要不就是左边的一个节点， 两个节点取个小
     * <p>
     * 增加到节点上， 最后就是一个最小和
     *
     * @param matrix
     * @return
     */
    public int minPath1(int[][] matrix) {
        if (matrix == null) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];

        dp[0][0] = matrix[0][0];
        // 初始化
        for (int i = 1; i < m; i++) {
            dp[0][i] += matrix[0][i] + dp[0][i - 1];
        }

        for (int i = 1; i < n; i++) {
            dp[i][0] = matrix[i][0] + dp[i - 1][0];
        }


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * 压缩指针的方式来做 ， 把二维数组压缩成一维
     *
     * @param matrix
     * @return
     */
    public int minPath2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int more = Math.max(matrix.length, matrix[0].length);
        int less = Math.min(matrix.length, matrix[0].length);
        boolean rowmore = more == matrix.length;
        int[] arr = new int[less];
        arr[0] = matrix[0][0];
        for (int i = 1; i < less; i++) {
            arr[i] = arr[i - 1] + (rowmore ? matrix[0][i] : matrix[i][0]);
        }

        for (int i = 1; i < more; i++) {
            arr[0] = arr[0] + (rowmore ? matrix[0][i] : matrix[i][0]);
            for (int j = 1; j < less; j++) {
                arr[j] = Math.min(arr[j - 1], arr[j]) + (rowmore ? matrix[i][j] : matrix[j][i]);
            }
        }
        return arr[less - 1];
    }

    public static void main(String[] args) {
        MatrixMinPath min = new MatrixMinPath();
        int i = min.minPath2(new int[][]{
                {1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}
        });

        System.out.println(i);
    }
}
