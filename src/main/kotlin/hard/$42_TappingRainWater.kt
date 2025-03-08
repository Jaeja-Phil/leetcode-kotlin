package hard

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water
 * it can trap after raining.
 *
 * Constraints:
 * - n == height.length
 * - 0 <= n <= 3 * 10^4
 * - 0 <= height[i] <= 10^5
 *
 * Example 1:
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * Example 2:
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 */
fun main() {
    fun trap(height: IntArray): Int {
        val n = height.size
        val maxLeft = IntArray(n)
        for (i in height.indices) {
            maxLeft[i] = when (i) {
                0 -> height[i]
                else -> maxOf(maxLeft[i - 1], height[i])
            }
        }

        val maxRight = IntArray(n)
        for (i in height.indices.reversed()) {
            maxRight[i] = when (i) {
                height.lastIndex -> height[i]
                else -> maxOf(maxRight[i + 1], height[i])
            }
        }

        var water = 0
        height.forEachIndexed { i, h ->
            val currWater = minOf(maxLeft[i], maxRight[i]) - h
            if (currWater > 0) {
                water += currWater
            }
        }

        return water
    }

    println(trap(intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1))) // 6
    println(trap(intArrayOf(4,2,0,3,2,5))) // 9
}