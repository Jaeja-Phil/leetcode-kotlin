package easy

/**
 * There is a biker going on a road trip. The road trip consists of n + 1 points at different altitudes.
 * The biker starts his trip on point 0 with altitude equal 0.
 *
 * You are given an integer array gain of length n where gain[i] is the net gain in altitude between points
 * i and i + 1 for all (0 <= i < n). Return the highest altitude of a point.
 *
 * constraints:
 * - n == gain.length
 * - 1 <= n <= 100
 * - -100 <= gain[i] <= 100
 *
 * Example 1:
 * Input: gain = [-5,1,5,0,-7]
 * Output: 1
 * Explanation: The altitudes are [0,-5,-4,1,1,-6]. The highest is 1.
 *
 * Example 2:
 * Input: gain = [-4,-3,-2,-1,4,3,2]
 * Output: 0
 * Explanation: The altitudes are [0,-4,-7,-9,-10,-6,-3,-1]. The highest is 0.
 */
fun main() {
    val res1 = `Find the Highest Altitude`(intArrayOf(-5, 1, 5, 0, -7))
    println(res1) // 1

    val res2 = `Find the Highest Altitude`(intArrayOf(-4, -3, -2, -1, 4, 3, 2))
    println(res2) // 0
}

fun `Find the Highest Altitude`(gain: IntArray): Int {
        /**
         * create two variables to store the
         * - maximum altitude so far
         * - current altitude
         */
        var maxAltitude = 0
        var currAltitude = 0

        /**
         * loop through the array
         */
        for (i in gain.indices) {
            /**
             * add the current gain to the current altitude
             */
            currAltitude += gain[i]

            /**
             * update the maximum altitude
             */
            maxAltitude = maxOf(maxAltitude, currAltitude)
        }

        /**
         * return the maximum altitude gained over the course of the whole ride
         */
        return maxAltitude
}