package medium

/**
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such
 * that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where
 * 1 <= index1 < index2 <= numbers.length.
 *
 * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of
 * length 2.
 *
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 *
 * Your solution must use only constant extra space.
 *
 * Constraints:
 * - 2 <= numbers.length <= 3 * 10^4
 * - -10^9 <= numbers[i] <= 10^9
 * - numbers is sorted in non-decreasing order.
 * - -1000 <= target <= 1000
 * - The tests are generated such that there is exactly one solution.
 *
 * Example 1:
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore, the output is [1,2].
 *
 * Example 2:
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 * Explanation: The sum of 2 and 4 is 6. Therefore, the output is [1,3].
 *
 * Example 3:
 * Input: numbers = [-1,0], target = -1
 * Output: [1,2]
 * Explanation: The sum of -1 and 0 is -1. Therefore, the output is [1,2].
 */
fun main() {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        // Solution 1.
//        var left = 0
//        var right = numbers.lastIndex
//        while (left < right) {
//            val sum = numbers[left] + numbers[right]
//            when {
//                sum < target -> left++
//                sum > target -> right--
//                else -> return intArrayOf(left + 1, right + 1)
//            }
//        }
//
//        return intArrayOf(-1, -1)

        // Solution 2.
        val map = mutableMapOf<Int, Int>() // key: number, value: index
        map[numbers[0]] = 1
        for (i in 1 .. numbers.lastIndex) {
            val num = numbers[i]
            val diff = target - num
            if (map.containsKey(diff)) {
                return intArrayOf(map[diff]!!, i + 1)
            }
            map[num] = i + 1
        }

        return intArrayOf(-1, -1)
    }

    val tests = listOf(
        Pair(intArrayOf(2, 7, 11, 15), 9), // [1, 2]
        Pair(intArrayOf(2, 3, 4), 6), // [1, 3]
        Pair(intArrayOf(-1, 0), -1) // [1, 2]
    )
    tests.forEach { test -> println(twoSum(test.first, test.second).contentToString()) }
}