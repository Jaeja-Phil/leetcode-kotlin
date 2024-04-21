package easy

/**
 * You have n coins and you want to build a staircase with these coins. The staircase consists of k rows where the
 * ith row has exactly i coins. The last row of the staircase may be incomplete.
 *
 * Given the integer n, return the number of complete rows of the staircase you will build.
 *
 * constraints:
 * - 1 <= n <= 2^31 - 1
 *
 * Example 1:
 * Input: n = 5
 * Output: 2
 * Explanation: Because the 3rd row is incomplete, we return 2.
 *
 * Example 2:
 * Input: n = 8
 * Output: 3
 * Explanation: Because the 4th row is incomplete, we return 3.
 */
fun main() {
    fun arrangeCoins(n: Int): Int {
        // base case
        if (n <= 2) return 1

        var left = 1L
        var right = n.toLong()
        val target = n.toLong()
        while (left <= right) {
            val mid = left + (right - left) / 2
            val currSum = mid * (mid + 1) / 2
            when {
                currSum == target -> return mid.toInt()
                currSum < target -> left = mid + 1
                else -> right = mid - 1
            }
        }

        return right.toInt()
    }

    println(arrangeCoins(5)) // 2
    println(arrangeCoins(8)) // 3
}