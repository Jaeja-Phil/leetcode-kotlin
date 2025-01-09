package medium

/**
 * Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and then at
 * each step perform either of the following:
 * - Append the character '0' zero times.
 * - Append the character '1' one times.
 *
 * This can be performed any number of times.
 *
 * A good string is a string constructed by the above process having a length between low and high (inclusive).
 *
 * Return the number of different good strings that can be constructed satisfying these properties. Since the answer
 * can be large, return it modulo 10^9 + 7.
 *
 * Constraints:
 * - 1 <= low <= high <= 10^5
 * - 1 <= zero, one <= low
 *
 * Example 1:
 * Input: low = 3, high = 3, zero = 1, one = 1
 * Output: 8
 * Explanation:
 * One possible valid good string is "011".
 * It can be constructed as follows: "" -> "0" -> "01" -> "011".
 * All binary strings from "000" to "111" are good strings in this example.
 *
 * Example 2:
 * Input: low = 2, high = 3, zero = 1, one = 2
 * Output: 5
 * Explanation: The good strings are "00", "11", "000", "110", and "011".
 */
fun main() {
    fun countGoodStrings(low: Int, high: Int, zero: Int, one: Int): Int {
        val mod = 1_000_000_007
        val dp = IntArray(high + 1)
        dp[0] = 1

        for (i in 1..high) {
            dp[i] = dp.getOrElse(i - zero) { 0 } + dp.getOrElse(i - one) { 0 }
            dp[i] %= mod
        }

        var answer = 0
        for (i in low..high) {
            answer = (answer + dp[i]) % mod
        }

        return answer
    }

    println(countGoodStrings(3, 3, 1, 1)) // 8
    println(countGoodStrings(2, 3, 1, 2)) // 5
}