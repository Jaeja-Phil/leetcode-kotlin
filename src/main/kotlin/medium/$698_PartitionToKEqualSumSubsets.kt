package medium

/**
 * Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty
 * subsets whose sums are all equal.
 *
 * Constraints:
 * - 1 <= k <= nums.length <= 16
 * - 1 <= nums[i] <= 10^4
 * - The frequency of each element is in the range [1, 4].
 *
 * Example 1:
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: true
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2, 3), (2, 3) with equal sums.
 *
 * Example 2:
 * Input: nums = [1, 2, 3, 4], k = 3
 * Output: false
 */
fun main() {
    fun canPartitionKSubsets(nums: IntArray, k: Int): Boolean {
        // Solution 1.
//        if (k == 1) {
//            return true
//        }
//
//        val totalSum = nums.sum()
//        if (totalSum % k != 0) {
//            return false
//        }
//        val targetSum = totalSum / k
//        val sums = IntArray(k)
//        nums.sortDescending()
//
//        fun dfs(index: Int): Boolean {
//            if (index == nums.size) {
//                return sums.all { it == targetSum }
//            }
//
//            for (i in sums.indices) {
//                if (sums[i] + nums[index] > targetSum) {
//                    continue
//                }
//
//                sums[i] += nums[index]
//                if (dfs(index + 1)) {
//                    return true
//                }
//                sums[i] -= nums[index]
//            }
//
//            return false
//        }
//
//        return dfs(0)

        // Solution 2.
        if (k == 1) {
            return true
        }

        val totalSum = nums.sum()
        if (totalSum % k != 0) {
            return false
        }
        val targetSum = totalSum / k
        val visited = BooleanArray(nums.size)
        nums.sortDescending()

        fun dfs(start: Int, k: Int, currentSum: Int): Boolean {
            if (k == 0) {
                return true
            }

            if (currentSum == targetSum) {
                return dfs(0, k - 1, 0)
            }

            for (i in start..<nums.size) {
                if (visited[i] || currentSum + nums[i] > targetSum) {
                    continue
                }

                visited[i] = true
                if (dfs(i + 1, k, currentSum + nums[i])) {
                    return true
                }
                visited[i] = false
            }

            return false
        }

        return dfs(0, k, 0)
    }

    val nums1 = intArrayOf(4, 3, 2, 3, 5, 2, 1)
    val k1 = 4
    println(canPartitionKSubsets(nums1, k1)) // true

    val nums2 = intArrayOf(1, 2, 3, 4)
    val k2 = 3
    println(canPartitionKSubsets(nums2, k2)) // false
}