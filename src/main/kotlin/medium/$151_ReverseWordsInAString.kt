package medium

/**
 * Given an input string s, reverse the order of the words.
 * A word is defined as a sequence of non-space characters.
 * The words in s will be separated by at least one space.
 * Return a string of the words in reverse order concatenated by a single space.
 * Note that s may contain leading or trailing spaces or multiple spaces between two words.
 * The returned string should only have a single space separating the words.
 * Do not include any extra spaces.
 *
 * constraints:
 * - 1 <= s.length <= 10^4
 * - s contains English letters (upper-case and lower-case), digits, and spaces ' '.
 * - There is at least one word in s.
 *
 * Example 1:
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 *
 * Example 2:
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 *
 * Example 3:
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 */
fun main() {
    val res1 = `Reverse Words in a String`("the sky is blue")
    println(res1) // blue is sky the

    val res2 = `Reverse Words in a String`("  hello world  ")
    println(res2) // world hello

    val res3 = `Reverse Words in a String`("a good   example")
    println(res3) // example good a
}

fun `Reverse Words in a String`(s: String): String {
    var start = s.lastIndex
    val deque = ArrayDeque<String>()

    while (start >= 0) {
        var end = start
        while (start >= 0 && s[start] != ' ') {
            start--
        }
        deque.add(s.substring(start + 1, end + 1))
        while (start >= 0 && s[start] == ' ') {
            start--
        }
    }

    return deque.joinToString(" ").trim()
}