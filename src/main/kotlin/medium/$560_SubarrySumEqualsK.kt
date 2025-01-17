package medium

/**
 * Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to
 * k.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Constraints:
 * - 1 <= nums.length <= 2 * 10^4
 * - -1000 <= nums[i] <= 1000
 * - -10^7 <= k <= 10^7
 *
 * Example 1:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 */
fun main() {
    fun subarraySum(nums: IntArray, k: Int): Int {
        // Solution 1. (Brute force)
//        var count = 0
//        for (i in nums.indices) {
//            var sum = 0
//            for (j in i until nums.size) {
//                sum += nums[j]
//                if (sum == k) {
//                    count++
//                }
//            }
//        }
//
//        return count

        // Solution 2. (Prefix sum)
        var count = 0
        var currentSum = 0
        val prefixSums = hashMapOf<Int, Int>()
        prefixSums[0] = 1

        for (num in nums) {
            currentSum += num
            val diff = currentSum - k
            count += prefixSums.getOrDefault(diff, 0)
            prefixSums[currentSum] = prefixSums.getOrDefault(currentSum, 0) + 1
        }

        return count
    }

    println(subarraySum(intArrayOf(1, 1, 1), 2)) // 2
    println(subarraySum(intArrayOf(1, 2, 3), 3)) // 2
}