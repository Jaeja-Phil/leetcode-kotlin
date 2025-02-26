package medium

/**
 * Alice and Bob continue their games with piles of stones. There are a number of piles arranged in a row, and each pile
 * has a positive integer number of stones piles[i]. The objective of the game is to end with the most stones.
 *
 * Alice and Bob take turns, with Alice starting first.
 *
 * On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M. Then,
 * we set M = max(M, X). Initially, M = 1.
 *
 * The game continues until all the stones have been taken.
 *
 * Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.
 *
 * Constraints:
 * - 1 <= piles.length <= 100
 * - 1 <= piles[i] <= 10^4
 *
 * Example 1:
 * Input: piles = [2, 7, 9, 4, 4]
 * Output: 10
 * Explanation:
 * If Alice takes one pile at the beginning, Bob takes two piles. Then Alice takes 3 piles, and gets 10 stones.
 * If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice gets 9 stones.
 * So we return 10 since it's larger.
 *
 * Example 2:
 * Input: piles = [1, 2, 3, 4, 5, 100]
 * Output: 104
 */
fun main() {
    fun stoneGameII(piles: IntArray): Int {
        val suffixSums = IntArray(piles.size + 1)
        for (i in piles.indices.reversed()) {
            suffixSums[i] = suffixSums[i + 1] + piles[i]
        }

        fun maxStonesAliceCanGet(start: Int, M: Int, dp: Array<IntArray>): Int {
            if (start >= piles.size) return 0
            if (start + 2 * M >= piles.size) return suffixSums[start]
            if (dp[start][M] != 0) return dp[start][M]

            var maxStones = 0
            for (X in 1..2 * M) {
                if (start + X > piles.size) break
                val stonesAliceCanGet = suffixSums[start] - suffixSums[start + X]
                val stonesBobCanGet = maxStonesAliceCanGet(start + X, maxOf(M, X), dp)
                maxStones = maxOf(maxStones, stonesAliceCanGet + suffixSums[start + X] - stonesBobCanGet)
            }

            dp[start][M] = maxStones
            return maxStones
        }

        return maxStonesAliceCanGet(0, 1, Array(piles.size) { IntArray(piles.size) })
    }

    println(stoneGameII(intArrayOf(2, 7, 9, 4, 4))) // 10
    println(stoneGameII(intArrayOf(1, 2, 3, 4, 5, 100))) // 104
}
