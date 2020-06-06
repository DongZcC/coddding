package com.dzc.book.recursiveanddp;

/**
 * @author Administrator
 * @date 2020-06-06 14:24
 * <p>
 * 龙与地下城游戏问题
 * <p>
 * 给定一个二维数组 map ， 定义是一个地图，
 * <p>
 * 骑士从左上角出发， 妹子只能向右或者向下走， 最后到达右下角见到公主
 * 如果是负数则证明需要 掉血， 如果是正数 ， 可以回血
 * <p>
 * 求初始血量最少为多少
 */
public class DragonGame {


    public int knight(int[][] map) {
        if (map == null || map.length == 0) {
            return -1;
        }

        int m = map.length;
        int n = map[0].length;
        for (int i = 1; i < m; i++) {
            map[0][i] += map[0][i - 1];
        }

        for (int j = 1; j < n; j++) {
            map[j][0] += map[j - 1][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                map[i][j] += Math.min(map[i - 1][j], map[i][j - 1]);
            }
        }

        if (map[m - 1][n - 1] > 0) {
            return 1;
        } else {
            return -map[m - 1][n - 1];
        }
    }
}
