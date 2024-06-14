package medium

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing
 * a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by
 * any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Constraints:
 * - 1 <= coins.length <= 12
 * - 1 <= coins[i] <= 2^31 - 1
 * - 0 <= amount <= 10^4
 *
 * Example 1:
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 *
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 *
 * Example 3:
 * Input: coins = [1], amount = 0
 * Output: 0
 */
fun main() {
    fun coinChange(coins: IntArray, amount: Int): Int {
        val dp = IntArray(amount + 1) { amount + 1 }
        dp[0] = 0 // 0 amount can be made with 0 coins

        for (i in 1..amount) {
            for (coin in coins) {
                if (coin <= i) {
                    dp[i] = minOf(dp[i], dp[i - coin] + 1)
                }
            }
        }

        return if (dp[amount] > amount) -1 else dp[amount]
    }

    val coins1 = intArrayOf(1, 2, 5)
    val amount1 = 11
    println(coinChange(coins1, amount1)) // 3

    val coins2 = intArrayOf(2)
    val amount2 = 3
    println(coinChange(coins2, amount2)) // -1

    val coins3 = intArrayOf(1)
    val amount3 = 0
    println(coinChange(coins3, amount3)) // 0
}