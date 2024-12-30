package medium

/**
 * You are given an array of n pairs "pairs" where pairs[i] = [lefti, righti] and lefti < righti.
 *
 * A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.
 *
 * Return the length longest chain which can be formed.
 *
 * You do not need to use up all the given intervals. You can select pairs in any order.
 *
 * Constraints:
 * - n == pairs.length
 * - 1 <= n <= 1000
 * - -1000 <= lefti < righti <= 1000
 *
 * Example 1:
 * Input: pairs = [[1, 2], [2, 3], [3, 4]]
 * Output: 2
 * Explanation: The longest chain is [1, 2] -> [3, 4].
 *
 * Example 2:
 * Input: pairs = [[1, 2], [7, 8], [4, 5]]
 * Output: 3
 * Explanation: The longest chain is [1, 2] -> [4, 5] -> [7, 8].
 */
fun main() {
    fun findLongestChain(pairs: Array<IntArray>): Int {
        val sortedPairs = pairs.sortedBy { it[1] } // sort by right value
        val n = sortedPairs.size
        val dp = IntArray(n) { 1 }
        for (i in 1..<n) {
            for (j in 0..<i) {
                val (leftI, _) = sortedPairs[i]
                val (_, rightJ) = sortedPairs[j]
                if (rightJ < leftI) {
                    dp[i] = maxOf(dp[i], dp[j] + 1)
                }
            }
        }

        return dp.maxOrNull()!!
    }

    println(findLongestChain(arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4)))) // 2
    println(findLongestChain(arrayOf(intArrayOf(1, 2), intArrayOf(7, 8), intArrayOf(4, 5)))) // 3
}
