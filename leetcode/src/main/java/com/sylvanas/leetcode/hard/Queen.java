package com.sylvanas.leetcode.hard;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Queen {

    public static List<List<String>> solveNQueens(int n) {
        String[][] board = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = ".";
            }
        }
        List<List<String>> resultBoardList = new ArrayList<>();
        backtrack(resultBoardList, board, 0);
        return resultBoardList;
    }

    public static void backtrack(List<List<String>> resultBoardList, String[][] board, int row) {
        if (row == board.length) {
            resultBoardList.add(Arrays.stream(board)
                    .map(rowArr -> {
                        StringJoiner result = new StringJoiner("");
                        for (String str : rowArr) {
                            result.add(str);
                        }
                        return result.toString();
                    })
                    .collect(Collectors.toList()));
            return;
        }
        int n = board[row].length;
        for (int col = 0; col < n; col++) {
            if (isNotValid(board, row, col)) {
                continue;
            }
            board[row][col] = "Q";
            backtrack(resultBoardList, board, row + 1);
            board[row][col] = ".";
        }
    }

    /**
     * 一行一行放 只检查 左上 上 右上
     *
     * @param board 棋盘
     * @param row   行
     * @param col   列
     * @return 是否可以board[row][col]放置皇后
     */
    public static boolean isNotValid(String[][] board, int row, int col) {
        int n = board[row].length;
        // 检查列是否有皇后互相冲突
        for (int i = 0; i <= row; i++) {
            if ("Q".equals(board[i][col])) {
                return true;
            }
        }
        // 检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if ("Q".equals(board[i][j])) {
                return true;
            }
        }
        // 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if ("Q".equals(board[i][j])) {
                return true;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        solveNQueens(4);
    }

}
