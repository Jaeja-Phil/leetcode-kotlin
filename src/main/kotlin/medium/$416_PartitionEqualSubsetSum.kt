package medium

/**
 * Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the
 * elements in both subsets is equal or false otherwise.
 *
 * Constraints:
 * - 1 <= nums.length <= 200
 * - 1 <= nums[i] <= 100
 *
 * Example 1:
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 * Example 2:
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 */
fun main() {
    fun canPartition(nums: IntArray): Boolean {
        // Solution 1. Dynamic Programming
//        val sum = nums.sum()
//        if (sum % 2 != 0) return false
//
//        val target = sum / 2
//        val dp = BooleanArray(target + 1)
//        dp[0] = true
//
//        for (num in nums) {
//            for (i in target downTo num) {
//                dp[i] = dp[i] || dp[i - num]
//                if (dp[target]) return true
//            }
//        }
//
//        return dp[target]

        // Solution 2. set
        val sum = nums.sum()
        if (sum % 2 != 0) return false

        val target = sum / 2
        val set = mutableSetOf(0)

        for (num in nums) {
            val newSet = mutableSetOf<Int>()
            for (s in set) {
                val newSum = s + num
                if (newSum == target) return true
                if (newSum < target) newSet.add(newSum)
            }
            set.addAll(newSet)
        }

        return false
    }

    println(canPartition(intArrayOf(1, 5, 11, 5))) // true
    println(canPartition(intArrayOf(1, 2, 3, 5))) // false
}
