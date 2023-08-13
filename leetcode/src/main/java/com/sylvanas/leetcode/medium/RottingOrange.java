package com.sylvanas.leetcode.medium;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/rotting-oranges/?envType=study-plan-v2&envId=top-100-liked
 */
public class RottingOrange {


    public int orangesRotting(int[][] grid) {
        if (grid == null) {
            return -1;
        }
        int count = 0;

        Queue<Coordinate> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new Coordinate(i, j, 2));
                } else if (grid[i][j] == 1) {
                    count++;
                }
            }
        }

        return bfs(grid, queue, count);
    }

    private int bfs(int[][] grid, Queue<Coordinate> queue, int count) {
        if (queue.isEmpty() && count == 0) {
            return 0;
        }
        if (queue.isEmpty() && count > 0) {
            return -1;
        }
        int minute = 0;
        while (count > 0 && !queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Coordinate coordinate = queue.poll();
                int row = coordinate.row;
                int col = coordinate.col;
                if (row - 1 >= 0 && grid[row - 1][col] == 1) {
                    count--;
                    grid[row - 1][col] = 2;
                    queue.offer(new Coordinate(row - 1, col, 2));
                }
                if (row + 1 < grid.length && grid[row + 1][col] == 1) {
                    count--;
                    grid[row + 1][col] = 2;
                    queue.offer(new Coordinate(row + 1, col, 2));
                }
                if (col - 1 >= 0 && grid[row][col - 1] == 1) {
                    count--;
                    grid[row][col - 1] = 2;
                    queue.offer(new Coordinate(row, col - 1, 2));
                }
                if (col + 1 < grid[row].length && grid[row][col + 1] == 1) {
                    count--;
                    grid[row][col + 1] = 2;
                    queue.offer(new Coordinate(row, col + 1, 2));
                }
            }
            minute++;
        }
        if (count > 0) {
            return -1;
        }
        return minute;
    }


    static class Coordinate {
        public int row;
        public int col;
        public int num;

        public Coordinate(int row, int col, int num) {
            this.row = row;
            this.col = col;
            this.num = num;
        }
    }

    public static void main(String[] args) {
        new RottingOrange().orangesRotting(new int[][]{{1}, {2}});
    }


}
