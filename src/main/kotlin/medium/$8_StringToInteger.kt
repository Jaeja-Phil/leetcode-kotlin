package medium

/**
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
 *
 * The algorithm for myAtoi(string s) is as follows:
 *
 * 1. Whitespace: Ignore any leading whitespace (" ").
 * 2. Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity if neither
 *    present.
 * 3. Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of
 *    the string is reached. If no digits were read, then the result is 0.
 * 4. Rounding: If the integer is out of the 32-bit signed integer range [-2^31, 2^31 - 1], then round the integer to
 *    remain in the range. Specifically, integers less than -231 should be rounded to -231, and integers greater than
 *    2^31 - 1 should be rounded to 2^31 - 1.
 *
 * Return the integer as the final result.
 *
 * Constraints:
 * - 0 <= s.length <= 200
 * - s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.
 *
 * Example 1:
 * Input: s = "42"
 * Output: 42
 *
 * Example 2:
 * Input: s = "   -42"
 * Output: -42
 *
 * Example 3:
 * Input: s = "1337c0d3"
 * Output: 1337
 *
 * Example 4:
 * Input: s = "0-1"
 * Output: 0
 *
 * Example 5:
 * Input: s = "words and 987"
 * Output: 0
 */
fun main() {
    fun myAtoi(s: String): Int {
        var finalNumber = StringBuilder()
        var isNegative = false

        run breaking@{
            s.forEach {
                if (it.isDigit())
                    finalNumber.append(it)
                else if (finalNumber.isEmpty() && (it == '-' || it == '+')) {
                    finalNumber.append(it)
                    isNegative = it == '-'
                } else if (!it.isDigit() && (finalNumber.isNotEmpty() || !it.isWhitespace()))
                    return@breaking
            }
        }

        if (!finalNumber.contains("[0-9]".toRegex()))
            return 0

        return try {
            finalNumber.toString().toInt()
        } catch (e: Exception) {
            if (isNegative) {
                -2147483648
            } else {
                2147483647
            }
        }
    }

    val tests = listOf(
        "42", // 42
        "   -42", // -42
        "1337c0d3", // 1337
        "0-1", // 0
        "words and 987" // 0
    )
    tests.forEach { test -> println(myAtoi(test)) }
}
