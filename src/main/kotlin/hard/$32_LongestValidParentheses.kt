package hard

/**
 * Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed)
 * parentheses substring.
 *
 * Constraints:
 * - 0 <= s.length <= 3 * 10^4
 *
 * Example 1:
 * Input: s = "(()"
 * Output: 2
 *
 * Example 2:
 * Input: s = ")()())"
 * Output: 4
 *
 * Example 3:
 * Input: s = ""
 * Output: 0
 */
fun main() {
    fun longestValidParentheses(s: String): Int {
        val stack = mutableListOf<Int>()
        var maxLength = 0
        var lastInvalidIndex = -1

        for (i in s.indices) {
            if (s[i] == '(') {
                stack.add(i)
            } else {
                if (stack.isNotEmpty()) {
                    stack.removeLast()
                    maxLength = if (stack.isEmpty()) {
                        maxOf(maxLength, i - lastInvalidIndex)
                    } else {
                        maxOf(maxLength, i - stack.last())
                    }
                } else {
                    lastInvalidIndex = i
                }
            }
        }

        return maxLength
    }

    println(longestValidParentheses("(()")) // Output: 2
    println(longestValidParentheses(")()())")) // Output: 4
    println(longestValidParentheses("")) // Output: 0
}
