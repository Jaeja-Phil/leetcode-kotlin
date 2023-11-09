package medium

/**
 * Given a binary array "nums" and an integer "k", return the maximum number of
 * consecutive 1's in the array if you can flip at most k 0's.
 *
 * constraints:
 * - 1 <= nums.length <= 10^5
 * - nums[i] is either 0 or 1.
 * - 0 <= k <= nums.length
 *
 * Example 1:
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 *
 * Example 2:
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 */
fun main() {
    val res1 = `Max Consecutive Ones III`(intArrayOf(1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0), 2)
    println(res1) // 6

    val res2 = `Max Consecutive Ones III`(intArrayOf(0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1), 3)
    println(res2) // 10
}

fun `Max Consecutive Ones III`(nums: IntArray, k: Int): Int {
    /**
     * create two pointers for sliding window
     */
    var windowStart = 0
    var windowEnd = 0

    /**
     * create a variable to store the maximum number of consecutive 1's with at most k 0's flipped
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
         * if the current element is 0, decrement zeroCount
         */
        if (nums[windowEnd] == 0) {
            zeroCount++
        }

        /**
         * if the number of 0's in the window is greater than k
         */
        while (zeroCount > k) {
            /**
             * if the element at windowStart is 0, decrement zeroCount
             * and increment windowStart
             */
            if (nums[windowStart++] == 0) {
                zeroCount--
            }
        }

        /**
         * update maxCount
         */
        maxCount = maxOf(maxCount, windowEnd - windowStart + 1)

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
