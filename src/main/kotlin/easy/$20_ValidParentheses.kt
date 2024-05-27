package easy

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is
 * valid.
 *
 * An input string is valid if:
 * - Open brackets must be closed by the same type of brackets.
 * - Open brackets must be closed in the correct order.
 * - Every close bracket has a corresponding open bracket of the same type.
 *
 * Constraints:
 * - 1 <= s.length <= 10^4
 * - s consists of parentheses only '()[]{}'.
 *
 * Example 1:
 * Input: s = "()"
 * Output: true
 *
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 *
 * Example 3:
 * Input: s = "(]"
 * Output: false
 */
fun main() {
    fun isValid(s: String): Boolean {
        // base case, if length is odd then it's invalid
        if (s.length % 2 != 0) return false

        val stack = mutableListOf<Char>()
        val map = mapOf(')' to '(', '}' to '{', ']' to '[')
        for (c in s) {
            if (c in map) { // closing bracket
                if (stack.isEmpty() || stack.removeLast() != map[c]) {
                    return false
                }
            } else { // opening bracket
                stack.add(c)
            }
        }

        return stack.isEmpty()
    }

    val input = "([)]"
    println(isValid(input)) // false

    val input2 = "{[]}"
    println(isValid(input2)) // true

    val input3 = "()"
    println(isValid(input3)) // true
}