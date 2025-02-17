package medium

/**
 * You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick. You want to use
 * all the matchsticks to make one square. You should not break any stick, but you can link them up, and each matchstick
 * must be used exactly one time.
 *
 * Return true if you can make this square and false otherwise.
 *
 * Constraints:
 * - 1 <= matchsticks.length <= 15
 * - 0 <= matchsticks[i] <= 10^9
 *
 * Example 1:
 * Input: matchsticks = [1,1,2,2,2]
 * Output: true
 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 *
 * Example 2:
 * Input: matchsticks = [3,3,3,3,4]
 * Output: false
 * Explanation: You cannot find a way to form a square with all the matchsticks.
 */
fun main() {
    fun makeSquare(matchsticks: IntArray): Boolean {
        val sumOfLengths = matchsticks.sum()
        if (sumOfLengths % 4 != 0) {
            return false
        }

        val sideLength = sumOfLengths / 4
        val sides = IntArray(4)

        fun dfs(index: Int): Boolean {
            if (index == matchsticks.size) {
                return sides.all { it == sideLength }
            }

            for (i in sides.indices) {
                if (sides[i] + matchsticks[index] > sideLength) {
                    continue
                }

                sides[i] += matchsticks[index]
                if (dfs(index + 1)) {
                    return true
                }
                sides[i] -= matchsticks[index]
            }

            return false
        }

        return dfs(0)
    }

    val matchsticks1 = intArrayOf(1, 1, 2, 2, 2)
    println(makeSquare(matchsticks1)) // true

    val matchsticks2 = intArrayOf(3, 3, 3, 3, 4)
    println(makeSquare(matchsticks2)) // false
}