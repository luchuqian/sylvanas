package com.sylvanas.leetcode.medium;

/**
 * https://leetcode.cn/problems/number-of-islands/?envType=study-plan-v2&envId=top-100-liked
 */
public class Islands {
    int count;

    public int numIslands(char[][] grid) {
        if (grid == null) {
            return 0;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }


    public void dfs(char[][] grid, int row, int col) {
        if (row < 0 || col < 0 || row == grid.length || col == grid[0].length) {
            return;
        }

        if (grid[row][col] == '0') {
            return;
        }
        grid[row][col] = '0';


        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);

    }
}
