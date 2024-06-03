package easy

/**
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
 * following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is
 * connected to. Note that pos is not passed as a parameter.
 *
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 * Constraints:
 * - The number of the nodes in the list is in the range [0, 10^4].
 * - -10^5 <= Node.val <= 10^5
 * - pos is -1 or a valid index in the linked list.
 *
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 *
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: true
 *
 * Example 3:
 * Input: head = [1], pos = -1
 * Output: false
 *
 */
fun main() {
    val head = `$141_LinkedListCycle`.ListNode(3)
    head.next = `$141_LinkedListCycle`.ListNode(2)
    head.next?.next = `$141_LinkedListCycle`.ListNode(0)
    head.next?.next?.next = `$141_LinkedListCycle`.ListNode(-4)
    head.next?.next?.next?.next = head.next
    println(`$141_LinkedListCycle`().hasCycle(head)) // true

    val head2 = `$141_LinkedListCycle`.ListNode(1)
    head2.next = `$141_LinkedListCycle`.ListNode(2)
    head2.next?.next = head2
    println(`$141_LinkedListCycle`().hasCycle(head2)) // true

    val head3 = `$141_LinkedListCycle`.ListNode(1)
    println(`$141_LinkedListCycle`().hasCycle(head3)) // false
}

class `$141_LinkedListCycle` {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun hasCycle(head: ListNode?): Boolean {
        if (head == null) return false

        var slow = head
        var fast = head
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
            if (slow == fast) return true
        }

        return false
    }
}