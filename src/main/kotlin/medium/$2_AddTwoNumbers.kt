package medium

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse
 * order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Constraints:
 * - The number of nodes in each linked list is in the range [1, 100].
 * - 0 <= Node.val <= 9
 * - It is guaranteed that the list represents a number that does not have leading zeros.
 *
 * Example 1:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 *
 * Example 2:
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 * Example 3:
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 */
fun main() {
    val l1 = `$42_AddTwoNumbers`.ListNode(2).apply {
        next = `$42_AddTwoNumbers`.ListNode(4).apply {
            next = `$42_AddTwoNumbers`.ListNode(3)
        }
    }
    val l2 = `$42_AddTwoNumbers`.ListNode(5).apply {
        next = `$42_AddTwoNumbers`.ListNode(6).apply {
            next = `$42_AddTwoNumbers`.ListNode(4)
        }
    }
    val result = `$42_AddTwoNumbers`().addTwoNumbers(l1, l2)
    var current = result
    while (current != null) {
        print("${current.`val`} ")
        current = current.next
    }
    println()
    println("----------------")

    val l3 = `$42_AddTwoNumbers`.ListNode(0)
    val l4 = `$42_AddTwoNumbers`.ListNode(0)
    val result2 = `$42_AddTwoNumbers`().addTwoNumbers(l3, l4)
    var current2 = result2
    while (current2 != null) {
        print("${current2.`val`} ")
        current2 = current2.next
    }
    println()
    println("----------------")

    val l5 = `$42_AddTwoNumbers`.ListNode(9).apply {
        next = `$42_AddTwoNumbers`.ListNode(9).apply {
            next = `$42_AddTwoNumbers`.ListNode(9).apply {
                next = `$42_AddTwoNumbers`.ListNode(9).apply {
                    next = `$42_AddTwoNumbers`.ListNode(9).apply {
                        next = `$42_AddTwoNumbers`.ListNode(9).apply {
                            next = `$42_AddTwoNumbers`.ListNode(9)
                        }
                    }
                }
            }
        }
    }
    val l6 = `$42_AddTwoNumbers`.ListNode(9).apply {
        next = `$42_AddTwoNumbers`.ListNode(9).apply {
            next = `$42_AddTwoNumbers`.ListNode(9).apply {
                next = `$42_AddTwoNumbers`.ListNode(9)
            }
        }
    }
    val result3 = `$42_AddTwoNumbers`().addTwoNumbers(l5, l6)
    var current3 = result3
    while (current3 != null) {
        print("${current3.`val`} ")
        current3 = current3.next
    }
    println()
}

class `$42_AddTwoNumbers` {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var carry = 0
        var l1 = l1
        var l2 = l2
        val dummyHead = ListNode(0)
        var current = dummyHead

        while (l1 != null || l2 != null) {
            val x = l1?.`val` ?: 0
            val y = l2?.`val` ?: 0
            val sum = carry + x + y
            carry = sum / 10
            current.next = ListNode(sum % 10)
            current = current.next!!
            if (l1 != null) l1 = l1.next
            if (l2 != null) l2 = l2.next
        }

        if (carry > 0) {
            current.next = ListNode(carry)
        }

        return dummyHead.next
    }
}