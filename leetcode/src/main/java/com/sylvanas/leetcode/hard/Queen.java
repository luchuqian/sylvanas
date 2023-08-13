package com.sylvanas.leetcode.hard;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Queen {
  List<List<String>> res = new ArrayList<>();

  /**
   * 输入棋盘边长 n，返回所有合法的放置
   */
  public List<List<String>> solveNQueens(int n) {
    // '.' 表示空，'Q' 表示皇后，初始化空棋盘。
    List<String> board = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      sb.append('.');
    }
    for (int i = 0; i < n; i++) {
      board.add(sb.toString());
    }
    backtrack(board, 0);
    return res;
  }

  /**
   * *路径：board 中小于 row 的那些行都已经成功放置了皇后
   * *选择列表：第 row 行的所有列都是放置皇后的选择
   * *结束条件：row 超过 board 的最后一行
   */
  private void backtrack(List<String> board, int row) {
    if (row == board.size()) {
      res.add(new ArrayList<>(board));
      return;
    }

    int n = board.get(row).length();
    for (int col = 0; col < n; col++) {
      // 排除不合法选择
      if (!isValid(board, row, col)) {



        continue;
      }
      // 做选择
      char[] arr = board.get(row).toCharArray();
      arr[col] = 'Q';
      board.set(row, String.valueOf(arr));
      // 进入下一行决策
      backtrack(board, row + 1);
      // 撤销选择
      arr[col] = '.';
      board.set(row, String.valueOf(arr));
    }
  }

  /* 是否可以在 board[row][col] 放置皇后？*/
  private boolean isValid(List<String> board, int row, int col) {
    int n = board.size();

    // 检查列是否有皇后互相冲突
    for (int i = 0; i <= row; i++) {
      if (board.get(i).charAt(col) == 'Q') {
        return false;
      }
    }

    // 检查右上方是否有皇后互相冲突
    for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
      if (board.get(i).charAt(j) == 'Q') {
        return false;
      }
    }

    // 检查左上方是否有皇后互相冲突
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
      if (board.get(i).charAt(j) == 'Q') {
        return false;
      }
    }

    return true;
  }

}
