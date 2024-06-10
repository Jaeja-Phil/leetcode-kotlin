package easy

import java.util.ArrayDeque

/**
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 *
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 *
 * Constraints:
 * - The number of nodes in both trees is in the range [0, 100].
 * - -10^4 <= Node.val <= 10^4
 *
 * Example 1:
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 *
 * Example 2:
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 *
 * Example 3:
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 */
fun main() {
    val p = `$100_SameTree`.TreeNode(1)
    p.left = `$100_SameTree`.TreeNode(2)
    p.right = `$100_SameTree`.TreeNode(3)
    val q = `$100_SameTree`.TreeNode(1)
    q.left = `$100_SameTree`.TreeNode(2)
    q.right = `$100_SameTree`.TreeNode(3)
    val result = `$100_SameTree`().isSameTree(p, q)
    println(result) // true

    val p2 = `$100_SameTree`.TreeNode(1)
    p2.left = `$100_SameTree`.TreeNode(2)
    val q2 = `$100_SameTree`.TreeNode(1)
    q2.right = `$100_SameTree`.TreeNode(2)
    val result2 = `$100_SameTree`().isSameTree(p2, q2)
    println(result2) // false

    val p3 = `$100_SameTree`.TreeNode(1)
    p3.left = `$100_SameTree`.TreeNode(2)
    p3.right = `$100_SameTree`.TreeNode(1)
    val q3 = `$100_SameTree`.TreeNode(1)
    q3.left = `$100_SameTree`.TreeNode(1)
    q3.right = `$100_SameTree`.TreeNode(2)
    val result3 = `$100_SameTree`().isSameTree(p3, q3)
    println(result3) // false
}

class `$100_SameTree` {
    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        // base case
        if (p == null && q == null) return true
        if ((p == null && q != null) || (p != null && q == null)) return false
        if (p!!.`val` != q!!.`val`) return false

        // bfs approach
        val queue = ArrayDeque <Pair<TreeNode, TreeNode>>()
        queue.add(Pair(p, q))

        while (queue.isNotEmpty()) {
            val size = queue.size
            repeat(size) {
                val (nodeP, nodeQ) = queue.poll()
                /**
                 * return false if...
                 * - if only one of the left is null
                 * - if only one of the right is null
                 */
                if (
                    (nodeP.left == null && nodeQ.left != null) ||
                    (nodeP.left != null && nodeQ.left == null) ||
                    (nodeP.right == null && nodeQ.right != null) ||
                    (nodeP.right != null && nodeQ.right == null)
                ) {
                    return false
                }

                /**
                 * return false if...
                 * - left values are not equal
                 * - right values are not equal
                 */
                if (
                    (nodeP.left?.`val` != nodeQ.left?.`val`) ||
                    (nodeP.right?.`val` != nodeQ.right?.`val`)
                ) {
                    return false
                }

                if (nodeP.left != null && nodeQ.left != null) {
                    queue.add(Pair(nodeP.left!!, nodeQ.left!!))
                }
                if (nodeP.right != null && nodeQ.right != null) {
                    queue.add(Pair(nodeP.right!!, nodeQ.right!!))
                }
            }
        }

        return true
    }
}