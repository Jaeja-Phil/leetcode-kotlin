package medium

import java.util.*

/**
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets
 * is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; there are no extra white spaces,
 * square brackets are well-formed, etc. Furthermore, you may assume that the original data
 * does not contain any digits and that digits are only for those repeat numbers,
 * k. For example, there will not be input like 3a or 2[4].
 *
 * The test cases are generated so that the length of the output will never exceed 10^5.
 *
 * Constraints:
 * - 1 <= s.length <= 30
 * - s consists of lowercase English letters, digits, and square brackets '[]'.
 * - s is guaranteed to be a valid input.
 * - All the integers in s are in the range [1, 300].
 *
 * Example 1:
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 *
 * Example 2:
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 *
 * Example 3:
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 */
fun main() {
    val res1 = `Decode String`("3[a]2[bc]")
    println(res1) // "aaabcbc"

    val res2 = `Decode String`("3[a2[c]]")
    println(res2) // "accaccacc"

    val res3 = `Decode String`("2[abc]3[cd]ef")
    println(res3) // "abcabccdcdcdef"
}

fun `Decode String`(s: String): String {
    val countStack = Stack<Int>()
    val sbStack = Stack<StringBuilder>()
    var currString = StringBuilder()
    var k = StringBuilder()

    s.forEach {
        when {
            /**
             * when current character is a digit, append it to the k string builder to keep track of the count.
             * (count can be more than 1 digit)
             */
            it.isDigit() -> k.append(it)
            /**
             * when current character is an opening bracket, push the count and current string to their respective stacks.
             */
            it == '[' -> {
                /**
                 * push the count to the count stack.
                 */
                countStack.push(k.toString().toInt())
                /**
                 * push the current string to the string builder stack.
                 */
                sbStack.push(currString)
                /**
                 * reset the current string and k string builder.
                 */
                currString = StringBuilder()
                k = StringBuilder()
            }
            /**
             * when current character is a closing bracket, pop the count and current string from their respective stacks.
             */
            it == ']' -> {
                /**
                 * get the last count from the count stack which is the number of times the current string should be repeated.
                 */
                val count = countStack.pop()
                /**
                 * get the last string builder from the string builder stack which is the string to be repeated.
                 */
                val sb = sbStack.pop()
                /**
                 * repeat the current string the number of times the count is.
                 */
                repeat(count) { sb.append(currString) }
                /**
                 * set the current string to the repeated string.
                 */
                currString = sb
            }
            /**
             * when current character is a letter, append it to the current string.
             */
            else -> currString.append(it)
        }
    }

    /**
     * return the current string.
     */
    return currString.toString()
}
