package medium

/**
 * Given a sorted array "arr", two integers "k" and "x", find the k closest elements to x in the array. The result should also
 * be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 * - |a - x| < |b - x|, or
 * - |a - x| == |b - x| and a < b.
 *
 * Constraints:
 * - 1 <= k <= arr.length
 * - 1 <= arr.length <= 10^4
 * - arr is sorted in ascending order.
 * - -10^4 <= arr[i], x <= 10^4
 *
 * Example 1:
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 *
 * Example 2:
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 */
fun main() {
    fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
//        // base case
//        if (arr.size <= k) return arr.toList()
//
//        var left = 0
//        var right = arr.lastIndex
//        // find where x is located OR x should be inserted
//        while (left <= right) {
//            val mid = (left + right) / 2
//            when {
//                arr[mid] > x -> right = mid - 1
//                arr[mid] < x -> left = mid + 1
//                else -> {
//                    left = mid
//                    break
//                }
//            }
//        }
//
//        if (left == 0) {
//            return arr.slice(0..< k)
//        }
//
//        // find k closest elements
//        var i = left
//        var j = left
//        while (j - i < k) {
//            if (i == 0) {
//                j++
//            } else if (j == arr.size) {
//                i--
//            } else {
//                if (x - arr[i - 1] <= arr[j] - x) {
//                    i--
//                } else {
//                    j++
//                }
//            }
//        }
//
//        return arr.slice(i ..< j)

        // binary search solution
        var left = 0
        var right = arr.size - k

        while (left < right) {
            val mid = (left + right) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return arr.sliceArray(left..<left + k).toList()
    }

    println(findClosestElements(intArrayOf(1, 2, 3, 4, 5), 4, 3)) // [1, 2, 3, 4]
    println(findClosestElements(intArrayOf(1, 2, 3, 4, 5), 4, -1)) // [1, 2, 3, 4]
}