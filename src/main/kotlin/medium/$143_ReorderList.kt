package medium

/**
 * You are given the head of a singly linked-list. The list can be represented as:
 * L0 → L1 → … → Ln - 1 → Ln
 *
 * Reorder the list to be on the following form:
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 *
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 *
 * Constraints:
 * - The number of nodes in the list is in the range [1, 5 * 10^4].
 * - 1 <= Node.val <= 1000
 *
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [1,4,2,3]
 *
 * Example 2:
 * Input: head = [1,2,3,4,5]
 * Output: [1,5,2,4,3]
 */
fun main() {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun reverse(head: ListNode?): ListNode? {
        var prev: ListNode? = null
        var curr = head

        while (curr != null) {
            val next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        }

        return prev
    }

    fun reorderList(head: ListNode?) {
        if (head?.next == null) {
            return
        }

        var slow = head
        var fast = head.next
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }

        var second = reverse(slow?.next)
        slow?.next = null

        var first = head
        while (second != null) {
            val firstNext = first?.next
            val secondNext = second.next
            first?.next = second
            second.next = firstNext
            first = firstNext
            second = secondNext
        }
    }

    fun printList(head: ListNode?) {
        var current = head
        while (current != null) {
            print("${current.`val`} -> ")
            current = current.next
        }
        println("null")
    }

    val head = ListNode(1)
    head.next = ListNode(2)
    head.next?.next = ListNode(3)
    head.next?.next?.next = ListNode(4)
    println("Before reordering:")
    printList(head)
    reorderList(head)
    println("After reordering:")
    printList(head)

    val head2 = ListNode(1)
    head2.next = ListNode(2)
    head2.next?.next = ListNode(3)
    head2.next?.next?.next = ListNode(4)
    head2.next?.next?.next?.next = ListNode(5)
    println("Before reordering:")
    printList(head2)
    reorderList(head2)
    println("After reordering:")
    printList(head2)
}