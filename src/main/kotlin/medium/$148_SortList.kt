package medium

/**
 * Given the head of a linked list, return the list after sorting it in ascending order.
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 5 * 10^4].
 * - -10^5 <= Node.val <= 10^5
 *
 * Example 1:
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 */
fun main() {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun printListNode(node: ListNode?) {
        var current = node
        while (current != null) {
            print("${current.`val`} -> ")
            current = current.next
        }
        println("null")
    }

    class Solution {
        fun sortList(head: ListNode?): ListNode? {
            // base case
            if (head?.next == null) {
                return head
            }

            val mid = findMid(head)
            val right = mid?.next
            mid?.next = null

            // do recursion
            val left = head
            val sortedLeft = sortList(left)
            val sortedRight = sortList(right)
            // helper function to merge
            val merged = merge(sortedLeft, sortedRight)

            return merged
        }

        fun findMid(head: ListNode?): ListNode? {
            var slow = head
            var fast = head?.next

            while (fast?.next != null) {
                slow = slow?.next
                fast = fast.next?.next
            }

            return slow
        }

        fun merge(left: ListNode?, right: ListNode?): ListNode? {
            val dummyHead = ListNode(Int.MAX_VALUE)

            var leftPointer = left
            var rightPointer = right

            var start: ListNode? = dummyHead
            while (leftPointer != null || rightPointer != null) {
                if (leftPointer == null) {
                    start?.next = rightPointer
                    rightPointer = rightPointer?.next
                } else if (rightPointer == null) {
                    start?.next = leftPointer
                    leftPointer = leftPointer.next
                } else {
                    if (leftPointer.`val` >= rightPointer.`val`) {
                        start?.next = rightPointer
                        rightPointer = rightPointer.next
                    } else {
                        start?.next = leftPointer
                        leftPointer = leftPointer.next
                    }
                }

                start = start?.next
            }

            return dummyHead.next
        }
    }

    // [4, 2, 1, 3]
    val head = ListNode(4)
    head.next = ListNode(2)
    head.next?.next = ListNode(1)
    head.next?.next?.next = ListNode(3)

    val solution = Solution()
    printListNode(solution.sortList(head))
}
