package medium

/**
 * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]]
 * such that:
 * - 0 <= a, b, c, d < n
 * - a, b, c, and d are distinct.
 * - nums[a] + nums[b] + nums[c] + nums[d] == target
 *
 * You may return the answer in any order.
 *
 * Constraints:
 * - 1 <= nums.length <= 200
 * - -10^9 <= nums[i] <= 10^9
 * - -10^9 <= target <= 10^9
 *
 * Example 1:
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * Example 2:
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 */
fun main() {
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        // sort the array
        nums.sort()

        val result = mutableListOf<List<Int>>()
        val quad = mutableListOf<Int>()

        fun kSum(k: Int, start: Int, target: Long) {
            if (k != 2) {
                for (i in start..<nums.size - k + 1) {
                    if (i > start && nums[i] == nums[i - 1]) continue

                    quad.add(nums[i])
                    kSum(k - 1, i + 1, target - nums[i])
                    quad.removeAt(quad.lastIndex)
                }

                return
            }

            var left = start
            var right = nums.lastIndex
            while (left < right) {
                val sum = nums[left] + nums[right]
                when {
                    sum < target -> left++
                    sum > target -> right--
                    else -> {
                        result.add(quad + listOf(nums[left], nums[right]))
                        while (left < right && nums[left] == nums[left + 1]) left++
                        while (left < right && nums[right] == nums[right - 1]) right--
                        left++
                        right--
                    }
                }
            }
        }

        kSum(4, 0, target.toLong())

        return result
    }

    println(fourSum(intArrayOf(1, 0, -1, 0, -2, 2), 0)) // [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
    println(fourSum(intArrayOf(2, 2, 2, 2, 2), 8)) // [[2,2,2,2]]
    println(fourSum(intArrayOf(1000000000,1000000000,1000000000,1000000000), -294967296)) // [[2,2,2,2]]
}