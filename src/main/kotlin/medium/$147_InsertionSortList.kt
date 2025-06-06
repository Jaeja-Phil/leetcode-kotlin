package medium

/**
 * Given the head of a singly linked list, sort the list using insertion sort, and return the sorted list's head.
 *
 * The steps of the insertion sort algorithm:
 * 1. Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.
 * 2. At each iteration, insertion sort removes one element from the input data, finds the location it belongs within
 *    the sorted list and inserts it there.
 * 3. It repeats until no input elements remain.
 *
 * The following is a graphical example of the insertion sort algorithm. The partially sorted list (black) initially
 * contains only the first element in the list. One element (red) is removed from the input data and inserted in-place
 * into the sorted list with each iteration.
 *
 * Constraints:
 * - The number of nodes in the list is in the range [1, 5000].
 * - -5000 <= Node.val <= 5000
 *
 * Example 1:
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 *
 * Example 2:
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 */
fun main() {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun insertionSortList(head: ListNode?): ListNode? {
        val dummyHead = ListNode(0) // Dummy head for the sorted list
        var current = head // Pointer to the current node in the original list

        while (current != null) {
            var prev = dummyHead // Pointer to find the insertion point in the sorted list
            // Find the correct position to insert the current node
            while (prev.next != null && prev.next!!.`val` < current!!.`val`) {
                prev = prev.next!!
            }
            // Insert the current node into the sorted list
            val nextTemp = current!!.next // Save the next node
            current!!.next = prev.next // Insert current node after prev
            prev.next = current // Link prev to current
            current = nextTemp // Move to the next node in the original list
        }

        return dummyHead.next // Return the sorted list, skipping the dummy head
    }

    // Example usage:
    val head1 = ListNode(4).apply {
        next = ListNode(2).apply {
            next = ListNode(1).apply {
                next = ListNode(3)
            }
        }
    }
    val sortedHead1 = insertionSortList(head1)
    var current = sortedHead1
    while (current != null) {
        print("${current!!.`val`} ")
        current = current!!.next
    }
    println() // Output: 1 2 3 4

    val head2 = ListNode(-1).apply {
        next = ListNode(5).apply {
            next = ListNode(3).apply {
                next = ListNode(4).apply {
                    next = ListNode(0)
                }
            }
        }
    }
    val sortedHead2 = insertionSortList(head2)
    current = sortedHead2
    while (current != null) {
        print("${current!!.`val`} ")
        current = current!!.next
    }
    println() // Output: -1 0 3 4 5
}
