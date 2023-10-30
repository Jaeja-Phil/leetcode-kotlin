package easy

/**
 * Given an integer array nums, move all 0's to the end of it while maintaining the
 * relative order of the non-zero elements.
 * Note that you must do this in-place without making a copy of the array.
 *
 * constraints:
 * - 1 <= nums.length <= 10^4
 * - -2^31 <= nums[i] <= 2^31 - 1
 *
 * Example 1:
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 *
 * Example 2:
 * Input: nums = [0]
 * Output: [0]
 */
fun main() {
    var nums = intArrayOf(0, 1, 0, 3, 12)
    `Move Zeroes`(nums)
    println(nums.contentToString()) // [1, 3, 12, 0, 0]

    nums = intArrayOf(0)
    `Move Zeroes`(nums)
    println(nums.contentToString()) // [0]
}

/**
 * function to check if the value at the given index is zero
 * - could also make it more general like...
 * fun IntArray.isValueAt(index: Int, value: Int): Boolean {
 *   if (index >= this.size || index < 0) return false
 *   return this[index] == value
 * }
 */
fun IntArray.isZeroAt(index: Int): Boolean {
    if (index >= this.size || index < 0) return false
    return this[index] == 0
}
fun `Move Zeroes`(nums: IntArray) {
    /**
     * create a variable to keep track of the index of the non-zero value's index
     */
    var nonZeroIndex = 0

    /**
     * iterate through the array
     */
    for (i in nums.indices) {
        /**
         * if the value at the current index is not zero, then set the value at the
         * non-zero index to the value at the current index and increment the non-zero index
         * (this will keep track of the index of the non-zero values)
         */
        if (!nums.isZeroAt(i)) {
            nums[nonZeroIndex++] = nums[i]
        }
    }

    /**
     * once the array has been iterated through, set all the values from the non-zero index
     * to the end of the array to zero
     */
    for (i in nonZeroIndex..< nums.size) {
        nums[i] = 0
    }
}

