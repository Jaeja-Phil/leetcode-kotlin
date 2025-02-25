package medium

/**
 * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
 *
 * The following rules define a valid string:
 * - Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * - Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * - Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * - '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 *
 * Constraints:
 * - 1 <= s.length <= 100
 * - s[i] is '(', ')' or '*'.
 *
 * Example 1:
 * Input: s = "()"
 * Output: true
 *
 * Example 2:
 * Input: s = "(*)"
 * Output: true
 *
 * Example 3:
 * Input: s = "(*))"
 * Output: true
 */
fun main() {
    fun checkValidString(s: String): Boolean {
        val leftStack = mutableListOf<Int>()
        val starStack = mutableListOf<Int>()

        for (i in s.indices) {
            when (s[i]) {
                '(' -> leftStack.add(i)
                ')' -> {
                    if (leftStack.isNotEmpty()) {
                        leftStack.removeLast()
                    } else if (starStack.isNotEmpty()) {
                        starStack.removeLast()
                    } else {
                        return false
                    }
                }
                '*' -> starStack.add(i)
                else -> throw IllegalArgumentException("Invalid character: ${s[i]}")
            }
        }

        while (leftStack.isNotEmpty() && starStack.isNotEmpty()) {
            if (leftStack.removeLast() > starStack.removeLast()) {
                return false
            }
        }

        return leftStack.isEmpty()
    }

    println(checkValidString("()")) // true
    println(checkValidString("(*)")) // true
    println(checkValidString("(*))")) // true
}
