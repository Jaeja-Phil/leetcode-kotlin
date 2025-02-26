package medium

import java.util.PriorityQueue

/**
 * You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at
 * index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:
 * - i + minJump <= j <= min(i + maxJump, s.length - 1), and
 * - s[j] == '0'.
 *
 * Return true if you can reach index s.length - 1 in s, or false otherwise.
 *
 * Constraints:
 * - 2 <= s.length <= 10^5
 * - s[i] is either '0' or '1'.
 * - s[0] == '0'
 * - 1 <= minJump <= maxJump < s.length
 *
 * Example 1:
 * Input: s = "011010", minJump = 2, maxJump = 3
 * Output: true
 * Explanation:
 * In the first step, move from index 0 to index 3.
 * In the second step, move from index 3 to index 5.
 *
 * Example 2:
 * Input: s = "01101110", minJump = 2, maxJump = 3
 * Output: false
 */
fun main() {
    fun jumpGameVII(s: String, minJump: Int, maxJump: Int): Boolean {
        // Solution 1. (Queue)
//        val queue = ArrayDeque<Int>().apply { add(0) }
//        var farthest = 0
//
//        while (queue.isNotEmpty()) {
//            val i = queue.removeFirst()
//            val start = maxOf(i + minJump, farthest)
//
//            for (j in start..minOf(i + maxJump, s.lastIndex)) {
//                if (s[j] == '0') {
//                    if (j == s.length - 1) return true
//                    queue.add(j)
//                }
//            }
//
//            farthest = i + maxJump
//        }
//
//        return false

        // Solution 2. (DP)
        if (s.last() == '1') {
            return false
        }

        val dp = BooleanArray(s.length)
        dp[0] = true
        var j = 0

        for (i in 0 until s.length) {
            if (!dp[i]) {
                continue
            }

            j = maxOf(j, i + minJump)
            while (j < minOf(i + maxJump + 1, s.length)) {
                if (s[j] == '0') {
                    dp[j] = true
                }
                j++
            }
        }

        return dp.last()
    }

    println(jumpGameVII("011010", 2, 3)) // true
    println(jumpGameVII("01101110", 2, 3)) // false
}
