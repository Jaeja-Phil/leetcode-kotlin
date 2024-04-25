package medium

/**
 * You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.
 *
 * You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1]
 * (inclusive) and returns it. The probability of picking an index i is w[i] / sum(w).
 *
 * For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), and the
 * probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
 *
 * constraints:
 * - 1 <= w.length <= 10000
 * - 1 <= w[i] <= 10^5
 * - pickIndex will be called at most 10000 times.
 *
 * Example 1:
 * Input:
 * ["Solution", "pickIndex"]
 * [[[1]], []]
 * Output:
 * [null, 0]
 * Explanation:
 * Solution solution = new Solution([1]);
 * solution.pickIndex(); // return 0. The only option is to return 0 since there is only one element in w.
 *
 * Example 2:
 * Input:
 * ["Solution", "pickIndex", "pickIndex", "pickIndex", "pickIndex", "pickIndex"]
 * [[[1, 3]], [], [], [], [], []]
 * Output:
 * [null, 1, 1, 1, 1, 0]
 * Explanation:
 * Solution solution = new Solution([1, 3]);
 * solution.pickIndex(); // return 1. It's returning the second element (index = 1) that has a probability of 3/4.
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 0. It's returning the first element (index = 0) that has a probability of 1/4.
 */
fun main() {
    class Solution(w: IntArray) {
        private val accumulatedWeights = IntArray(w.size)

        init {
            if (w.isEmpty()) throw RuntimeException("must have more than 1 element")

            var sum = 0
            w.forEachIndexed { index, weight ->
                sum += weight
                accumulatedWeights[index] = sum
            }
        }

        fun pickIndex(): Int {
            val randInt = (1..accumulatedWeights.last()).random()
            return findRightMostIndex(randInt)
        }

        fun findRightMostIndex(target: Int): Int {
            var left = 0
            var right = accumulatedWeights.lastIndex
            while (left <= right) {
                val mid = (left + right) / 2
                val currentRange = when {
                    mid == 0 -> 0..accumulatedWeights[mid]
                    else -> accumulatedWeights[mid - 1] + 1..accumulatedWeights[mid]
                }
                if (target in currentRange) return mid

                if (target < currentRange.first) {
                    right = mid - 1
                } else {
                    left = mid + 1
                }
            }

            return left
        }
    }

//    val solution = Solution(intArrayOf(1))
//    println(solution.pickIndex()) // 0
//
//    val solution2 = Solution(intArrayOf(1, 3))
//    println(solution2.pickIndex()) // 1
//    println(solution2.pickIndex()) // 1
//    println(solution2.pickIndex()) // 1
//    println(solution2.pickIndex()) // 1
//    println(solution2.pickIndex()) // 0

    val solution3 = Solution(intArrayOf(3, 14, 1, 7))
    println(solution3.pickIndex())
}