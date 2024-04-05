package easy

/**
 * You are given an array of characters letters that is sorted in non-decreasing order, and a character target.
 * There are at least two different characters in letters.
 *
 * Return the smallest character in letters that is lexicographically greater than target. If such a character does
 * not exist, return the first character in letters.
 *
 * Constraints:
 * - 2 <= letters.length <= 10^4
 * - letters[i] is a lowercase English letter.
 * - letters is sorted in non-decreasing order.
 * - letters contains at least two different characters.
 * - target is a lowercase English letter.
 *
 * Example 1:
 * Input: letters = ["c","f","j"], target = "a"
 * Output: "c"
 * Explanation: The smallest character in letters that is greater than "a" is "c".
 *
 * Example 2:
 * Input: letters = ["c","f","j"], target = "c"
 * Output: "f"
 * Explanation: The smallest character in letters that is greater than "c" is "f".
 *
 * Example 3:
 * Input: letters = ["c","f","j"], target = "d"
 * Output: "f"
 * Explanation: The smallest character in letters that is greater than "d" is "f".
 */
fun main() {
    fun nextGreatestLetter(letters: CharArray, target: Char): Char {
        var left = 0
        var right = letters.lastIndex

        // base case
        if (target >= letters[right] || target < letters[left]) {
            return letters[left]
        }

        while (left < right) {
            val mid = left + (right - left) / 2
            if (target < letters[mid]) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return letters[left % letters.size]
    }

    println(nextGreatestLetter(charArrayOf('c', 'f', 'j'), 'a')) // c
    println(nextGreatestLetter(charArrayOf('c', 'f', 'j'), 'c')) // f
    println(nextGreatestLetter(charArrayOf('c', 'f', 'j'), 'd')) // f
    println(nextGreatestLetter(charArrayOf('x', 'x', 'y', 'y'), 'z')) // x
}