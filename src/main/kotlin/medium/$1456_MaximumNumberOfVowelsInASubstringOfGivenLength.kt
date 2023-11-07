package medium

/**
 * Given a string s and an integer k, return the maximum number of vowel letters in
 * any substring of s with length k.
 *
 * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
 *
 * constraints:
 * - 1 <= s.length <= 10^5
 * - s consists of lowercase English letters.
 * - 1 <= k <= s.length
 *
 * Example 1:
 * Input: s = "abciiidef", k = 3
 * Output: 3
 * Explanation: The substring "iii" contains 3 vowel letters.
 *
 * Example 2:
 * Input: s = "aeiou", k = 2
 * Output: 2
 * Explanation: Any substring of length 2 contains 2 vowels.
 *
 * Example 3:
 * Input: s = "leetcode", k = 3
 * Output: 2
 * Explanation: The substring "lee" contains 2 vowel letters.
 */

fun main() {
    val res1 = `Maximum Number of Vowels in a Substring of Given Length`("abciiidef", 3)
    println(res1) // 3

    val res2 = `Maximum Number of Vowels in a Substring of Given Length`("aeiou", 2)
    println(res2) // 2

    val res3 = `Maximum Number of Vowels in a Substring of Given Length`("leetcode", 3)
    println(res3) // 2
}

fun `Maximum Number of Vowels in a Substring of Given Length`(s: String, k: Int): Int {
    /**
     * create a set for list of vowels
     */
    val vowels = setOf('a', 'e', 'i', 'o', 'u')

    /**
     * create two pointers for sliding window
     */
    var windowStart = 0
    var windowEnd = 0

    /**
     * create two variables to store
     * - maxCount: maximum number of vowels so far
     * - currCount: current number of vowels in the window
     */
    var maxCount = 0
    var currCount = 0

    /**
     * start sliding
     */
    while (windowEnd < s.length) {
        /**
         * get current letter in the window (right side of the window)
         */
        val currLetter = s[windowEnd]

        /**
         * if current letter is a vowel, increment currCount
         */
        if (currLetter in vowels) {
            currCount++
        }

        /**
         * update maxCount if currCount is greater than maxCount
         */
        maxCount = maxOf(maxCount, currCount)

        /**
         * if maxCount is equal to k, there is no need to continue (because it is the maximum value possible)
         * return the maxCount
         */
        if (maxCount == k) {
            return maxCount
        }

        /**
         * if window length is equal to k, we need to shrink the window
         */
        val windowLength = windowEnd - windowStart + 1
        if (windowLength == k) {
            val startLetter = s[windowStart]
            /**
             * if letter on the left side of the window is a vowel, decrement currCount
             */
            if (startLetter in vowels) {
                currCount--
            }
            /**
             * slide the left side of the window by one
             */
            windowStart++
        }

        /**
         * slide the right side of the window by one
         */
        windowEnd++
    }

    /**
     * once iteration is done, return maxCount which holds the maximum number of vowels in a substring of given length
     */
    return maxCount
}