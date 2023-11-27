package medium

/**
 * You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.
 *
 * The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing, where ⌊x⌋
 * denotes the largest integer less than or equal to x.
 *
 * For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.
 *
 * constraints:
 * - The number of nodes in the list is in the range [1, 10^5].
 * - 1 <= Node.val <= 10^5
 *
 * Example 1:
 * Input: head = [1,3,4,7,1,2,6]
 * Output: [1,3,4,1,2,6]
 * Explanation:
 * The above figure represents the given linked list. The indices of the nodes are written below.
 * Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
 * We return the new list after removing this node.
 *
 * Example 2:
 * Input: head = [1,2,3,4]
 * Output: [1,2,4]
 * Explanation:
 * The above figure represents the given linked list.
 * For n = 4, node 2 with value 3 is the middle node, which is marked in red.
 *
 * Example 3:
 * Input: head = [2,1]
 * Output: [2]
 * Explanation:
 * The above figure represents the given linked list.
 * For n = 2, node 1 with value 1 is the middle node, which is marked in red.
 * Node 0 with value 2 is the only node remaining after removing node 1.
 */
fun main() {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }


    fun `Delete the Middle Ndde of a Linked List`(head: ListNode?): ListNode? {
        /**
         * base case: if the linked list is empty or has only one node, return empty node
         */
        if (head?.next == null) {
            return null
        }

        /**
         * create a slow pointer and a fast pointer
         */
        var slow = head
        var fast = head

        /**
         * create a prev node to keep track of the previous node of slow
         */
        var prev: ListNode? = null

        /**
         * loop through the linked list,
         * - slow pointer moves one node at a time
         * - fast pointer moves two nodes at a time
         * - this way, slow will be at the middle node when fast reaches the end of the linked list (and also meets the
         *   requirement of (n / 2)th node from the start using 0-based indexing)
         */
        while (fast?.next != null) {
            prev = slow
            slow = slow?.next
            fast = fast.next?.next
        }

        /**
         * attach prev node of slow and next node of slow
         */
        prev?.next = slow?.next

        /**
         * return the head of the modified linked list
         */
        return head
    }

    fun printLinkedList(head: ListNode?) {
        var node = head
        print("[")
        while (node != null) {
            print("${node.`val`}")
            node = node.next
            if (node != null) {
                print(",")
            }
        }
        println("]")
    }

    val head1 = ListNode(1)
    head1.next = ListNode(3)
    head1.next?.next = ListNode(4)
    head1.next?.next?.next = ListNode(7)
    head1.next?.next?.next?.next = ListNode(1)
    head1.next?.next?.next?.next?.next = ListNode(2)
    head1.next?.next?.next?.next?.next?.next = ListNode(6)
    val res1 = `Delete the Middle Ndde of a Linked List`(head1)
    printLinkedList(res1) // [1,3,4,1,2,6]

    val head2 = ListNode(1)
    head2.next = ListNode(2)
    head2.next?.next = ListNode(3)
    head2.next?.next?.next = ListNode(4)
    val res2 = `Delete the Middle Ndde of a Linked List`(head2)
    printLinkedList(res2) // [1,2,4]

    val head3 = ListNode(2)
    head3.next = ListNode(1)
    val res3 = `Delete the Middle Ndde of a Linked List`(head3)
    printLinkedList(res3) // [2]
}

