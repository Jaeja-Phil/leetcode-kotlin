package medium

/**
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of
 * unique values from 1 to n.
 *
 * Constraints:
 * - 1 <= n <= 19
 *
 * Example 1:
 * Input: n = 3
 * Output: 5
 *
 * Example 2:
 * Input: n = 1
 * Output: 1
 */
fun main() {
    fun numTrees(n: Int): Int {
//        // initialize the dp array, where dp[i] represents the number of unique BSTs that can be formed with i nodes
//        val dp = IntArray(n + 1)
//        // dp[0] = 1 because there is one unique BST with 0 nodes (an empty tree)
//        dp[0] = 1
//        // dp[1] = 1 because there is one unique BST with 1 node (a single node tree)
//        dp[1] = 1
//
//        // fill the dp array
//        for (i in 2..n) {
//            // i-th iteration represents the number of unique BSTs that can be formed with i nodes
//            // consider each node as the root, and calculate the number of unique BSTs that can be formed
//            // formula:
//            // dp[i] = sum of left * right subtrees for each possible root
//            for (j in 1..i) {
//                dp[i] += dp[j - 1] * dp[i - j]
//            }
//        }
//
//        // return the number of unique BSTs that can be formed with n nodes
//        return dp[n]

        // My Solution
        if (n <= 2) return n

        val dp = IntArray(n + 1)
        dp[0] = 1
        dp[1] = 1
        dp[2] = 2

        for (i in 3..n) {
            var count = 0
            for (j in 1..i) {
                val leftBstCount = if (j == 1) 1 else dp[j - 1]
                val rightBstCount = if (j == i) 1 else dp[i - j]
                count += leftBstCount * rightBstCount
            }

            dp[i] = count
        }

        return dp[n]
    }

    println(numTrees(3)) // 5
    println(numTrees(1)) // 1
}