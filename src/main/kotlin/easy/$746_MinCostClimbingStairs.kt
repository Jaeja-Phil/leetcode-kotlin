package easy

/**
 * You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost,
 * you can either climb one or two steps.
 *
 * You can either start from the step with index 0, or the step with index 1.
 *
 * Return the minimum cost to reach the top of the floor.
 *
 * constraints:
 * - 2 <= cost.length <= 1000
 * - 0 <= cost[i] <= 999
 *
 * Example 1:
 * Input: cost = [10, 15, 20]
 * Output: 15
 * Explanation: Cheapest is: start on cost[1], pay that cost, and go to the top.
 *
 * Example 2:
 * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * Output: 6
 * Explanation: Cheapest is: start on cost[0], and only step on 1s, skipping cost[3].
 */
fun main() {
    fun minCostClimbingStairs(cost: IntArray): Int {
        // Recursion with memoization
        val memo = IntArray(cost.size + 1) { -1 }
        fun minCost(i: Int): Int {
            if (i <= 1) return 0
            if (memo[i] != -1) return memo[i]
            memo[i] = minOf(minCost(i - 1) + cost[i - 1], minCost(i - 2) + cost[i - 2])
            return memo[i]
        }

        return minCost(cost.size)

        // Dynamic programming
//        val dp = IntArray(cost.size + 1)
//        dp[0] = 0 // cost to reach step 0
//        dp[1] = 0 // cost to reach step 1
//        for (i in 2..cost.size) {
//            dp[i] =
//                minOf(
//                    dp[i - 1] + cost[i - 1],
//                    dp[i - 2] + cost[i - 2]
//                )
//        }
//
//        return dp[cost.size] // cost to reach the top

        // Space optimized dynamic programming
//        val dp = IntArray(3)
//        for (i in 2..cost.size) {
//            dp[2] = minOf(dp[0] + cost[i - 2], dp[1] + cost[i - 1])
//            dp[0] = dp[1]
//            dp[1] = dp[2]
//        }
//        return dp[2]
    }

    val input = intArrayOf(10, 15, 20)
    println(minCostClimbingStairs(input)) // 15

    val input2 = intArrayOf(1, 100, 1, 1, 1, 100, 1, 1, 100, 1)
    println(minCostClimbingStairs(input2)) // 6
}
