package medium

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee
 * representing a transaction fee.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay
 * the transaction fee for each transaction.
 *
 * Note:
 * - You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * - The transaction fee is only charged once for each stock purchase and sale.
 *
 * Constraints:
 * - 1 <= prices.length <= 5 * 10^4
 * - 1 <= prices[i] <= 5 * 10^4
 * - 0 <= fee <= 5 * 10^4
 *
 * Example 1:
 * Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * - Buying at prices[0] = 1
 * - Selling at prices[3] = 8
 * - Buying at prices[4] = 4
 * - Selling at prices[5] = 9
 * - The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 *
 * Example 2:
 * Input: prices = [1, 3, 7, 5, 10, 3], fee = 3
 * Output: 6
 * Explanation: The maximum profit can be achieved by:
 * - Buying at prices[0] = 1
 * - Selling at prices[4] = 10
 * - The total profit is ((10 - 1) - 3) = 6.
 */
fun main() {
    fun maxProfit(prices: IntArray, fee: Int): Int {
        // base case
        if (prices.size <= 1) return 0
        if (prices.size == 2) return maxOf(0, prices[1] - prices[0] - fee)

        /**
         * we can perform one of 3 actions on each day:
         * 1. buy a stock
         * 2. sell a stock
         * 3. do nothing
         *
         * profit depends on our operation + max profit from the previous day.
         * use the sub-problem of day "i - 1" to solve the problem of day "i".
         * - which means we are eligible of using DP
         *
         * we need 2 sets of dp arrays:
         * 1. free: represents the maximum profit we can make if we do not have a stock on day "i"
         * 2. hold: represents the maximum profit we can make if we have a stock on day "i"
         *
         * as we iterate free...
         * - max profit achievable on day "i" as free is the max of
         *   - the previous day's free and
         *   - the profit we make if we sell the stock on day "i" (hold[i - 1] + prices[i] - fee)
         * as we iterate hold...
         * - max profit achievable on day "i" as hold is the max of
         *   - the previous day's hold and
         *   - the profit we make if we buy the stock on day "i" (free[i - 1] - prices[i])
         *
         * finally return the last element of the free array (since we should not have any stock on the last day)
         */

        val free = IntArray(prices.size)
        val hold = IntArray(prices.size)

        hold[0] = -prices[0] // buy the first stock
        for (i in 1 ..< prices.size) {
            free[i] = maxOf(free[i - 1], hold[i - 1] + prices[i] - fee)
            hold[i] = maxOf(hold[i - 1], free[i - 1] - prices[i])
        }

        return free.last()
    }

    println(maxProfit(intArrayOf(1, 3, 2, 8, 4, 9), 2)) // 8
    println(maxProfit(intArrayOf(1, 3, 7, 5, 10, 3), 3)) // 6
}