package easy

/**
 * Write a function that reverses a string. The input string is given as an array of characters s.
 *
 * You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Constraints:
 * - 1 <= s.length <= 10^5
 * - s[i] is a printable ascii character
 *
 * Example 1:
 * Input: s = ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 *
 * Example 2:
 * Input: s = ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 */
fun main() {
    fun reverseString(s: CharArray) {
        var left = 0
        var right = s.lastIndex
        while (left < right) {
            val tmp = s[left]
            s[left] = s[right]
            s[right] = tmp
            left++
            right--
        }
    }

    val s1 = charArrayOf('h', 'e', 'l', 'l', 'o')
    reverseString(s1)
    println(s1.contentToString()) // [o, l, l, e, h]

    val s2 = charArrayOf('H', 'a', 'n', 'n', 'a', 'h')
    reverseString(s2)
    println(s2.contentToString()) // [h, a, n, n, a, H]
}