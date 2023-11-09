package medium

/**
 * Given a binary array nums, you should delete one element from it.
 *
 * Return the size of the longest non-empty subarray containing only 1's in the resulting array.
 * Return 0 if there is no such subarray.
 *
 * constraints:
 * - 1 <= nums.length <= 10^5
 * - nums[i] is either 0 or 1.
 *
 * Example 1:
 * Input: nums = [1,1,0,1]
 * Output: 3
 * Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
 *
 * Example 2:
 * Input: nums = [0,1,1,1,0,1,1,0,1]
 * Output: 5
 * Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
 *
 * Example 3:
 * Input: nums = [1,1,1]
 * Output: 2
 * Explanation: You must delete one element.
 */
fun main() {
    val res1 = `Longest Subarray of 1's After Deleting One Element`(intArrayOf(1, 1, 0, 1))
    println(res1) // 3

    val res2 = `Longest Subarray of 1's After Deleting One Element`(intArrayOf(0, 1, 1, 1, 0, 1, 1, 0, 1))
    println(res2) // 5

    val res3 = `Longest Subarray of 1's After Deleting One Element`(intArrayOf(1, 1, 1))
    println(res3) // 2
}

fun `Longest Subarray of 1's After Deleting One Element`(nums: IntArray): Int {
        /**
         * create two pointers for sliding window
         */
        var windowStart = 0
        var windowEnd = 0

        /**
         * create a variable to store the maximum number of consecutive 1's
         */
        var maxCount = 0

        /**
         * create a variable to store the number of 0's in the window
         */
        var zeroCount = 0

        /**
         * loop through the array
         */
        while (windowEnd < nums.size) {
            /**
             * if the current element is 0, increment zeroCount
             */
            if (nums[windowEnd] == 0) {
                zeroCount++
            }

            /**
             * if the number of 0's in the window is greater than 1
             */
            while (zeroCount > 1) {
                /**
                 * if the element at windowStart is 0, decrement zeroCount
                 */
                if (nums[windowStart] == 0) {
                    zeroCount--
                }

                /**
                 * increment windowStart
                 */
                windowStart++
            }

            /**
             * update maxCount
             * - no need to add 1 because we are not including the deleted element
             */
            maxCount = maxOf(maxCount, windowEnd - windowStart)

            /**
             * increment windowEnd
             */
            windowEnd++
        }

        /**
         * return maxCount
         */
        return maxCount
}
