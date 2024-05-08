package easy

/**
 * Given a string s consisting of words and spaces, return the length of the last word in the string.
 * A word is a maximal substring consisting of non-space characters only.
 *
 * Constraints:
 * - 1 <= s.length <= 10^4
 * - s consists of only English letters and spaces ' '.
 * - There will be at least one word in s.
 *
 * Example 1:
 * Input: s = "Hello World"
 * Output: 5
 *
 * Example 2:
 * Input: s = "   fly me   to   the moon  "
 * Output: 4
 */
fun main() {
    fun lengthOfLastWord(s: String): Int {
        var idx = s.lastIndex
        var count = 0
        while (idx >= 0) {
            val currentChar = s[idx]
            if (currentChar != ' ') count++
            if (currentChar == ' ' && count > 0) break
            idx--
        }

        return count
    }

    val tests = listOf(
        "Hello World", // 5
        "   fly me   to   the moon  ", // 4
    )
    tests.forEach { test ->
        println(lengthOfLastWord(test))
    }
}
