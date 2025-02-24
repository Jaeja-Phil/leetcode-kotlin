package medium

/**
 * Alice and Bob play a game with piles of stones. There are an even number of piles arranged in a row, and each pile
 * has a positive integer number of stones piles[i].
 *
 * The objective of the game is to end with the most stones. The total number of stones across all the piles is odd,
 * so there are no ties.
 *
 * Alice and Bob take turns, with Alice starting first. Each turn, a player takes the entire pile of stones either
 * from the beginning or from the end of the row. This continues until there are no more piles left, at which point
 * the person with the most stones wins.
 *
 * Assuming Alice and Bob play optimally, return true if Alice wins the game, or false if Bob wins.
 *
 * Constraints:
 * - 2 <= piles.length <= 500
 * - piles.length is even.
 * - 1 <= piles[i] <= 500
 * - sum(piles) is odd.
 *
 * Example 1:
 * Input: piles = [5,3,4,5]
 * Output: true
 * Explanation:
 * Alice starts first, and can only take the first 5 or the last 5.
 * Say she takes the first 5, so that the row becomes [3, 4, 5].
 * If Bob takes 3, then the board is [4, 5], and Alice takes 5 to win with 10 points.
 * If Bob takes the last 5, then the board is [3, 4], and Alice takes 4 to win with 9 points.
 * This demonstrated that taking the first 5 was a winning move for Alice, so we return true.
 *
 * Example 2:
 * Input: piles = [3, 7, 2, 3]
 * Output: true
 */
fun main() {
    fun stoneGame(piles: IntArray): Boolean {
        val n = piles.size
        // dp[i][j] = (score, opponentScore) for piles[i..j]
        val dp = Array(n) { Array(n) { 0 to 0 } }

        // on length 1, first player will always take the only pile, while second player will have 0 score (since there
        // is no pile to take)
        for (i in piles.indices) {
            dp[i][i] = piles[i] to 0
        }

        // checking for all possible lengths of piles
        // note: length 1 is already checked above
        for (len in 2..n) {
            // ex:
            // length 1 -> 0..1, 1..2, 2..3, ... etc
            // length 2 -> 0..2, 1..3, 2..4, ... etc
            for (left in piles.indices) {
                val right = left + len - 1
                if (right >= n) break

                // on [left..right], dp[left + 1][right] + taking the pile at left must be evaluated
                // dp[left + 1][right] stores the best score & opponent score for piles[left + 1..right]
                // .second + left pile is used as "this turn's score" for the current player
                val leftScore = piles[left] + dp[left + 1][right].second to dp[left + 1][right].first

                // similarly, dp[left][right - 1] + taking the pile at right must be evaluated
                // dp[left][right - 1] stores the best score & opponent score for piles[left..right - 1]
                // .second + right pile is used as "this turn's score" for the current player
                val rightScore = piles[right] + dp[left][right - 1].second to dp[left][right - 1].first

                // the best score for the current player is the one that maximizes the current player's score
                dp[left][right] = if (leftScore.first > rightScore.first) leftScore else rightScore
            }
        }

        return dp[0][n - 1].first > dp[0][n - 1].second
    }

    println(stoneGame(intArrayOf(5, 3, 4, 5))) // true
    println(stoneGame(intArrayOf(3, 7, 2, 3))) // true
}
