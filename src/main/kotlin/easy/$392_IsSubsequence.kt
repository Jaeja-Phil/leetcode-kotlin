package easy

/**
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 *
 * A subsequence of a string is a new string that is formed from the original string by
 * deleting some (can be none) of the characters without disturbing the relative positions
 * of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * constraints:
 * - 0 <= s.length <= 100
 * - 0 <= t.length <= 10^4
 * - s and t consist only of lowercase English letters.
 *
 * Example 1:
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 *
 * Example 2:
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 */
fun main() {
    val res1 = `Is Subsequence`("abc", "ahbgdc")
    println(res1) // true

    val res2 = `Is Subsequence`("axc", "ahbgdc")
    println(res2) // false
}

fun `Is Subsequence`(s: String, t: String): Boolean {
    /**
     * some edge / quickly returnable cases...
     */
    if (s.isEmpty()) return true
    if (t.isEmpty()) return false
    if (s.length > t.length) return false
    if (s == t) return true

    /**
     * set a pointer for "s" and "t"
     */
    var sPointer = 0
    var tPointer = 0

    /**
     * loop through t
     */
    while (tPointer < t.length) {
        /**
         * check if "s" pointer reached the end of "s"
         * if it did, it means we have found all the characters in "s" in "t"
         */
        if (sPointer == s.length) return true

        /**
         * if s pointer is equal to t pointer, increment s pointer
         */
        if (s[sPointer] == t[tPointer]) sPointer++

        /**
         * increment t pointer
         */
        tPointer++
    }

    /**
     * again, check if "s" pointer reached the end of "s"
     * if it did, it means we have found all the characters in "s" in "t"
     */
    return sPointer == s.length
}