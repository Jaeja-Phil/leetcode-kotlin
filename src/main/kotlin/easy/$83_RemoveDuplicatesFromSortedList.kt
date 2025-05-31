package easy

/**
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the
 * linked list sorted as well.
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 300].
 * - -100 <= Node.val <= 100
 * - The list is guaranteed to be sorted in ascending order.
 *
 * Example 1:
 * Input: head = [1,1,2]
 * Output: [1,2]
 *
 * Example 2:
 * Input: head = [1,1,2,3,3]
 * Output: [1,2,3]
 */
fun main() {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun deleteDuplicates(head: ListNode?): ListNode? {
        var current = head
        while (current?.next != null) {
            if (current!!.`val` == current!!.next!!.`val`) {
                current!!.next = current!!.next!!.next
            } else {
                current = current!!.next
            }
        }
        return head
    }

    // Example usage
    val head = ListNode(1).apply {
        next = ListNode(1).apply {
            next = ListNode(2)
        }
    }
    val result = deleteDuplicates(head)
    var current = result
    while (current != null) {
        print("${current!!.`val`} ")
        current = current!!.next
    }
    // Output: 1 2
}