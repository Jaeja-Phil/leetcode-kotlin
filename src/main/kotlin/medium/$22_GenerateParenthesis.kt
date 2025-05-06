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
        // Solution 1. Using recursion
//        val res = mutableListOf<String>()
//        fun backtrack(s: String, open: Int, close: Int) {
//            if (open == n && close == n) {
//                res.add(s)
//                return
//            }
//            if (open < n) backtrack("$s(", open + 1, close)
//            if (close < open) backtrack("$s)", open, close + 1)
//        }
//
//        backtrack("", 0, 0)
//        return res

        // Solution 2. Using dp
        val dp = Array(n + 1) { mutableListOf<String>() }
        dp[0].add("")

        for (i in 1..n) {
            for (j in 0..<i) {
                for (left in dp[j]) {
                    for (right in dp[i - 1 - j]) {
                        dp[i].add("($left)$right")
                    }
                }
            }
        }

        return dp[n]
    }

    println(generateParenthesis(3)) // [((())), (()()), (())(), ()(()), ()()()]
    println(generateParenthesis(1)) // [()]
}