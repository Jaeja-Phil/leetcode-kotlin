package medium

/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 *
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any
 * time. However, you can buy it then immediately sell it on the same day.
 *
 * Find and return the maximum profit you can achieve.
 *
 * Constraints:
 * - 1 <= prices.length <= 3 * 10^4
 * - 0 <= prices[i] <= 10^4
 *
 * Example 1:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation:
 * - Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * - Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * - Total profit is 4 + 3 = 7.
 *
 * Example 2:
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation:
 * - Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * - Total profit is 4.
 *
 * Example 3:
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation:
 * - In this case, no transactions are done and the max profit = 0.
 */
fun main() {
    fun maxProfit(prices: IntArray): Int {
        // Solution 1. Greedy
        // whenever profit is positive, add it to the total profit
//        var profit = 0
//        for (i in 1 .. prices.lastIndex) {
//            if (prices[i] > prices[i - 1]) {
//                profit += prices[i] - prices[i - 1]
//            }
//        }
//        return profit

        // Solution 2. Dynamic Programming
        // 0: max profit when holding stock, 1: max profit when not holding stock
        val dp = Array(prices.size) { IntArray(2) }

        dp[0][0] = -prices[0] // buy on day 0
        dp[0][1] = 0 // since we haven't sold anything yet, profit is 0

        for (i in 1 .. prices.lastIndex) {
            dp[i][0] = maxOf(
                // hold the stock from previous day
                dp[i - 1][0],
                // buy the stock today
                dp[i - 1][1] - prices[i]
            )
            dp[i][1] = maxOf(
                // do nothing from previous day (keep not holding stock)
                dp[i - 1][1],
                // sell the stock today
                dp[i - 1][0] + prices[i]
            )
        }
        // max profit when not holding stock on the last day
        return dp[prices.lastIndex][1]
    }

    println(maxProfit(intArrayOf(7, 1, 5, 3, 6, 4))) // 7
    println(maxProfit(intArrayOf(1, 2, 3, 4, 5))) // 4
    println(maxProfit(intArrayOf(7, 6, 4, 3, 1))) // 0
}
