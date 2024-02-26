package medium

import java.util.*

/**
 * You are given two 0-indexed integer arrays nums1 and nums2 of equal length n and a positive integer k.
 * You must choose a subsequence of indices from nums1 of length k.
 *
 * For chosen indices i0, i1, ..., ik - 1, your score is defined as:
 * - The sum of the selected elements from nums1 multiplied with the minimum of the selected elements from nums2.
 * - It can defined simply as: (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]).
 *
 * Return the maximum possible score.
 *
 * A subsequence of indices of an array is a set that can be derived from the set {0, 1, ..., n-1} by deleting some or
 * no elements.
 *
 * constraints:
 * - n == nums1.length == nums2.length
 * - 1 <= n <= 10^5
 * - 0 <= nums1[i], nums2[i] <= 10^5
 * - 1 <= k <= n
 *
 * Example 1:
 * Input: nums1 = [1, 3, 3, 2], nums2 = [2, 1, 3, 4], k = 3
 * Output: 12
 * Explanation:
 * 4 possible subsequence scores are:
 * - We choose the indices 0, 1, and 2 with score = (1+3+3) * min(2,1,3) = 7.
 * - We choose the indices 0, 1, and 3 with score = (1+3+2) * min(2,1,4) = 6.
 * - We choose the indices 0, 2, and 3 with score = (1+3+2) * min(2,3,4) = 12.
 * - We choose the indices 1, 2, and 3 with score = (3+3+2) * min(1,3,4) = 8.
 * Therefore, we return the max score, which is 12.
 *
 * Example 2:
 * Input: nums1 = [4,2,3,1,1], nums2 = [7,5,10,9,6], k = 1
 * Output: 30
 * Explanation:
 * Choosing index 2 is optimal: nums1[2] * nums2[2] = 3 * 10 = 30 is the maximum possible score.
 */
fun main() {
    fun maxScore(nums1: IntArray, nums2: IntArray, k: Int): Long {
        /**
         * we need to find the maximum sum of k elements from nums1 and the minimum of k elements from nums2
         */
        val pairs = mutableListOf<Pair<Int, Int>>()
        for (i in nums1.indices) {
            pairs.add(Pair(nums1[i], nums2[i]))
        }
        pairs.sortByDescending { it.second }

        val minHeap = PriorityQueue<Int>()
        var sum = 0L
        var result = 0L

        for (i in 0 ..< k) {
            sum += pairs[i].first
            minHeap.add(pairs[i].first)
        }
        result = sum * pairs[k - 1].second

        for (i in k ..< pairs.size) {
            if (pairs[i].first > minHeap.peek()) {
                sum -= minHeap.poll()
                sum += pairs[i].first
                minHeap.add(pairs[i].first)
                result = Math.max(result, sum * pairs[i].second)
            }
        }

        return result
    }

    val nums1 = intArrayOf(1, 3, 3, 2)
    val nums2 = intArrayOf(2, 1, 3, 4)
    val k = 3
    println(maxScore(nums1, nums2, k)) // 12

    val nums3 = intArrayOf(4, 2, 3, 1, 1)
    val nums4 = intArrayOf(7, 5, 10, 9, 6)
    val k2 = 1
    println(maxScore(nums3, nums4, k2)) // 30
}