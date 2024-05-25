package easy

/**
 * You are given a sorted unique integer array nums.
 * A range [a,b] is the set of all integers from a to b (inclusive).
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of
 * nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but
 * not in nums.
 *
 * Each range [a,b] in the list should be output as: *
 * - "a->b" if a != b
 * - "a" if a == b
 *
 * Constraints:
 * - 0 <= nums.length <= 20
 * - -2^31 <= nums[i] <= 2^31 - 1
 * - All the values of nums are unique.
 * - nums is sorted in ascending order.
 *
 * Example 1:
 * Input: nums = [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 *
 * Example 2:
 * Input: nums = [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 */
fun main() {
    fun summaryRanges(nums: IntArray): List<String> {
        val result = mutableListOf<String>()
        var start = 0
        var end = 0
        while (end <= nums.lastIndex) {
            while (end < nums.lastIndex && nums[end] + 1 == nums[end + 1]) {
                end++
            }
            if (start == end) {
                result.add(nums[start].toString())
            } else {
                result.add("${nums[start]}->${nums[end]}")
            }
            end++
            start = end
        }
        return result
    }

    println(summaryRanges(intArrayOf(0, 1, 2, 4, 5, 7)).joinToString()) // Output: ["0->2","4->5","7"]
    println(summaryRanges(intArrayOf(0, 2, 3, 4, 6, 8, 9)).joinToString()) // Output: ["0","2->4","6","8->9"]
}