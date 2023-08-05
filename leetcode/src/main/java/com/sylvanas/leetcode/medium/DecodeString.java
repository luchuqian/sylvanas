package com.sylvanas.leetcode.medium;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class DecodeString {
    private static Integer startIndex = 0;

    public static String decodeString(String s) {
        Stack<String> stack = new Stack<>();
        while (startIndex < s.length()) {
            char cur = s.charAt(startIndex);
            if (Character.isDigit(cur)) {
                String repeatNum = getDigital(s);
                stack.push(repeatNum);
            } else if (Character.isLetter(cur) || cur == '[') {
                stack.push(cur + "");
                startIndex++;
            } else {
                startIndex++;
                LinkedList<String> sub = new LinkedList<>();
                while (!"[".equals(stack.peek())) {
                    sub.addLast(stack.pop());
                }
                Collections.reverse(sub);
                stack.pop();
                int repeatNum = Integer.parseInt(stack.pop());

                StringBuilder subStr = new StringBuilder();
                String tempStr = getString(sub);
                while (repeatNum-- > 0) {
                    subStr.append(tempStr);
                }
                stack.push(subStr.toString());
            }
        }
        String result = "";
        while (!stack.isEmpty()) {
            result = stack.pop() + result;
        }
        return result;
    }

    public static String getDigital(String s) {
        StringBuilder sb = new StringBuilder();
        while (Character.isDigit(s.charAt(startIndex))) {
            sb.append(s.charAt(startIndex++));
        }
        return sb.toString();
    }

    public static String getString(LinkedList<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        System.out.println(decodeString("3[a2[c]]"));
    }
}
