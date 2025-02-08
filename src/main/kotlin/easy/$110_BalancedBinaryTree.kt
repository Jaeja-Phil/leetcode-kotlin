package easy

import kotlin.math.abs

/**
 * Given a binary tree, determine if it is height-balanced.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 5000].
 * - -10^4 <= Node.val <= 10^4
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 *
 * Example 2:
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 *
 * Example 3:
 * Input: root = []
 * Output: true
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun isBalanced(root: TreeNode?): Boolean {
        fun dfs(node: TreeNode?, level: Int): Int {
            if (node == null) return level

            val left = dfs(node.left, level + 1)
            val right = dfs(node.right, level + 1)

            if (left == -1 || right == -1 || abs(left - right) > 1) return -1

            return left.coerceAtLeast(right)
        }

        return dfs(root, 0) != -1
    }

    val root1 = TreeNode(3).apply {
        left = TreeNode(9)
        right = TreeNode(20).apply {
            left = TreeNode(15)
            right = TreeNode(7)
        }
    }
    println(isBalanced(root1)) // true

    val root2 = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(3).apply {
                left = TreeNode(4)
                right = TreeNode(4)
            }
            right = TreeNode(3)
        }
        right = TreeNode(2)
    }
    println(isBalanced(root2)) // false
}