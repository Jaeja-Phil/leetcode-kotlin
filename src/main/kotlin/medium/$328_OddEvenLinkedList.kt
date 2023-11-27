package medium

/**
 * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes
 * with even indices, and return the reordered list.
 *
 * The first node is considered odd, and the second node is even, and so on.
 *
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 *
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 *
 * constraints:
 * - 0 <= n <= 10^4
 * - -10^6 <= Node.val <= 10^6
 *
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [1,3,5,2,4]
 *
 * Example 2:
 * Input: head = [2,1,3,5,6,4,7]
 * Output: [2,3,6,7,1,5,4]
 */
fun main() {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null

        override fun toString(): String {
            return "ListNode(`val`=$`val`, next=$next)"
        }

    }

    fun `Odd Even Linked List`(head: ListNode?): ListNode? {
        /**
         * base case: if the linked list is empty or has only one node, return head (no need to reorder)
         */
        if (head?.next == null) {
            return head
        }

        /**
         * create a pointer for the head of the even nodes (we can use original head for the odd node's head)
         */
        val evenHead = head.next

        /**
         * create two pointers for odd and even nodes that will iterate through the linked list
         */
        var oddCurr = head
        var evenCurr = evenHead

        /**
         * loop through the linked list,
         */
        while (evenCurr?.next != null) {
            /**
             * connect the odd nodes
             * - current odd pointer should point the node after the current even pointer (evenCurr.next)
             * - after connecting the odd nodes, move the odd pointer to the next odd node
             */
            oddCurr?.next = evenCurr.next
            oddCurr = oddCurr?.next

            /**
             * connect the even nodes
             * - current even pointer should point the node after the current odd pointer (oddCurr.next)
             *   - since oddCurr is already moved to the next odd node, oddCurr.next is the next even node
             * - after connecting the even nodes, move the even pointer to the next even node
             */
            evenCurr.next = oddCurr?.next
            evenCurr = evenCurr.next
        }

        /**
         * connect the odd nodes to the even nodes
         */
        oddCurr?.next = evenHead

        return head
    }

    val head1 = ListNode(1)
    head1.next = ListNode(2)
    head1.next?.next = ListNode(3)
    head1.next?.next?.next = ListNode(4)
    head1.next?.next?.next?.next = ListNode(5)

    var res1 = `Odd Even Linked List`(head1)
    while (res1 != null) {
        print("${res1.`val`} ") // 1 3 5 2 4
        res1 = res1.next
    }
    println()

    val head2 = ListNode(2)
    head2.next = ListNode(1)
    head2.next?.next = ListNode(3)
    head2.next?.next?.next = ListNode(5)
    head2.next?.next?.next?.next = ListNode(6)
    head2.next?.next?.next?.next?.next = ListNode(4)
    head2.next?.next?.next?.next?.next?.next = ListNode(7)

    var res2 = `Odd Even Linked List`(head2)
    while (res2 != null) {
        print("${res2.`val`} ") // 2 3 6 7 1 5 4
        res2 = res2.next
    }
    println()
}
