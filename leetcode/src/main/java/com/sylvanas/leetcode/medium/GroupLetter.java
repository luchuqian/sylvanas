package com.sylvanas.leetcode.medium;


import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.cn/problems/group-anagrams/?envType=study-plan-v2&envId=top-100-liked
 */
public class GroupLetter {

    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return Collections.emptyList();
        }

        return new ArrayList<>(
                Arrays.stream(strs)
                        .collect(Collectors.groupingBy(str -> {
                            char[] letterArr = str.toCharArray();
                            Arrays.sort(letterArr);
                            return String.valueOf(letterArr);
                        }))
                        .values()
        );
    }


    public static void main(String[] args) {
        List<List<String>> result = groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        result.forEach(list -> {
            list.forEach(str -> System.out.print(str + " "));
            System.out.println();
        });
    }

}
