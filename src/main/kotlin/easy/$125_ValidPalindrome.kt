package easy

import com.sun.org.apache.xpath.internal.operations.Bool

/**
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all
 * non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters
 * and numbers.
 *
 * Given a string s, return true if it is a palindrome, or false otherwise.
 *
 * Constraints:
 * - 1 <= s.length <= 2 * 10^5
 * - s consists only of printable ASCII characters.
 *
 * Example 1:
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 *
 * Example 2:
 * Input: s ="race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 *
 * Example 3:
 * Input: s = " "
 * Output: true
 */
fun main() {
    fun isPalindrome(s: String): Boolean {
        // Solution 1. Using filter and reversed.
//        val cleanString = s.filter { it.isLetterOrDigit() }.lowercase()
//        return cleanString == cleanString.reversed()

        // Solution 2.
        var left = 0
        var right = s.lastIndex

        while (left < right) {
            while (left < right && !s[left].isLetterOrDigit()) left++
            while (left < right && !s[right].isLetterOrDigit()) right--

            if (s[left].lowercase() != s[right].lowercase()) return false
            left++
            right--
        }

        return true
    }

    val tests = listOf(
        "A man, a plan, a canal: Panama", // true
        "race a car", // false
        " " // true
    )
    tests.forEach { test -> println(isPalindrome(test)) }
}
