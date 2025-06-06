package medium

/**
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
 * following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is
 * connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
 *
 * Do not modify the linked list.
 *
 * constraints:
 * - The number of the nodes in the list is in the range [0, 10^4].
 * - -10^5 <= Node.val <= 10^5
 * - pos is -1 or a valid index in the linked-list.
 *
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 */
fun main() {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun detectCycle(head: ListNode?): ListNode? {
        // base case
        if (head?.next == null) {
            return null
        }

        var currNode = head

        // create set marking visited nodes
        val visited = mutableSetOf<ListNode>()

        // iterate slow & fast and detect cycle
        while (currNode != null) {
            if (!visited.add(currNode)) {
                return currNode
            }
            currNode = currNode.next
        }

        return null
    }

    val head = ListNode(3)
    val node1 = ListNode(2)
    val node2 = ListNode(0)
    val node3 = ListNode(-4)
    head.next = node1
    node1.next = node2
    node2.next = node3
    node3.next = node1
    println(detectCycle(head)?.`val`) // 2
}
