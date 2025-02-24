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
        (0..w1Length).forEach { w1Idx -> dp[w1Idx][0] = w1Idx } // empty string to word2 means you need to insert all characters
        (0..w2Length).forEach { w2Idx -> dp[0][w2Idx] = w2Idx } // word1 to empty string means you need to delete all characters

        (1..w1Length).forEach { w1Idx ->
            (1..w2Length).forEach { w2Idx ->
                dp[w1Idx][w2Idx] = when {
                    // if the characters are the same, no operation is needed
                    word1[w1Idx - 1] == word2[w2Idx - 1] -> dp[w1Idx - 1][w2Idx - 1]
                    else -> 1 + minOf(
                        dp[w1Idx - 1][w2Idx - 1], // replace
                        dp[w1Idx - 1][w2Idx], // delete (from word1)
                        dp[w1Idx][w2Idx - 1], // insert (to word1)
                    )
                }
            }
        }

        return dp[w1Length][w2Length]
    }

    println(minDistance("horse", "ros")) // 3
    println(minDistance("intention", "execution")) // 5
}