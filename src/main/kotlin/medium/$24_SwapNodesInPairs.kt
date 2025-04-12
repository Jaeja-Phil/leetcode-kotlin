package medium

/**
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying
 * the values in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 100].
 * - 0 <= Node.val <= 100
 *
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 *
 * Example 2:
 * Input: head = []
 * Output: []
 *
 * Example 3:
 * Input: head = [1]
 * Output: [1]
 */
fun main() {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun swapPairs(head: ListNode?): ListNode? {
        var dummy = ListNode(-1)
        dummy.next = head
        var prev: ListNode? = dummy
        var cur = head

        while (cur?.next != null) {
            val nextPairStart = cur.next?.next
            val second = cur.next

            // Swap the nodes
            second?.next = cur
            cur.next = nextPairStart
            prev?.next = second

            // Move to the next pair
            prev = cur
            cur = nextPairStart
        }

        return dummy.next
    }

    val head = ListNode(1).apply {
        next = ListNode(2).apply {
            next = ListNode(3).apply {
                next = ListNode(4)
            }
        }
    }
    var result = swapPairs(head)

    while (result != null) {
        print("${result.`val`} -> ")
        result = result.next
    }
    println("null")
    // Output: 2 -> 1 -> 4 -> 3 -> null

    val head2 = null
    var result2 = swapPairs(head2)

    while (result2 != null) {
        print("${result2.`val`} -> ")
        result2 = result2.next
    }
    println("null")
    // Output: null

    val head3 = ListNode(1)
    var result3 = swapPairs(head3)

    while (result3 != null) {
        print("${result3.`val`} -> ")
        result3 = result3.next
    }
    println("null")
    // Output: 1 -> null
}