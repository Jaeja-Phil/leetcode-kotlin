package easy

/**
 * The XOR total of an array is defined as the bitwise XOR of all its elements, or 0 if the array is empty.
 * - For example, the XOR total of the array [2,5,6] is 2 XOR 5 XOR 6 = 1.
 *
 * Given an array nums, return the sum of all XOR totals for every subset of nums.
 *
 * Note: Subsets with the same elements should be counted multiple times.
 *
 * An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b.
 *
 * Constraints:
 * - 1 <= nums.length <= 12
 * - 1 <= nums[i] <= 20
 *
 * Example 1:
 * Input: nums = [1,3]
 * Output: 6
 *
 * Example 2:
 * Input: nums = [5,1,6]
 * Output: 28
 */
fun main() {
    fun subsetXORSum(nums: IntArray): Int {
        var result = 0
        val n = nums.size

        // Iterate through all possible subsets
        fun dfs(index: Int, currentXOR: Int) {
            if (index == n) {
                result += currentXOR
                return
            }

            // Include the current element in the subset
            dfs(index + 1, currentXOR xor nums[index])
            // Exclude the current element from the subset
            dfs(index + 1, currentXOR)
        }

        dfs(0, 0)

        return result
    }

    println(subsetXORSum(intArrayOf(1, 3))) // 6
    println(subsetXORSum(intArrayOf(5, 1, 6))) // 28
}