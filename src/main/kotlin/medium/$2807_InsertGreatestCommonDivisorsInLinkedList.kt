package medium

/**
 * Given the head of a linked list head, in which each node contains an integer value.
 * Between every pair of adjacent nodes, insert a new node with a value equal to the greatest common divisor of them.
 * Return the linked list after insertion.
 * The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.
 *
 * Constraints:
 * - The number of nodes in the linked list is in the range [1, 5000].
 * - 1 <= Node.val <= 1000
 *
 * Example 1:
 * Input: head = [18,6,10,3]
 * Output: [18,6,6,2,10,1,3]
 * Explanation: The 1st diagram denotes the initial linked list and the 2nd diagram denotes the linked list after
 * inserting the new nodes (nodes in blue are the inserted nodes).
 * - We insert the greatest common divisor of 18 and 6 = 6 between the 1st and the 2nd nodes.
 * - We insert the greatest common divisor of 6 and 10 = 2 between the 2nd and the 3rd nodes.
 * - We insert the greatest common divisor of 10 and 3 = 1 between the 3rd and the 4th nodes.
 * There are no more adjacent nodes, so we return the linked list.
 */
fun main() {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun insertGreatestCommonDivisors(head: ListNode?): ListNode? {
        fun getGCD(a: Int, b: Int): Int {
            return if (b == 0) a else getGCD(b, a % b)
        }

        var temp = head

        while (temp?.next != null) {
            val gcd = getGCD(temp.`val`, temp.next!!.`val`)
            val newNode = ListNode(gcd)
            newNode.next = temp.next
            temp.next = newNode
            temp = newNode.next
        }

        return head
    }

    fun printList(head: ListNode?) {
        var temp = head
        while (temp != null) {
            print("${temp!!.`val`} ")
            temp = temp!!.next
        }
        println()
    }

    val head =
        ListNode(18).apply {
            next = ListNode(6).apply {
                next = ListNode(10).apply {
                    next = ListNode(3)
                }
            }
        }
    printList(insertGreatestCommonDivisors(head)) // 18 6 6 2 10 1 3
}
