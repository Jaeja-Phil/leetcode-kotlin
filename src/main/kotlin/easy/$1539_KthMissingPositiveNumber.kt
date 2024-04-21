package easy

/**
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 *
 * Return the kth positive integer that is missing from this array.
 *
 * constraints:
 * - 1 <= arr.length <= 1000
 * - 1 <= arr[i] <= 1000
 * - 1 <= k <= 1000
 * - arr[i] < arr[j] for 1 <= i < j <= arr.length
 *
 * Example 1:
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,...]. The 5th missing positive integer is 9.
 *
 * Example 2:
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 */
fun main() {
    fun findKthPositive(arr: IntArray, k: Int): Int {
        var left = 0
        var right = arr.lastIndex
        while (left <= right) {
            val mid = left + (right - left) / 2
            val missing = arr[mid] - 1 - mid
            when {
                missing < k -> left = mid + 1
                else -> right = mid - 1
            }
        }

        return left + k
    }

    println(findKthPositive(intArrayOf(2,3,4,7,11), 5)) // 9
    println(findKthPositive(intArrayOf(1,2,3,4), 2)) // 6
    println(findKthPositive(intArrayOf(1,3), 5)) // 7
}
