package easy

/**
 * You are given two strings word1 and word2.
 * Merge the strings by adding letters in alternating order, starting with word1.
 * If a string is longer than the other, append the additional letters onto the end of the merged string.
 * Return the merged string.
 *
 * constraints:
 * - 1 <= word1.length, word2.length <= 100
 * - word1 and word2 consist of lowercase English letters.
 *
 * Example 1:
 * Input: word1 = "abc", word2 = "pqr"
 * Output: "apbqcr"
 * Explanation: The merged string will be merged as so:
 * word1:  a   b   c
 * word2:    p   q   r
 * merged: a p b q c r
 *
 * Example 2:
 * Input: word1 = "ab", word2 = "pqrs"
 * Output: "apbqrs"
 * Explanation: Notice that as word2 is longer, "rs" is appended to the end.
 * word1:  a   b
 * word2:    p   q   r   s
 * merged: a p b q   r   s
 */

fun main() {
    val res1 = `Merge Strings Alternately`("abc", "pqr")
    println(res1) // apbqcr

    val res2 = `Merge Strings Alternately`("ab", "pqrs")
    println(res2) // apbqrs
}

fun `Merge Strings Alternately`(word1: String, word2: String): String {
    /** initialize a StringBuilder to store the result **/
    val sb = StringBuilder()

    /**
     * use minOf() to get the minimum value of two numbers
     *
     * documentation:
     * @SinceKotlin("1.1")
     * @kotlin.internal.InlineOnly
     * public actual inline fun minOf(a: Int, b: Int): Int {
     *     return Math.min(a, b)
     * }
     */
    val min = minOf(word1.length, word2.length)

    /**
     * use for loop to iterate through the minimum length of two words
     *
     * "..<" operator is used to create a range of values from a start value (inclusive) to an end value (exclusive)
     * - https://kotlinlang.org/docs/ranges.html#ranges
     */
    for (i in 0..< min) {
        sb.append(word1[i])
        sb.append(word2[i])
    }

    /**
     * append the rest of the longer word to the result
     * - use substring() to get the rest of the longer word
     * - NOTE: min is the minimum length of two words
     */
    if (word1.length > word2.length) {
        sb.append(word1.substring(min))
    } else if (word2.length > word1.length) {
        sb.append(word2.substring(min))
    }

    /**
     * finally, return the result
     */
    return sb.toString()
}