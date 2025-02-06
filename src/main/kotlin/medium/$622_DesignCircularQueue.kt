package medium

/**
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the
 * operations are performed based on FIFO (First In First Out) principle, and the last position is connected back to the
 * first position to make a circle. It is also called "Ring Buffer".
 *
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal
 * queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue.
 * But using the circular queue, we can use the space to store new values.
 *
 * Implement the MyCircularQueue class:
 * - MyCircularQueue(k) Initializes the object with the size of the queue to be k.
 * - int Front() Gets the front item from the queue. If the queue is empty, return -1.
 * - int Rear() Gets the last item from the queue. If the queue is empty, return -1.
 * - boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
 * - boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
 * - boolean isEmpty() Checks whether the circular queue is empty or not.
 * - boolean isFull() Checks whether the circular queue is full or not.
 *
 * You must solve the problem without using the built-in queue data structure in your programming language.
 *
 * Constraints:
 * - 1 <= k <= 1000
 * - 0 <= value <= 1000
 * - At most 3000 calls will be made to enQueue, deQueue, Front, Rear, isEmpty, and isFull.
 *
 * Example 1:
 * Input
 * ["MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * Output
 * [null, true, true, true, false, 3, true, true, true, 4]
 * Explanation
 * MyCircularQueue myCircularQueue = new MyCircularQueue(3);
 * myCircularQueue.enQueue(1); // return True
 * myCircularQueue.enQueue(2); // return True
 * myCircularQueue.enQueue(3); // return True
 * myCircularQueue.enQueue(4); // return False
 * myCircularQueue.Rear();     // return 3
 * myCircularQueue.isFull();   // return True
 * myCircularQueue.deQueue();  // return True
 * myCircularQueue.enQueue(4); // return True
 * myCircularQueue.Rear();     // return 4
 */
fun main() {
    data class ListNode(
        var value: Int,
        var next: ListNode? = null,
        var prev: ListNode? = null,
    )

    class MyCircularQueue(k: Int) {
        private var capacity = k
        private var left = ListNode(-1)
        private var right = ListNode(-1)

        init {
            left.next = right
            right.prev = left
        }

        fun enQueue(value: Int): Boolean {
            if (isFull()) {
                return false
            }

            val node = ListNode(value)
            node.prev = right.prev
            node.next = right
            right.prev?.next = node
            right.prev = node
            capacity--
            return true
        }

        fun deQueue(): Boolean {
            if (isEmpty()) {
                return false
            }

            left.next = left.next?.next
            left.next?.prev = left
            capacity++
            return true
        }

        fun Front(): Int {
            return left.next?.value ?: -1
        }

        fun Rear(): Int {
            return right.prev?.value ?: -1
        }

        fun isEmpty(): Boolean {
            return left.next == right
        }

        fun isFull(): Boolean {
            return capacity == 0
        }
    }

    val myCircularQueue = MyCircularQueue(3)
    println(myCircularQueue.enQueue(1)) // return True
    println(myCircularQueue.enQueue(2)) // return True
    println(myCircularQueue.enQueue(3)) // return True
    println(myCircularQueue.enQueue(4)) // return False
    println(myCircularQueue.Rear())     // return 3
    println(myCircularQueue.isFull())   // return True
    println(myCircularQueue.deQueue())  // return True
    println(myCircularQueue.enQueue(4)) // return True
    println(myCircularQueue.Rear())     // return 4
}
