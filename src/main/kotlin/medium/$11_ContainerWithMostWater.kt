package medium

/**
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two
 * endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Notice that you may not slant the container.
 *
 * constraints:
 * - n == height.length
 * - 2 <= n <= 10^5
 * - 0 <= height[i] <= 10^4
 *
 * Example 1:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49 (between 8 (index 1) and 7 (index 8))
 *
 * Example 2:
 * Input: height = [1,1]
 * Output: 1 (between 1 (index 0) and 1 (index 1))
 */
fun main() {
    val res1 = `Container With Most Water`(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7))
    println(res1) // 49

    val res2 = `Container With Most Water`(intArrayOf(1, 1))
    println(res2) // 1
}

fun `Container With Most Water`(height: IntArray): Int {
        /**
         * create two pointers, one at the beginning and one at the end
         */
        var left = 0
        var right = height.lastIndex

        /**
         * create a variable to hold the max area
         */
        var maxArea = 0

        /**
         * loop through the array
         */
        while (left < right) { // while the pointers haven't crossed each other...
            /**
             * calculate the window size
             */
            val windowSize = right - left

            /**
             * get the lower of two points
             * - this is because the water will be limited by the lower of the two points
             */
            val minHeight = minOf(height[left], height[right])

            /**
             * calculate the area
             */
            val area = windowSize * minHeight

            /**
             * if the area is greater than the max area, update the max area
             */
            maxArea = maxOf(maxArea, area)

            /**
             * if the left pointer is the lower of the two points, increment the left pointer
             * else, decrement the right pointer
             */
            if (height[left] < height[right]) {
                left++
            } else {
                right--
            }
        }

        /**
         * return the max area achieved so far
         */
        return maxArea
}