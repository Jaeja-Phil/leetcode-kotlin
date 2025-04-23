package medium

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell
 * one share of the stock multiple times) with the following restrictions:
 * - After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * Constraints:
 * - 1 <= prices.length <= 5000
 * - 0 <= prices[i] <= 1000
 *
 * Example 1:
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 *
 * Example 2:
 * Input: prices = [1]
 * Output: 0
 */
fun main() {
    fun maxProfit(prices: IntArray): Int {
        val n = prices.size
        if (n == 1) return 0

        // dp[i][0] = max profit on day i with no stock
        // dp[i][1] = max profit on day i with stock
        val dp = Array(n) { IntArray(2) }
        dp[0][0] = 0 // day 0, no stock: 0 profit
        dp[0][1] = -prices[0] // day 0, buy stock: profit negative cost
        dp[1][0] = maxOf(dp[0][0], dp[0][1] + prices[1]) // do nothing, or sell stock today
        dp[1][1] = maxOf(dp[0][1], dp[0][0] - prices[1]) // do nothing, or buy stock today

        for (i in 2..<n) {
            dp[i][0] = maxOf(
                // do nothing
                dp[i - 1][0],
                // sell stock today
                dp[i - 1][1] + prices[i]
            )

            dp[i][1] = maxOf(
                // do nothing
                dp[i - 1][1],
                // buy stock today with cooldown (hence i - 2)
                dp[i - 2][0] - prices[i]
            )
        }

        // The maximum profit on the last day with no stock
        return dp[n - 1][0]
    }

    println(maxProfit(intArrayOf(1, 2, 3, 0, 2))) // 3
    println(maxProfit(intArrayOf(1))) // 0
}
