package medium

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Constraints:
 * - The number of nodes in the list is sz.
 * - 1 <= sz <= 30
 * - 0 <= Node.val <= 100
 * - 1 <= n <= sz
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 *
 * Example 2:
 * Input: head = [1], n = 1
 * Output: []
 *
 * Example 3:
 * Input: head = [1,2], n = 1
 * Output: [1]
 */
fun main() {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        val dummy = ListNode(0).apply { next = head }
        var left: ListNode? = dummy
        var right = head

        repeat(n) {
            right = right?.next
        }

        while (right != null) {
            left = left?.next
            right = right?.next
        }

        left?.next = left?.next?.next

        return dummy.next
    }

    fun printList(head: ListNode?) {
        var temp = head
        while (temp != null) {
            print("${temp.`val`} ")
            temp = temp.next
        }
        println()
    }

    val head = ListNode(1)
    head.next = ListNode(2)
    head.next?.next = ListNode(3)
    head.next?.next?.next = ListNode(4)
    head.next?.next?.next?.next = ListNode(5)
    val n = 2
    val result = removeNthFromEnd(head, n)
    printList(result)

    val head2 = ListNode(1)
    val n2 = 1
    val result2 = removeNthFromEnd(head2, n2)
    printList(result2)
}