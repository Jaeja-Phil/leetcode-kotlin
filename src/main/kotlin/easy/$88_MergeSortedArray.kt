package easy

/**
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n,
 * representing the number of elements in nums1 and nums2 respectively.
 *
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 *
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
 * To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should
 * be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 *
 * Constraints:
 * - nums1.length == m + n
 * - nums2.length == n
 * - 0 <= m, n <= 200
 * - 1 <= m + n <= 200
 * - -10^9 <= nums1[i], nums2[j] <= 10^9
 *
 * Example 1:
 * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * Output: [1,2,2,3,5,6]
 * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
 * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
 *
 * Example 2:
 * Input: nums1 = [1], m = 1, nums2 = [], n = 0
 * Output: [1]
 * Explanation: The arrays we are merging are [1] and [].
 * The result of the merge is [1].
 *
 * Example 3:
 * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
 * Output: [1]
 * Explanation: The arrays we are merging are [] and [1].
 * The result of the merge is [1].
 */
fun main() {
    // solution 1.
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
        var nums1Idx = m - 1
        var nums2Idx = n - 1
        var currIdx = nums1.lastIndex
        while (nums1Idx >= 0 && nums2Idx >= 0) {
            val nums1CurrValue = nums1[nums1Idx]
            val nums2CurrValue = nums2[nums2Idx]
            nums1[currIdx] = when {
                nums1CurrValue > nums2CurrValue -> {
                    nums1Idx--
                    nums1CurrValue
                }
                else -> {
                    nums2Idx--
                    nums2CurrValue
                }

            }
            currIdx--
        }

        while (nums2Idx >= 0) {
            nums1[currIdx--] = nums2[nums2Idx--]
        }
    }

    // solution 2: https://leetcode.com/problems/merge-sorted-array/solutions/3155713/two-pointer-on-kotlin/?envType=study-plan-v2&envId=top-interview-150
    fun merge2(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
        var i = m - 1
        var j = n - 1
        var k = m + n - 1
        while (k >= 0) {
            nums1[k--] = when {
                i < 0 || nums1[i] < nums2[j] -> nums2[j--]
                else -> nums1[i--]
            }
        }
    }

    val nums1 = intArrayOf(1, 2, 3, 0, 0, 0)
    val m = 3
    val nums2 = intArrayOf(2, 5, 6)
    val n = 3
    merge(nums1, m, nums2, n)
    println(nums1.joinToString()) // [1, 2, 2, 3, 5, 6]

    val nums3 = intArrayOf(1)
    val m2 = 1
    val nums4 = intArrayOf()
    val n2 = 0
    merge(nums3, m2, nums4, n2)
    println(nums3.joinToString()) // [1]

    val nums5 = intArrayOf(0)
    val m3 = 0
    val nums6 = intArrayOf(1)
    val n3 = 1
    merge(nums5, m3, nums6, n3)
    println(nums5.joinToString()) // [1]

    val nums7 = intArrayOf(4, 5, 6, 0, 0, 0)
    val m4 = 3
    val nums8 = intArrayOf(1, 2, 3)
    val n4 = 3
    merge(nums7, m4, nums8, n4)
    println(nums7.joinToString()) // [1, 2, 3, 4, 5, 6]
}
