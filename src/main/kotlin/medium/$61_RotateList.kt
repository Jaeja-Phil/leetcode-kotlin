package medium

/**
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 500].
 * - -100 <= Node.val <= 100
 * - 0 <= k <= 2 * 10^9
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 *
 * Example 2:
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 */
fun main() {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if (head?.next == null || k == 0) {
            return head
        }

        var length = 1
        var tail = head
        while (tail?.next != null) {
            tail = tail.next
            length++
        }
        tail?.next = head // Connect the tail to the head to make it circular

        // Calculate the effective rotation
        val effectiveK = k % length
        if (effectiveK == 0) {
            tail?.next = null // Break the circular link
            return head
        }
        val stepsToNewHead = length - effectiveK

        var newTail = tail
        repeat(stepsToNewHead) {
            newTail = newTail?.next
        }
        val newHead = newTail?.next
        newTail?.next = null // Break the circular link

        return newHead
    }

    val node = ListNode(1)
    node.next = ListNode(2)
    node.next?.next = ListNode(3)
    node.next?.next?.next = ListNode(4)
    node.next?.next?.next?.next = ListNode(5)
    val k = 2
    var result = rotateRight(node, k)
    while (result != null) {
        print("${result.`val`} -> ")
        result = result.next
    }
    println("null")
    // Output: [4,5,1,2,3]

    val node2 = ListNode(0)
    node2.next = ListNode(1)
    node2.next?.next = ListNode(2)
    val k2 = 4
    result = rotateRight(node2, k2)
    while (result != null) {
        print("${result.`val`} -> ")
        result = result.next
    }
    println("null")
    // Output: [2,0,1]
}