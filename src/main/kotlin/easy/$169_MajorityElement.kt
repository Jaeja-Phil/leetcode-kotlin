package easy

/**
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element
 * always exists in the array.
 *
 * constraints:
 * - n == nums.length
 * - 1 <= n <= 5 * 10^4
 * - -10^9 <= nums[i] <= 10^9
 *
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 */
fun main() {
    fun majorityElement(nums: IntArray): Int {
        // boer moore voting algorithm
        var candidate = nums[0]
        var count = 1
        for (i in 1 ..< nums.size) {
            if (nums[i] == candidate) {
                count++
            } else {
                count--
                if (count == 0) {
                    candidate = nums[i]
                    count = 1
                }
            }
        }

        return candidate
    }

    fun majorityElementMySolution(nums: IntArray): Int {
        nums.sort()
        return nums[nums.size / 2]
    }

    println(majorityElement(intArrayOf(3, 2, 3))) // 3
    println(majorityElement(intArrayOf(2, 2, 1, 1, 1, 2, 2))) // 2
    println(majorityElement(intArrayOf(1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3))) // 1
}
