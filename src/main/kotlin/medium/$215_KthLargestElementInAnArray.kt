package medium

import java.util.PriorityQueue

/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * constraints:
 * - 1 <= k <= nums.length <= 10^5
 * - -10^4 <= nums[i] <= 10^4
 *
 * Example 1:
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 *
 * Example 2:
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 */
fun main() {
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val minHeap = PriorityQueue<Int>()
        for (num in nums) {
            minHeap.add(num)
            println(minHeap)
            if (minHeap.size > k) {
                minHeap.poll()
            }
        }
        return minHeap.poll()
    }

    val nums = intArrayOf(3, 2, 1, 5, 6, 4)
    val k = 2
    println(findKthLargest(nums, k)) // 5

    val nums2 = intArrayOf(3, 2, 3, 1, 2, 4, 5, 5, 6)
    val k2 = 4
    println(findKthLargest(nums2, k2)) // 4

}