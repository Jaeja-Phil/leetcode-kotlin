package medium

import java.util.*

/**
 * Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size
 * groupSize, and consists of groupSize consecutive cards.
 *
 * Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize, return true
 * if she can rearrange the cards, or false otherwise.
 *
 * Constraints:
 * - 1 <= hand.length <= 10^4
 * - 0 <= hand[i] <= 10^9
 * - 1 <= groupSize <= hand.length
 *
 * Example 1:
 * Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
 *
 * Example 2:
 * Input: hand = [1,2,3,4,5], groupSize = 4
 * Output: false
 * Explanation: Alice's hand can't be rearranged into groups of 4.
 */
fun main() {
    fun isNStraightHand(hand: IntArray, groupSize: Int): Boolean {
        // Solution 1
//        val sortedHandsByCount = hand.groupBy { it }.mapValues { it.value.size }.toSortedMap()
//
//        while (sortedHandsByCount.isNotEmpty()) {
//            val firstKey = sortedHandsByCount.firstKey()
//            for (i in 0..<groupSize) {
//                val key = firstKey + i
//                if (sortedHandsByCount.getOrDefault(key, 0) == 0) {
//                    return false
//                }
//                sortedHandsByCount[key] = sortedHandsByCount[key]!! - 1
//                if (sortedHandsByCount[key] == 0) {
//                    sortedHandsByCount.remove(key)
//                }
//            }
//        }
//
//        return true

        // Solution 2 (Min Heap)
        if (hand.size % groupSize != 0) return false

        val count = mutableMapOf<Int, Int>()
        for (n in hand) {
            count[n] = count.getOrDefault(n, 0) + 1
        }

        val minHeap = PriorityQueue(count.keys)
        while (minHeap.isNotEmpty()) {
            val first = minHeap.peek()
            for (i in first..<first + groupSize) {
                if (count.getOrDefault(i, 0) == 0) return false
                count[i] = count[i]!! - 1
                if (count[i] == 0) {
                    if (i != minHeap.peek()) return false
                    minHeap.poll()
                }
            }
        }

        return true
    }

    println(isNStraightHand(intArrayOf(1,2,3,6,2,3,4,7,8), 3)) // true
    println(isNStraightHand(intArrayOf(1,2,3,4,5), 4)) // false
}
