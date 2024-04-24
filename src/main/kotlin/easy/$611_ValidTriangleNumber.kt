package easy

/**
 * Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we
 * take them as side lengths of a triangle.
 *
 * Constraints:
 * - 1 <= nums.length <= 1000
 * - 0 <= nums[i] <= 1000
 *
 * Example1:
 * Input: nums = [2,2,3,4]
 * Output: 3
 *
 * Example2:
 * Input: nums = [4,2,3,4]
 * Output: 4
 */
fun main() {
    fun triangleNumber(nums: IntArray): Int {
        // sort the side's length
        nums.sort()

        // valid triangle means largest side should be smaller than other two sides length combined
        var largestSideIdx = nums.lastIndex
        var count = 0
        while (largestSideIdx > 1) {
            var left = 0
            var right = largestSideIdx - 1
            while (left < right) {
                if (nums[left] + nums[right] > nums[largestSideIdx]) {
                    count += right - left
                    right--
                } else {
                    left++
                }
            }
            largestSideIdx--
        }


        return count
    }

    println(triangleNumber(intArrayOf(2, 2, 3, 4))) // 3
    println(triangleNumber(intArrayOf(4, 2, 3, 4))) // 4
}
