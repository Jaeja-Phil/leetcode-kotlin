package medium

import java.util.*

/**
 * You have a set which contains all positive integers [1, 2, 3, 4, 5, ...].
 *
 * Implement the SmallestInfiniteSet class:
 *
 * SmallestInfiniteSet() Initializes the SmallestInfiniteSet object to contain all positive integers.
 * int popSmallest() Removes and returns the smallest integer contained in the infinite set.
 * void addBack(int num) Adds a positive integer num back into the infinite set, if it is not already in the infinite set.
 *
 * constraints:
 * - 1 <= num <= 1000
 * - at most 1000 calls will be made in total to popSmallest and addBack
 *
 * Example 1:
 * Input
 * ["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
 * [[], [2], [], [], [], [1], [], [], []]
 * Output
 * [null, null, 1, 2, 3, null, 1, 4, 5]
 *
 */
fun main() {

    /**
     * we need 2 data structures:
     * - a min heap to keep track of the smallest number
     * - a set to keep track of the numbers added back
     *
     * we also need a variable to keep track of the smallest number
     * - this is because we can't just use the min heap to keep track of the smallest number (ex. some number can be popped and added back multiple times)
     *
     * on popSmallest(), we check if the min heap is empty.
     * - if it is, it means we have to return the smallest number and increment it
     * - if the min heap is not empty, we remove the smallest number from the min heap and the set
     *
     * upon addBack(), we check if the number is smaller than the smallest number we have tracked
     * - if it is, we add it to the set and the min heap
     * - if it is not, we don't have to do anything b.c it is already in the infinite set
     */
    class SmallestInfiniteSet() {
        val minHeap = PriorityQueue<Int>()
        val set = mutableSetOf<Int>()
        var smallest = 1
        fun popSmallest(): Int {
            if (minHeap.isEmpty()) {
                return smallest++
            }

            set.remove(minHeap.peek())
            return minHeap.poll()
        }

        fun addBack(num: Int) {
            if (num < smallest) {
                if (set.add(num)) {
                    minHeap.add(num)
                }
            }
        }
    }
}
