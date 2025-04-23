package medium

/**
 * You are given an array of integers stones where stones[i] is the weight of the ith stone.
 *
 * We are playing a game with the stones. On each turn, we choose any two stones and smash them together. Suppose the
 * stones have weights x and y with x <= y. The result of this smash is:
 * - If x == y, both stones are destroyed, and
 * - If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
 *
 * At the end of the game, there is at most one stone left.
 *
 * Return the smallest possible weight of the left stone. If there are no stones left, return 0.
 *
 * Constraints:
 * - 1 <= stones.length <= 30
 * - 1 <= stones[i] <= 100
 *
 * Example 1:
 * Input: stones = [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * - We combine 2 and 4 to get 2, so the array converts to [2,7,1,8,1] then,
 * - We combine 7 and 8 to get 1, so the array converts to [2,1,1,1] then,
 * - We combine 2 and 1 to get 1, so the array converts to [1,1,1] then,
 * - We combine 1 and 1 to get 0, so the array converts to [1], then that's the value of the last stone.
 */
fun main() {
    fun lastStoneWeight(stones: IntArray): Int {
        /**
         * compute the total weight of stones and set target to half of the total weight
         * - why? because we want to find the closest sum to half of the total weight
         */
        val stoneSum = stones.sum()
        val target = stoneSum / 2
        val dp = IntArray(target + 1) // dp[i] = max achievable subset sum that does not exceed i

        /**
         * for each stone, we iterate from target down to stone
         * option 1: dont take this stone => dp[i]
         * option 2: take this stone => dp[i - stone] + stone
         * take the max of both options => dp[i] = maxOf(dp[i], dp[i - stone] + stone)
         * **Note**
         * - scanning backwards is important to avoid using the same stone multiple times
         *   (because we never reuse the updated dp[i] in the same iteration)
         */
        for (stone in stones) {
            for (i in target downTo stone) {
                dp[i] = maxOf(dp[i], dp[i - stone] + stone)
            }
        }

        /**
         * dp[target] holds the closest sum to half of the total weight
         * therefore, the smallest possible weight of the last stone is:
         * total weight - (2 * closest sum to half of the total weight)
         */
        return stoneSum - 2 * dp[target]
    }

    println(lastStoneWeight(intArrayOf(2, 7, 4, 1, 8, 1))) // 1
}