package easy

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the
 * future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 * Constraints:
 * - 1 <= prices.length <= 10^5
 * - 0 <= prices[i] <= 10^4
 *
 * Example 1:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 *
 * Example 2:
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 */
fun main() {
    fun maxProfit(prices: IntArray): Int {
        // Solution 1.
//        var minPrice = prices[0]
//        var maxProfit = 0
//        for (i in 1 .. prices.lastIndex) {
//            val currPrice = prices[i]
//            if (currPrice > minPrice) {
//                maxProfit = maxOf(maxProfit, currPrice - minPrice)
//            } else {
//                minPrice = minOf(minPrice, currPrice)
//            }
//        }
//
//        return maxProfit

        // Solution 2.
        var buy = 0
        var sell = 1
        var maxProfit = 0
        while (sell < prices.size) {
            if (prices[sell] > prices[buy]) {
                maxProfit = maxOf(maxProfit, prices[sell] - prices[buy])
            } else {
                // If the current price is less than the buy price, update when to buy.
                buy = sell
            }

            sell++
        }

        return maxProfit
    }

    val prices1 = intArrayOf(7, 1, 5, 3, 6, 4)
    println(maxProfit(prices1)) // 5

    val prices2 = intArrayOf(7, 6, 4, 3, 1)
    println(maxProfit(prices2)) // 0
}