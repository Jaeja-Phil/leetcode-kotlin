package medium

/**
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the
 * list from position left to position right, and return the reversed list.
 *
 * constraints:
 * - The number of nodes in the list is n.
 * - 1 <= n <= 500
 * - -500 <= Node.val <= 500
 * - 1 <= left <= right <= n
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 *
 * Example 2:
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 */
fun main() {
    val head = `$92_ReverseLinkedListII`.ListNode(1)
    head.next = `$92_ReverseLinkedListII`.ListNode(2)
    head.next?.next = `$92_ReverseLinkedListII`.ListNode(3)
    head.next?.next?.next = `$92_ReverseLinkedListII`.ListNode(4)
    head.next?.next?.next?.next = `$92_ReverseLinkedListII`.ListNode(5)
    val left = 2
    val right = 4
    val result = `$92_ReverseLinkedListII`().reverseBetween(head, left, right)
    println(result)
}

class `$92_ReverseLinkedListII` {
    data class ListNode(var `val`: Int, var next: ListNode? = null)

    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        if (head == null) return null

        val dummy = ListNode(0)
        dummy.next = head

        var prev = dummy
        for (i in 0..<left - 1) {
            prev = prev.next!!
        }

        val beforeRotate = prev
        var rotatePrev: ListNode? = null
        var current = prev.next
        for (i in 0.. right - left) {
            val next = current?.next
            current?.next = rotatePrev
            rotatePrev = current
            current = next
        }

        beforeRotate.next?.next = current
        beforeRotate.next = rotatePrev

        return dummy.next
    }
}
