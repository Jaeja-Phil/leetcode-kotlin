package easy

/**
 * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
 * - For example, for arr = [1,2,3], the following are all the permutations of arr:
 *   [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
 *
 * The next permutation of an array of integers is the next lexicographically greater permutation of its integer.
 * More formally, if all the permutations of the array are sorted in one container according to their lexicographical
 * order, then the next permutation of that array is the permutation that follows it in the sorted container.
 * If such arrangement is not possible, the array must be rearranged as the lowest possible order
 * (i.e., sorted in ascending order).
 * - For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * - Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * - While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger
 *   rearrangement.
 *
 * Given an array of integers nums, find the next permutation of nums.
 *
 * The replacement must be in place and use only constant extra memory.
 */
fun main() {
    fun swap(nums: IntArray, i: Int, j: Int) {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }

    fun nextPermutation(nums: IntArray) {
        val n = nums.size
        var i = n - 2

        // Find the first decreasing element from the end
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--
        }

        if (i >= 0) {
            // Find the first element larger than nums[i] from the end
            var j = n - 1
            while (j > i && nums[j] <= nums[i]) {
                j--
            }
            swap(nums, i, j)
        }

        // Reverse the elements after index i
        var left = i + 1
        var right = n - 1
        while (left < right) {
            swap(nums, left, right)
            left++
            right--
        }
    }

    val input1 = intArrayOf(1, 2, 3)
    nextPermutation(input1)
    println(input1.joinToString(", ")) // Output: 1, 3, 2

    val input2 = intArrayOf(3, 2, 1)
    nextPermutation(input2)
    println(input2.joinToString(", ")) // Output: 1, 2, 3

    val input3 = intArrayOf(1, 1, 5)
    nextPermutation(input3)
    println(input3.joinToString(", ")) // Output: 1, 5, 1
}