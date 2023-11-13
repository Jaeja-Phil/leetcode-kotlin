package easy

/**
 * Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:
 *
 * answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
 * answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
 * Note that the integers in the lists may be returned in any order.
 *
 * constraints:
 * - 1 <= nums1.length, nums2.length <= 1000
 * - 0 <= nums1[i], nums2[i] <= 1000
 *
 * Example 1:
 * Input: nums1 = [1,2,3], nums2 = [2,4,6]
 * Output: [[1,3],[4,6]]
 * Explanation:
 * For nums1, nums1[1] = 2 is present at index 0 of nums2, whereas nums1[0] = 1 and nums1[2] = 3 are not present in nums2. Therefore, answer[0] = [1,3].
 * For nums2, nums2[0] = 2 is present at index 1 of nums1, whereas nums2[1] = 4 and nums2[2] = 6 are not present in nums2. Therefore, answer[1] = [4,6].
 *
 * Example 2:
 * Input: nums1 = [1,2,3,3], nums2 = [1,1,2,2]
 * Output: [[3],[]]
 * Explanation:
 * For nums1, nums1[2] and nums1[3] are not present in nums2. Since nums1[2] == nums1[3], their value is only included once and answer[0] = [3].
 * Every integer in nums2 is present in nums1. Therefore, answer[1] = [].
 *
 */
fun main() {
    val nums1 = intArrayOf(1, 2, 3)
    val nums2 = intArrayOf(2, 4, 6)
    println(`Find the Difference of Two Arrays`(nums1, nums2)) // [[1, 3], [4, 6]]

    val nums3 = intArrayOf(1, 2, 3, 3)
    val nums4 = intArrayOf(1, 1, 2, 2)
    println(`Find the Difference of Two Arrays`(nums3, nums4)) // [[3], []]
}

fun `Find the Difference of Two Arrays`(nums1: IntArray, nums2: IntArray): List<List<Int>> {
    /**
     * create a set version from each provided arguments (arrays)
     * - this will remove all duplicates from each array
     */
    val nums1Set = nums1.toSet()
    val nums2Set = nums2.toSet()

    println("minus: ${nums1Set - nums2Set}")
    /**
     * return filtered version of each set where the other set does not contain the value
     * - alternatively, you can use the subtract function: nums1Set.subtract(nums2Set)
     * - or you can also use the minus function: nums1Set.minus(nums2Set)
     * - or you can also use the minus operator: nums1Set - nums2Set (equivalent to the minus function)
     * above OTHER suggestions need to be wrapped in a listOf() function or else it will return a Set<Int>
     * - or you can call .toList() on the result of the subtract function
     */
    return listOf(
        nums1Set.filter { !nums2Set.contains(it) },
        nums2Set.filter { !nums1Set.contains(it) }
    )
}