package medium

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and
 * j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Constraints:
 * - 3 <= nums.length <= 3000
 * - -10^5 <= nums[i] <= 10^5
 *
 * Example 1:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 *
 * Example 2:
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 *
 * Example 3:
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 */
fun main() {
    fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort()
        val result = mutableListOf<List<Int>>()
        var first = 0
        while (first < nums.size - 2) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                first++
                continue
            }
            var left = first + 1
            var right = nums.lastIndex
            while (left < right) {
                val sum = nums[first] + nums[left] + nums[right]
                when {
                    sum == 0 -> {
                        result.add(listOf(nums[first], nums[left], nums[right]))
                        while (left < right && nums[left] == nums[left + 1]) left++
                        while (left < right && nums[right] == nums[right - 1]) right--
                        left++
                        right--
                    }
                    sum < 0 -> left++
                    else -> right--
                }
            }
            first++
        }


        return result
    }

    val input = intArrayOf(-1, 0, 1, 2, -1, -4)
    println(threeSum(input)) // [[-1,-1,2],[-1,0,1]]

    val input2 = intArrayOf(0, 1, 1)
    println(threeSum(input2)) // []

    val input3 = intArrayOf(0, 0, 0)
    println(threeSum(input3)) // [[0,0,0]]
}