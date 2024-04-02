package medium

/**
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that
 * answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is
 * no future day for which this is possible, keep answer[i] == 0 instead.
 *
 * constraints:
 * - 1 <= temperatures.length <= 10^5
 * - 30 <= temperatures[i] <= 100
 *
 * Example 1:
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 *
 * Example 2:
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 *
 * Example 3:
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 */
fun main() {
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val stack = mutableListOf<Int>()
        val result = IntArray(temperatures.size)
        for (i in temperatures.indices) {
            while (stack.isNotEmpty() && temperatures[i] > temperatures[stack.last()]) {
                val index = stack.removeLast()
                result[index] = i - index
            }
            stack.add(i)
        }
        return result
    }

    println(dailyTemperatures(intArrayOf(73,74,75,71,69,72,76,73)).contentToString()) // [1,1,4,2,1,1,0,0]
    println(dailyTemperatures(intArrayOf(30,40,50,60)).contentToString()) // [1,1,1,0]
    println(dailyTemperatures(intArrayOf(30,60,90)).contentToString()) // [1,1,0]
}