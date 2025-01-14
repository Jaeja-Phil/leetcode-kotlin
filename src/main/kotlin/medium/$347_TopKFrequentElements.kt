package medium

/**
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any
 * order.
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - -10^4 <= nums[i] <= 10^4
 * - k is in the range [1, the number of unique elements in the array].
 * - It is guaranteed that the answer is unique.
 *
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 *
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 */
fun main() {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        // Solution 1
//        return nums.toList()
//            .groupingBy { it }
//            .eachCount()
//            .toList()
//            .sortedByDescending { it.second }
//            .take(k)
//            .map { it.first }.toIntArray()

        // Solution 2
        val freq = mutableMapOf<Int, Int>()
        nums.forEach { num -> freq[num] = freq.getOrDefault(num, 0) + 1 }
        val buckets = Array<MutableList<Int>>(nums.size + 1) { mutableListOf() }
        freq.forEach { (num, count) -> buckets[count].add(num) }

        val result = mutableListOf<Int>()
        for (i in buckets.lastIndex downTo 0) {
            val currentBucket = buckets[i]
            if (currentBucket.isNotEmpty()) {
                result.addAll(currentBucket.take(k - result.size))
                if (result.size >= k) break
            }
        }

        return result.toIntArray()
    }

    println(topKFrequent(intArrayOf(1, 1, 1, 2, 2, 3), 2).contentToString()) // [1,2]
    println(topKFrequent(intArrayOf(1), 1).contentToString()) // [1]
}