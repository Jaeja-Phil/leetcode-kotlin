package medium

/**
 * You have a sorted array of unique elements and an unknown size. You do not have access to the array but you can
 * use the ArrayReader interface to access it. You can call ArrayReader.get(i) that:
 * - returns the value at the ith index (0-indexed) of the secret array (i.e., secret[i]), or
 * - returns 2^31 - 1 if the i is out of the boundary of the array.
 *
 * You are also given an integer target.
 * Return the index k of the hidden array where secret[k] == target or return -1 otherwise.
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Constraints:
 * - 1 <= secret.length <= 10^4
 * - -10^4 <= secret[i], target <= 10^4
 * - secret is sorted in ascending order.
 *
 * Example 1:
 * Input: secret = [-1,0,3,5,9,12], target = 9
 * Output: 4
 *
 * Example 2:
 * Input: secret = [-1,0,3,5,9,12], target = 2
 * Output: -1
 */
fun main() {
    class ArrayReader(private val secret: IntArray) {
        fun get(index: Int): Int {
            return if (index < secret.size) secret[index] else Int.MAX_VALUE
        }
    }

    fun search(reader: ArrayReader, target: Int): Int {
        var left = 0
        var right = 9999
        while (left <= right) {
            val mid = (left + right) / 2
            val currNum = reader.get(mid)
            when {
                currNum == target -> return mid
                currNum < target -> left = mid + 1
                else -> right = mid - 1
            }
        }
        return -1
    }

    val reader = ArrayReader(intArrayOf(-1, 0, 3, 5, 9, 12))
    println(search(reader, 9)) // 4
//    println(search(reader, 2)) // -1

}