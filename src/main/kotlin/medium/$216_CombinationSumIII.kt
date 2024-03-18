package medium

/**
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 * - Only numbers 1 through 9 are used.
 * - Each number is used at most once.
 *
 * Return a list of all possible valid combinations. The list must not contain the same combination twice, and the
 * combinations may be returned in any order.
 *
 * constraints:
 * - 2 <= k <= 9
 * - 1 <= n <= 60
 *
 * Example 1:
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 *
 * Example 2:
 * Input: k = 3, n = 9
 * Output: [[1,2,6],[1,3,5],[2,3,4]]
 *
 * Example 3:
 * Input: k = 4, n = 1
 * Output: []
 */
fun main() {
    fun combinationSum3(k: Int, n: Int): List<List<Int>> {
        // base case
        if (n > 45) return emptyList()

        val result = mutableListOf<List<Int>>()

        fun dfs(start: Int, k: Int, n: Int, path: MutableList<Int>) {
            // when the right amount of numbers are used to sum up to n, add the path to the result
            if (k == 0 && n == 0) {
                result.add(path.toList())
                return
            }

            // from "start", add the numbers to the path and recursively call the function
            for (i in start..9) {
                // if "i", which is current iterating number, is greater than "n", which is the remaining sum, then
                // no need to further investigate the path, break the loop
                if (i > n) break

                // add the number to the path and call the function recursively
                path.add(i)
                dfs(i + 1, k - 1, n - i, path)

                // once the recursive call is done, remove the last number from the path
                path.removeAt(path.lastIndex)
            }
        }

        dfs(1, k, n, mutableListOf())

        return result
    }

    println(combinationSum3(3, 7)) // [[1,2,4]]
    println(combinationSum3(3, 9)) // [[1,2,6],[1,3,5],[2,3,4]]
    println(combinationSum3(4, 1)) // []
}