package easy

import java.util.*

/**
 * You are part of a university admissions office and need to keep track of the kth highest test score from applicants
 * in real-time. This helps to determine cut-off marks for interviews and admissions dynamically as new applicants
 * submit their scores.
 *
 * You are tasked to implement a class which, for a given integer k, maintains a stream of test scores and continuously
 * returns the kth highest test score after a new score has been submitted. More specifically, we are looking for the
 * kth highest score in the sorted list of all scores.
 *
 * Implement the KthLargest class:
 * - KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of test scores nums.
 * - int add(int val) Adds a new test score val to the stream and returns the element representing the kth largest
 *   element in the pool of test scores so far.
 *
 * Constraints:
 * - 0 <= nums.length <= 10^4
 * - 1 <= k <= nums.length + 1
 * - -10^4 <= nums[i] <= 10^4
 * - -10^4 <= val <= 10^4
 * - At most 10^4 calls will be made to add.
 *
 * Example 1:
 * Input
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * Output
 * [null, 4, 5, 5, 8, 8]
 * Explanation
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * KthLargest.add(3);   // return 4
 * KthLargest.add(5);   // return 5
 * KthLargest.add(10);  // return 5
 * KthLargest.add(9);   // return 8
 * KthLargest.add(4);   // return 8
 *
 * Example 2:
 * Input
 * ["KthLargest", "add", "add", "add", "add"]
 * [[4, [7, 7, 7, 7, 8, 3]], [2], [10], [9], [9]]
 * Output
 * [null, 7, 8, 8, 8]
 * Explanation
 * KthLargest kthLargest = new KthLargest(4, [7, 7, 7, 7, 8, 3]);
 * KthLargest.add(2);  // return 7
 * KthLargest.add(10); // return 7
 * KthLargest.add(9);  // return 7
 * KthLargest.add(9);  // return 8
 */
fun main() {
    class KthLargest(private val k: Int, nums: IntArray) {
        private val minHeap = PriorityQueue<Int>()

        init {
            for (num in nums) {
                add(num)
            }

            while (minHeap.size > k) {
                minHeap.poll()
            }
        }

        fun add(`val`: Int): Int {
            minHeap.offer(`val`)
            if (minHeap.size > k) {
                minHeap.poll()
            }
            return minHeap.peek()
        }
    }

    val kthLargest = KthLargest(3, intArrayOf(4, 5, 8, 2))
    println(kthLargest.add(3)) // 4
    println(kthLargest.add(5)) // 5
    println(kthLargest.add(10)) // 5
    println(kthLargest.add(9)) // 8
    println(kthLargest.add(4)) // 8

    val kthLargest2 = KthLargest(4, intArrayOf(7, 7, 7, 7, 8, 3))
    println(kthLargest2.add(2)) // 7
    println(kthLargest2.add(10)) // 7
    println(kthLargest2.add(9)) // 7
    println(kthLargest2.add(9)) // 8
}