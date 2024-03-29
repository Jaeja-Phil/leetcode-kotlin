package medium

/**
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 *
 * You have the following three operations permitted on a word:
 * -  Insert a character
 * - Delete a character
 * - Replace a character
 *
 * Constraints:
 * - 0 <= word1.length, word2.length <= 500
 * - word1 and word2 consist of lowercase English letters.
 *
 * Example 1:
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 *
 * Example 2:
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 */
fun main() {
    fun minDistance(word1: String, word2: String): Int {
        val w1Length = word1.length
        val w2Length = word2.length
        // base case
        if (w1Length == 0) return w2Length
        if (w2Length == 0) return w1Length
        if (word1 == word2) return 0

        // create a 2D array to store the minimum number of operations required to convert word1 to word2
        // + 1 to account for the empty string
        val dp = Array(w1Length + 1) { IntArray(w2Length + 1) }
        (0..w1Length).forEach { dpRowIdx -> dp[dpRowIdx][0] = dpRowIdx }
        (0..w2Length).forEach { dpColIdx -> dp[0][dpColIdx] = dpColIdx }

        (1..w1Length).forEach { dpRowIdx ->
            (1..w2Length).forEach { dpColIdx ->
                dp[dpRowIdx][dpColIdx] = when {
                    word1[dpRowIdx - 1] == word2[dpColIdx - 1] -> dp[dpRowIdx - 1][dpColIdx - 1]
                    else -> 1 + minOf(
                        dp[dpRowIdx - 1][dpColIdx - 1], // replace
                        dp[dpRowIdx - 1][dpColIdx], // delete
                        dp[dpRowIdx][dpColIdx - 1] // insert
                    )
                }
            }
        }

        return dp[w1Length][w2Length]
    }

    println(minDistance("horse", "ros")) // 3
    println(minDistance("intention", "execution")) // 5
}