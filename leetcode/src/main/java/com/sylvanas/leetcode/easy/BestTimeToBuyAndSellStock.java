package com.sylvanas.leetcode.easy;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/?envType=study-plan-v2&envId=top-100-liked
 */
public class BestTimeToBuyAndSellStock {



    public int maxProfit(int[] prices) {
        if(prices==null||prices.length==0){
            return 0;
        }
        int min = prices[0];
        int max = 0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]<min){
                min = prices[i];
            }
            max = Math.max(max,prices[i]-min);
        }
        return max;
    }

}
