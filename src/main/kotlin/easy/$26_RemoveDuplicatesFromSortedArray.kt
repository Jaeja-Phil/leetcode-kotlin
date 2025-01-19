package easy

/**
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique
 * element appears only once. The relative order of the elements should be kept the same. Then return the number of
 * unique elements in nums.
 *
 * Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
 * - Change the array nums such that the first k elements of nums contain the unique elements in the order they were
 *   present in nums initially. The remaining elements of nums are not important as well as the size of nums.
 * - Return k.
 *
 * Custom Judge:
 * The judge will test your solution with the following code:
 * ```
 * int[] nums = [...]; // Input array
 * int[] expectedNums = [...]; // The expected answer with correct length
 *
 * int k = removeDuplicates(nums); // Calls your implementation
 *
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 *     assert nums[i] == expectedNums[i];
 * }
 * ```
 *
 * If all assertions pass, then your solution will be accepted.
 *
 * Constraints:
 * - 0 <= nums.length <= 3 * 10^4
 * - -100 <= nums[i] <= 100
 * - nums is sorted in non-decreasing order.
 *
 * Example 1:
 * Input: nums = [1,1,2]
 * Output: 2, nums = [1,2,_]
 * Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
 *
 * Example 2:
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
 */
fun main() {
    fun removeDuplicates(nums: IntArray): Int {
        // Solution 1.
//        // base case
//        if (nums.size <= 1) return nums.size
//
//        var prevNum = nums[0]
//        var prevIndex = 1
//        for (i in 1 .. nums.lastIndex) {
//            val currNum = nums[i]
//            if (currNum != prevNum) {
//                prevNum = currNum
//                nums[prevIndex++] = currNum
//            }
//        }
//
//        return prevIndex

        // Solution 2.
        if (nums.size <= 1) return nums.size

        var left = 1
        for (right in 1 .. nums.lastIndex) {
            if (nums[right] != nums[right - 1]) {
                nums[left++] = nums[right]
            }
        }

        return left
    }

    val nums1 = intArrayOf(1, 1, 2)
    println(removeDuplicates(nums1)) // 2
    println(nums1.joinToString()) // 1, 2, _

    val nums2 = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
    println(removeDuplicates(nums2)) // 5
    println(nums2.joinToString()) // 0, 1, 2, 3, 4, _, _, _, _, _

}
