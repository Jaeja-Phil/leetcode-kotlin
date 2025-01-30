package medium

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * Constraints:
 * - 1 <= n <= 8
 *
 * Example 1:
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 *
 * Example 2:
 * Input: n = 1
 */
fun main() {
    fun generateParenthesis(n: Int): List<String> {
        val res = mutableListOf<String>()
        fun backtrack(s: String, open: Int, close: Int) {
            if (open == n && close == n) {
                res.add(s)
                return
            }
            if (open < n) backtrack("$s(", open + 1, close)
            if (close < open) backtrack("$s)", open, close + 1)
        }

        backtrack("", 0, 0)
        return res
    }

    println(generateParenthesis(3)) // [((())), (()()), (())(), ()(()), ()()()]
    println(generateParenthesis(1)) // [()]
}