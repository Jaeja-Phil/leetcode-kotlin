package easy

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * constraints:
 * - The number of nodes in the list is the range [0, 5000].
 * - -5000 <= Node.val <= 5000
 *
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 *
 * Example 2:
 * Input: head = [1,2]
 * Output: [2,1]
 *
 * Example 3:
 * Input: head = []
 * Output: []
 */
fun main() {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun reverseList(head: ListNode?): ListNode? {
        /**
         * base case: return head if head is null or head.next is null
         */
        if (head?.next == null) {
            return head
        }

        /**
         * create a variable to store the previous node
         */
        var prev: ListNode? = null
        var curr = head

        /**
         * iterate through the LinkedList while there is a next node
         */
        while (curr != null) {
            /**
             * store the next node in a variable
             */
            val next = curr.next

            /**
             * set the next node to the previous node
             */
            curr.next = prev

            /**
             * set the previous node to the current node
             */
            prev = curr

            /**
             * set the current node to the next node
             */
            curr = next
        }

        /**
         * prev is the reversed head now, return it
         */
        return prev
    }

    fun printList(head: ListNode?) {
        print("List: ")
        var temp = head
        while (temp != null) {
            print("${temp.`val`} ")
            temp = temp.next
        }
        println()
    }

    val head1 = ListNode(1)
    head1.next = ListNode(2)
    head1.next?.next = ListNode(3)
    head1.next?.next?.next = ListNode(4)
    head1.next?.next?.next?.next = ListNode(5)
    printList(reverseList(head1)) // 5 4 3 2 1

    val head2 = ListNode(1)
    head2.next = ListNode(2)
    printList(reverseList(head2)) // 2 1

    val head3 = null
    printList(reverseList(head3)) //
}
