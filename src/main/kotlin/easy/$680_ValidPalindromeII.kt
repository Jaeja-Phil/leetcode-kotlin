package easy

/**
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 *
 * Constraints:
 * - 1 <= s.length <= 10^5
 * - s consists of lowercase English letters.
 *
 * Example 1:
 * Input: s = "aba"
 * Output: true
 *
 * Example 2:
 * Input: s = "abca"
 * Output: true
 *
 * Example 3:
 * Input: s = "abc"
 * Output: false
 */
fun main() {
    fun validPalindrome(s: String): Boolean {
        // Solution 1. (Recursive)
//        fun canFormPalindrome(left: Int, right: Int, deleteCount: Int): Boolean {
//            // since this question allows at most one deletion...
//            // if allowed delete count changes, you may change accordingly
//            if (deleteCount > 1) {
//                return false
//            }
//
//            // base case, if recursion has reached its end, it means the string is palindrome
//            if (left > right) {
//                return true
//            }
//
//            // if the characters are equal, move both pointers
//            return if (s[left] == s[right]) {
//                canFormPalindrome(left + 1, right - 1, deleteCount)
//            } else {
//                // if the characters are not equal, try to delete one character from left OR right
//                // NOTE: must increase deleteCount by 1
//                canFormPalindrome(left + 1, right, deleteCount + 1) || canFormPalindrome(left, right - 1, deleteCount + 1)
//            }
//        }
//
//        return canFormPalindrome(0, s.lastIndex, 0)

        // Solution 2.
        fun isPalindrome(left: Int, right: Int): Boolean {
            var l = left
            var r = right
            while (l < r) {
                if (s[l] != s[r]) {
                    return false
                }
                l++
                r--
            }
            return true
        }

        var left = 0
        var right = s.lastIndex
        while (left < right) {
            if (s[left] != s[right]) {
                return isPalindrome(left + 1, right) || isPalindrome(left, right - 1)
            }
            left++
            right--
        }

        return true
    }

    println(validPalindrome("aba")) // true
    println(validPalindrome("abca")) // true
    println(validPalindrome("abc")) // false
}
