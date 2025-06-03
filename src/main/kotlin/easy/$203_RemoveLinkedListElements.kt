package easy

/**
 * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has
 * Node.val == val, and return the new head.
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 10^4].
 * - 1 <= Node.val <= 50
 * - 0 <= val <= 50
 *
 * Example 1:
 * Input: head = [1,2,6,3,4,5,6], val = 6
 * Output: [1,2,3,4,5]
 *
 * Example 2:
 * Input: head = [], val = 1
 * Output: []
 *
 * Example 3:
 * Input: head = [7,7,7,7], val = 7
 * Output: []
 */
fun main() {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun removeElement(head: ListNode?, `val`: Int): ListNode? {
        var dummy = ListNode(0)
        dummy.next = head
        var curr: ListNode? = dummy

        while (curr?.next != null) {
            if (curr.next!!.`val` == `val`) {
                curr.next = curr.next!!.next // Skip the node with the value `val`
            } else {
                curr = curr.next // Move to the next node
            }
        }

        return dummy.next // Return the new head, which is the next of dummy
    }

    fun printListNode(head: ListNode?) {
        var current = head
        while (current != null) {
            print("${current.`val`} -> ")
            current = current.next
        }
        println("null")
    }

    val head1 = ListNode(1).apply {
        next = ListNode(2).apply {
            next = ListNode(6).apply {
                next = ListNode(3).apply {
                    next = ListNode(4).apply {
                        next = ListNode(5).apply {
                            next = ListNode(6)
                        }
                    }
                }
            }
        }
    }
    printListNode(head1)

    val head2 = ListNode(7).apply {
        next = ListNode(7).apply {
            next = ListNode(7).apply {
                next = ListNode(7)
            }
        }
    }
    printListNode(head2)

    val head3 = ListNode(1).apply {
        next = ListNode(2).apply {
            next = ListNode(6).apply {
                next = ListNode(3).apply {
                    next = ListNode(4).apply {
                        next = ListNode(5)
                    }
                }
            }
        }
    }
    printListNode(head3)
}