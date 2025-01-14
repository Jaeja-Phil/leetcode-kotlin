package medium

/**
 * Given an array of integers nums, sort the array in ascending order and return it.
 *
 * You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest
 * space complexity possible.
 *
 * Constraints:
 * - 1 <= nums.length <= 5 * 10^4
 * - -5 * 10^4 <= nums[i] <= 5 * 10^4
 *
 * Example 1:
 * Input: nums = [5,2,3,1]
 * Output: [1,2,3,5]
 * Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), while the
 * positions of other numbers are changed (for example, 1 and 5).
 *
 * Example 2:
 * Input: nums = [5,1,1,2,0,0]
 * Output: [0,0,1,1,2,5]
 * Explanation: Note that the values of nums are not necessarily unique.
 */
fun main() {
    fun merge(array: IntArray, left: Int, mid: Int, right: Int) {
        val leftArray = array.sliceArray(left..mid)
        val rightArray = array.sliceArray(mid + 1..right)
        var i = 0
        var j = 0
        var k = left
        while (i < leftArray.size && j < rightArray.size) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i]
                i++
            } else {
                array[k] = rightArray[j]
                j++
            }
            k++
        }

        while (i < leftArray.size) {
            array[k++] = leftArray[i++]
        }

        while (j < rightArray.size) {
            array[k++] = rightArray[j++]
        }
    }


    fun mergeSort(nums: IntArray, left: Int, right: Int) {
        if (left == right) {
            return
        }

        val mid = left + (right - left) / 2
        mergeSort(nums, left, mid)
        mergeSort(nums, mid + 1, right)
        merge(nums, left, mid, right)
    }

    fun sortArray(nums: IntArray): IntArray {
        mergeSort(nums, 0, nums.lastIndex)
        return nums
    }

    println(sortArray(intArrayOf(5, 2, 3, 1)).contentToString()) // [1, 2, 3, 5]
    println(sortArray(intArrayOf(5, 1, 1, 2, 0, 0)).contentToString()) // [0, 0, 1, 1, 2, 5]
}