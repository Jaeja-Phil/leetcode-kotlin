package medium

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 *
 * Constraints:
 * - 1 <= nums.length <= 5 * 10^4
 * - -10^9 <= nums[i] <= 10^9
 *
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: [3]
 *
 * Example 2:
 * Input: nums = [1]
 * Output: [1]
 *
 * Example 3:
 * Input: nums = [1,2]
 * Output: [1,2]
 */
fun main() {
    fun majorityElement(nums: IntArray): List<Int> {
        val n = nums.size
        val countMap = hashMapOf<Int, Int>()

        for (num in nums) {
            countMap[num] = countMap.getOrDefault(num, 0) + 1
            if (countMap.size > 2) {
                val keysToRemove = mutableListOf<Int>()
                for (key in countMap.keys) {
                    countMap[key] = countMap[key]!! - 1
                    if (countMap[key] == 0) {
                        keysToRemove.add(key)
                    }
                }
                keysToRemove.forEach { countMap.remove(it) }
            }
        }

        val result = mutableListOf<Int>()
        for (num in countMap.keys) {
            if (nums.count { it == num } > n / 3) {
                result.add(num)
            }
        }
        return result
    }

    println(majorityElement(intArrayOf(3, 2, 3))) // [3]
    println(majorityElement(intArrayOf(1))) // [1]
    println(majorityElement(intArrayOf(1, 2))) // [1, 2]
}
