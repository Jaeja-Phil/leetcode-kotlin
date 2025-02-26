package medium

/**
 * Given an integer array arr, return the length of a maximum size turbulent subarray of arr.
 * A subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.
 * More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is said to be turbulent if and only if:
 * - For i <= k < j:
 *   - arr[k] > arr[k + 1] when k is odd, and
 *   - arr[k] < arr[k + 1] when k is even.
 * - Or, for i <= k < j:
 *   - arr[k] > arr[k + 1] when k is even, and
 *   - arr[k] < arr[k + 1] when k is odd.
 *
 * Constraints:
 * - 1 <= arr.length <= 10^4
 * - 0 <= arr[i] <= 10^9
 *
 * Example 1:
 * Input: arr = [9,4,2,10,7,8,8,1,9]
 * Output: 5
 * Explanation: arr[1] > arr[2] < arr[3] > arr[4] < arr[5]
 *
 * Example 2:
 * Input: arr = [4,8,12,16]
 * Output: 2
 *
 * Example 3:
 * Input: arr = [100]
 * Output: 1
 */
fun main() {
    fun maxTurbulenceSize(arr: IntArray): Int {
        var left = 0
        var right = 1
        var res = 1
        var prev = ""

        while (right < arr.size) {
            // if previous element was greater and the sign was not flipped
            if (arr[right - 1] > arr[right] && prev != ">") {
                // flip the sign
                prev = ">"
                // update the result
                res = maxOf(res, right - left + 1)
                // move the right pointer
                right++
                // if previous element was smaller and the sign was not flipped
            } else if (arr[right - 1] < arr[right] && prev != "<") {
                // flip the sign
                prev = "<"
                // update the result
                res = maxOf(res, right - left + 1)
                // move the right pointer
                right++
            } else {
                // if not the above two cases, check if the current element is equal to the previous element
                // if so, it means we can
                right = if (arr[right - 1] == arr[right]) right + 1 else right
                // move the left pointer
                // why right - 1? because we need to check the last two elements
                left = right - 1
                prev = ""
            }
        }

        return res
    }

    println(maxTurbulenceSize(intArrayOf(9, 4, 2, 10, 7, 8, 8, 1, 9))) // 5
    println(maxTurbulenceSize(intArrayOf(4, 8, 12, 16))) // 2
    println(maxTurbulenceSize(intArrayOf(100))) // 1
}
