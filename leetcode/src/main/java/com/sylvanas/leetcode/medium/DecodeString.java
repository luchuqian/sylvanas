package com.sylvanas.leetcode.medium;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class DecodeString {

    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        Stack<Integer> factorStack = new Stack<>();
        Stack<String> eleStack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c == '[') {
                factorStack.push(multi);
                eleStack.push(res.toString());
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int curTop = factorStack.pop();
                for (int i = 0; i < curTop; i++) {
                    tmp.append(res);
                }
                res = new StringBuilder(eleStack.pop() + tmp);
            } else if (c >= '0' && c <= '9') {
                multi = multi * 10 + Integer.parseInt(c + "");
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    public String decodeString2(String s) {
        return dfs(s, 0)[0];
    }

    private String[] dfs(String s, int index) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        while (index < s.length()) {
            if (s.charAt(index) >= '0' && s.charAt(index) <= '9'){
                multi = multi * 10 + Integer.parseInt(String.valueOf(s.charAt(index)));
            }
            else if (s.charAt(index) == '[') {
                String[] tmp = dfs(s, index + 1);
                index = Integer.parseInt(tmp[0]);
                while (multi > 0) {
                    res.append(tmp[1]);
                    multi--;
                }
            } else if (s.charAt(index) == ']') {
                return new String[]{String.valueOf(index), res.toString()};
            } else {
                res.append(s.charAt(index));
            }
            index++;
        }
        return new String[]{res.toString()};
    }


}
