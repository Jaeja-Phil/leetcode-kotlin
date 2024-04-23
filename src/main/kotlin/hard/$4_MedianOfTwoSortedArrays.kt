package hard

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 *
 * constraints:
 * - nums1.length == m
 * - nums2.length == n
 * - 0 <= m <= 1000
 * - 0 <= n <= 1000
 * - 1 <= m + n <= 2000
 * - -10^6 <= nums1[i], nums2[i] <= 10^6
 *
 * Example 1:
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 *
 * Example 2:
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 */
fun main() {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        // make sure nums1 is the smaller array
        if (nums1.size > nums2.size) {
            return findMedianSortedArrays(nums2, nums1)
        }

        // base case
        if (nums1.isEmpty() && nums2.isEmpty()) {
            return 0.0
        } else if (nums1.isEmpty()) {
            return when {
                nums2.size % 2 == 0 -> (nums2[nums2.size / 2 - 1] + nums2[nums2.size / 2]) / 2.0
                else -> nums2[nums2.size / 2].toDouble()
            }
        } else if (nums2.isEmpty()) {
            return when {
                nums1.size % 2 == 0 -> (nums1[nums1.size / 2 - 1] + nums1[nums1.size / 2]) / 2.0
                else -> nums1[nums1.size / 2].toDouble()
            }
        }

        val n1Length = nums1.size
        val n2Length = nums2.size
        val totalLength = n1Length + n2Length
        val target = (totalLength + 1) / 2 // the index of the median element
        var low = 0
        var high = n1Length
        while (true) {
            val partitionN1 = (low + high) / 2
            val partitionN2 = target - partitionN1

            val maxLeftN1 = if (partitionN1 == 0) Int.MIN_VALUE else nums1[partitionN1 - 1]
            val minRightN1 = if (partitionN1 == n1Length) Int.MAX_VALUE else nums1[partitionN1]
            val maxLeftN2 = if (partitionN2 == 0) Int.MIN_VALUE else nums2[partitionN2 - 1]
            val minRightN2 = if (partitionN2 == n2Length) Int.MAX_VALUE else nums2[partitionN2]

            if (maxLeftN1 <= minRightN2 && maxLeftN2 <= minRightN1) {
                return when {
                    totalLength % 2 == 0 -> (maxLeftN1.coerceAtLeast(maxLeftN2) + minRightN1.coerceAtMost(minRightN2)) / 2.0
                    else -> maxLeftN1.coerceAtLeast(maxLeftN2).toDouble()
                }
            } else if (maxLeftN1 > minRightN2) {
                high = partitionN1 - 1
            } else {
                low = partitionN1 + 1
            }
        }
    }

    val nums1 = intArrayOf(1, 3)
    val nums2 = intArrayOf(2)
    println(findMedianSortedArrays(nums1, nums2)) // 2.0

    val nums3 = intArrayOf(1, 2)
    val nums4 = intArrayOf(3, 4)
    println(findMedianSortedArrays(nums3, nums4)) // 2.5
}