package medium

/**
 * In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the
 * (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
 *
 * For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes
 * with twins for n = 4. The twin sum is defined as the sum of a node and its twin.
 *
 * Given the head of a linked list with even length, return the maximum twin sum of the linked list.
 *
 * constraints:
 * - The number of nodes in the list is in the range [2, 10^5].
 * - 0 <= Node.val <= 10^5
 *
 * Example 1:
 * Input: head = [5, 4, 2, 1]
 * Output: 6
 * Explanation:
 * Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
 * There are no other nodes with twins in the linked list.
 * Thus, the maximum twin sum of the linked list is 6.
 *
 * Example 2:
 * Input: head = [4,2,2,3]
 * Output: 7
 * Explanation:
 * The nodes with twins present in this linked list are:
 * - Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
 * - Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
 * Thus, the maximum twin sum of the linked list is max(7, 4) = 7.
 */
fun main() {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun `Maximum Twin Sum of a Linked List`(head: ListNode?): Int {
        /**
         * base case: constraint states that there will be at least 2 nodes in the linked list
         * return the sum of the first 2 nodes if there are only 2 nodes in the linked list
         */
        if (head?.next?.next == null) {
            return head?.`val`!! + head.next?.`val`!!
        }

        /**
         * create a variable to
         * - store the maximum twin sum
         * - store the current node
         */
        var maxSum = 0
        var curr = head

        /**
         * reverse the first half of the linked list
         */
        var prev: ListNode? = null
        var fast = head
        while (curr != null && fast?.next != null) {
            fast = fast.next?.next // move fast pointer 2 nodes ahead to end the reverse at the middle of the linked list

            /**
             * reverse process
             */
            val next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        }

        /**
         * at this point...
         * - prev: the head of the reversed linked list
         * - curr: the head of the second half of the linked list
         */

        /**
         * iterate through the linked list and compare the sum of the current node and its twin to the maximum twin sum
         */
        while (curr != null && prev != null) {
            maxSum = maxOf(maxSum, curr.`val` + prev.`val`)
            curr = curr.next
            prev = prev.next
        }

        return maxSum
    }

    val head1 = ListNode(5)
    head1.next = ListNode(4)
    head1.next?.next = ListNode(2)
    head1.next?.next?.next = ListNode(1)
    println(`Maximum Twin Sum of a Linked List`(head1)) // 6

    val head2 = ListNode(4)
    head2.next = ListNode(2)
    head2.next?.next = ListNode(2)
    head2.next?.next?.next = ListNode(3)
    println(`Maximum Twin Sum of a Linked List`(head2)) // 7


}

