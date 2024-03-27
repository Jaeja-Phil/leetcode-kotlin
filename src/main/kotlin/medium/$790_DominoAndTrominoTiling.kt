package medium

/**
 * You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
 *
 * Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it
 * modulo 10^9 + 7.
 *
 * In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two
 * 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.
 *
 * constraints:
 * - 1 <= n <= 1000
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
    fun numTilings(n: Int): Int {
        if (n < 3) {
            return n
        }

        var array = longArrayOf(1, 2, 5)
        for (i in 3 ..< n) {
            val temp = array[0]
            array[0] = array[1]
            array[1] = array[2]
            array[2] = ((array[2] * 2 + temp) % 1000000007)
        }

        return array[2].toInt()
    }

    println(numTilings(1)) // 1
    println(numTilings(2)) // 2
    println(numTilings(3)) // 5
    println(numTilings(4)) // 11
    println(numTilings(5)) // 24
}