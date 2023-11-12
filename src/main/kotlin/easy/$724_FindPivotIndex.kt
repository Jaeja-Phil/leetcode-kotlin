package easy

/**
 * Given an array of integers nums, calculate the pivot index of this array.
 *
 * The pivot index is the index where the sum of all the numbers strictly to the left
 * of the index is equal to the sum of all the numbers strictly to the index's right.
 *
 * If the index is on the left edge of the array, then the left sum is 0 because there are
 * no elements to the left. This also applies to the right edge of the array.
 *
 * Return the leftmost pivot index. If no such index exists, return -1.
 *
 * constraints:
 * - 1 <= nums.length <= 10^4
 * - -1000 <= nums[i] <= 1000
 *
 * Example 1:
 * Input: nums = [1,7,3,6,5,6]
 * Output: 3
 * Explanation:
 * The pivot index is 3.
 * Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
 * Right sum = nums[4] + nums[5] = 5 + 6 = 11
 *
 * Example 2:
 * Input: nums = [1,2,3]
 * Output: -1
 * Explanation:
 * There is no index that satisfies the conditions in the problem statement.
 *
 * Example 3:
 * Input: nums = [2,1,-1]
 * Output: 0
 * Explanation:
 * The pivot index is 0.
 * Left sum = 0 (no elements to the left of index 0)
 * Right sum = nums[1] + nums[2] = 1 + -1 = 0
 *
 */
fun main() {
    val res1 = `Find Pivot Index`(intArrayOf(1, 7, 3, 6, 5, 6))
    println(res1) // 3

    val res2 = `Find Pivot Index`(intArrayOf(1, 2, 3))
    println(res2) // -1

    val res3 = `Find Pivot Index`(intArrayOf(2, 1, -1))
    println(res3) // 0
}

fun `Find Pivot Index`(nums: IntArray): Int {
    /**
     * create a variable to store the sum of all elements in the array
     */
    val sum = nums.sum()

    /**
     * create a variable to store the left sum
     */
    var leftSum = 0

    /**
     * loop through the array
     */
    for (i in nums.indices) {
        /**
         * if the left sum is equal to the sum of all elements in the array
         * minus the current element and the left sum
         */
        if (leftSum == sum - nums[i] - leftSum) {
            /**
             * return the current index
             */
            return i
        }

        /**
         * add the current element to the left sum
         */
        leftSum += nums[i]
    }

    /**
     * return -1 if no such index exists
     */
    return -1
}
