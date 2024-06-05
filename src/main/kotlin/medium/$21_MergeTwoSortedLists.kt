package medium

/**
 * You are given the heads of two sorted linked lists list1 and list2.
 *
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
 *
 * Return the head of the merged linked list.
 *
 * Constraints:
 * - The number of nodes in list1 and list2 is in the range [0, 50].
 * - -100 <= Node.val <= 100
 * - Both list1 and list2 are sorted in non-decreasing order.
 *
 * Example 1:
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 *
 * Example 2:
 * Input: list1 = [], list2 = []
 * Output: []
 *
 * Example 3:
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 */
fun main() {
    val l1 = `$21_MergeTwoSortedLists`.ListNode(1).apply {
        next = `$21_MergeTwoSortedLists`.ListNode(2).apply {
            next = `$21_MergeTwoSortedLists`.ListNode(4)
        }
    }
    val l2 = `$21_MergeTwoSortedLists`.ListNode(1).apply {
        next = `$21_MergeTwoSortedLists`.ListNode(3).apply {
            next = `$21_MergeTwoSortedLists`.ListNode(4)
        }
    }
    val result = `$21_MergeTwoSortedLists`().mergeTwoLists(l1, l2)
    var current = result
    while (current != null) {
        print("${current.`val`} ")
        current = current.next
    }
    println()
    println("----------------")

    val l3 = `$21_MergeTwoSortedLists`.ListNode(0)
    val l4 = `$21_MergeTwoSortedLists`.ListNode(0)
    val result2 = `$21_MergeTwoSortedLists`().mergeTwoLists(l3, l4)
    var current2 = result2
    while (current2 != null) {
        print("${current2.`val`} ")
        current2 = current2.next
    }
    println()
    println("----------------")

    val l5 = `$21_MergeTwoSortedLists`.ListNode(0)
    val l6 = `$21_MergeTwoSortedLists`.ListNode(0).apply {
        next = `$21_MergeTwoSortedLists`.ListNode(1)
    }
    val result3 = `$21_MergeTwoSortedLists`().mergeTwoLists(l5, l6)
    var current3 = result3
    while (current3 != null) {
        print("${current3.`val`} ")
        current3 = current3.next
    }
    println()
    println("----------------")
}

class `$21_MergeTwoSortedLists` {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        var list1 = l1
        var list2 = l2
        val dummy = ListNode(0)
        var current = dummy
        while (list1 != null && list2 != null) {
            if (list1.`val` < list2.`val`) {
                current.next = list1
                list1 = list1.next
            } else {
                current.next = list2
                list2 = list2.next
            }
            current = current.next!!
        }
        current.next = list1 ?: list2
        return dummy.next
    }
}