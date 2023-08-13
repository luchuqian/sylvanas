package com.sylvanas.leetcode.medium;

/**
 * https://leetcode.cn/problems/word-search/?envType=study-plan-v2&envId=top-100-liked
 */
public class WordSearch {

    boolean found = false;

    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, word, 0);
                if (found) {
                    return true;
                }
            }
        }
        return false;
    }

    // 从 (i, j) 开始向四周搜索，试图匹配 word[p..]
    void dfs(char[][] board, int i, int j, String word, int p) {
        if (p == word.length()) {
            // 整个 word 已经被匹配完，找到了一个答案
            found = true;
            return;
        }
        if (found) {
            // 已经找到了一个答案，不用再搜索了
            return;
        }
        int m = board.length, n = board[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        if (board[i][j] != word.charAt(p)) {
            return;
        }

        // 已经匹配过的字符，我们给它添一个负号作为标记，避免走回头路
        board[i][j] = (char)(-board[i][j]);
        // word[p] 被 board[i][j] 匹配，开始向四周搜索 word[p+1..]
        dfs(board, i + 1, j, word, p + 1);
        dfs(board, i, j + 1, word, p + 1);
        dfs(board, i - 1, j, word, p + 1);
        dfs(board, i, j - 1, word, p + 1);
        board[i][j] = (char)(-board[i][j]);
    }

}
