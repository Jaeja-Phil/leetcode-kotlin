package easy

/**
 * Given an integer array nums, handle multiple queries of the following type:
 * - Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 *
 * Implement the NumArray class:
 * - NumArray(int[] nums) Initializes the object with the integer array nums.
 * - int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive
 *   (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 *
 * Constraints:
 * - 1 <= nums.length <= 10^4
 * - -10^5 <= nums[i] <= 10^5
 * - 0 <= left <= right < nums.length
 * - At most 10^4 calls will be made to sumRange.
 *
 * Example 1:
 * Input
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[[-2, 0, 3, -5, 2, -1]]], [0, 2], [2, 5], [0, 5]]
 *
 * Output
 * [null, 1, -1, -3]
 *
 * Explanation
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
 * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
 * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 */
fun main() {
    class NumArray(nums: IntArray) {
        private val prefixSum = IntArray(nums.size + 1)

        init {
            for (i in nums.indices) {
                prefixSum[i + 1] = prefixSum[i] + nums[i]
            }
        }

        fun sumRange(left: Int, right: Int): Int {
            return prefixSum[right + 1] - prefixSum[left]
        }
    }

    val numArray = NumArray(intArrayOf(-2, 0, 3, -5, 2, -1))
    println(numArray.sumRange(0, 2)) // 1
    println(numArray.sumRange(2, 5)) // -1
    println(numArray.sumRange(0, 5)) // -3
}