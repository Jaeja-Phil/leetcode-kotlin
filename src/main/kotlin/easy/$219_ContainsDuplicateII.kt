package easy

/**
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array
 * such that nums[i] == nums[j] and abs(i - j) <= k.
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - -10^9 <= nums[i] <= 10^9
 * - 0 <= k <= 10^5
 *
 * Example 1:
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 *
 * Example 2:
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 *
 * Example 3:
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 */
fun main() {
    fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        // Solution 1.
//        val distanceMap = mutableMapOf<Int, Int>()
//        for (i in nums.indices) {
//            if (distanceMap.containsKey(nums[i]) && i - distanceMap[nums[i]]!! <= k) {
//                return true
//            }
//            distanceMap[nums[i]] = i
//        }
//
//        return false

        // Solution 2.
        val set = hashSetOf<Int>()
        var left = 0
        for (i in nums.indices) {
            if (i - left > k) {
                set.remove(nums[left++])
            }
            if (set.contains(nums[i])) {
                return true
            }
            set.add(nums[i])
        }

        return false
    }

    val nums = intArrayOf(1, 2, 3, 1)
    val k = 3
    println(containsNearbyDuplicate(nums, k)) // Output: true

    val nums2 = intArrayOf(1, 0, 1, 1)
    val k2 = 1
    println(containsNearbyDuplicate(nums2, k2)) // Output: true

    val nums3 = intArrayOf(1, 2, 3, 1, 2, 3)
    val k3 = 2
    println(containsNearbyDuplicate(nums3, k3)) // Output: false
}
