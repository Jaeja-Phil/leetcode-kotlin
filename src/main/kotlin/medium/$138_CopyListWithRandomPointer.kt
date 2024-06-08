package medium

/**
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to
 * any node in the list, or null.
 *
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has
 * its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes
 * should point to new nodes in the copied list such that the pointers in the original list and copied list represent
 * the same list state. None of the pointers in the new list should point to nodes in the original list.
 *
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding
 * two nodes x and y in the copied list, x.random --> y.
 *
 * Return the head of the copied linked list.
 *
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of
 * [val, random_index] where:
 * - val: an integer representing Node.val
 * - random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does
 *   not point to any node.
 *
 * Your code will only be given the head of the original linked list.
 *
 * Constraints:
 * - The number of nodes in the linked list is in the range [0, 1000].
 * - -10000 <= Node.val <= 10000
 * - Node.random is null or is pointing to some node in the linked list.
 *
 * Example 1:
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * Example 2:
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 */
fun main() {
    val head = `$138_CopyListWithRandomPointer`.Node(7).apply {
        next = `$138_CopyListWithRandomPointer`.Node(13).apply {
            next = `$138_CopyListWithRandomPointer`.Node(11).apply {
                next = `$138_CopyListWithRandomPointer`.Node(10).apply {
                    next = `$138_CopyListWithRandomPointer`.Node(1)
                }
            }
        }
        random = null
        next?.random = this.next?.next
        next?.next?.random = this.next?.next?.next?.next
        next?.next?.next?.random = this.next?.next?.next
        next?.next?.next?.next?.random = this
    }
    val result = `$138_CopyListWithRandomPointer`().copyRandomList(head)
    var current = result
    while (current != null) {
        print("[${current.`val`}, ${current.random?.`val`}] ")
        current = current.next
    }
    println()
    println("----------------")

    val head2 = `$138_CopyListWithRandomPointer`.Node(1).apply {
        next = `$138_CopyListWithRandomPointer`.Node(2)
        random = this.next
        next?.random = this.next
    }
    val result2 = `$138_CopyListWithRandomPointer`().copyRandomList(head2)
    var current2 = result2
    while (current2 != null) {
        print("[${current2.`val`}, ${current2.random?.`val`}] ")
        current2 = current2.next
    }
    println()
}

class `$138_CopyListWithRandomPointer` {
    data class Node(var `val`: Int) {
        var next: Node? = null
        var random: Node? = null
    }

    fun copyRandomList(node: Node?): Node? {
        val oldToNew = mutableMapOf<Node, Node>()
        var copy: Node?
        var curr: Node? = node
        while (curr != null) {
            copy = Node(curr.`val`)
            oldToNew[curr] = copy
            curr = curr.next
        }

        curr = node
        while (curr != null) {
            copy = oldToNew[curr]!!
            copy.next = oldToNew[curr.next]
            copy.random = oldToNew[curr.random]
            curr = curr.next
        }

        return oldToNew[node]
    }
}
