package medium

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
 *
 * Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all
 * houses in this place form a binary tree. It will automatically contact the police if two directly-linked houses were
 * broken into on the same night.
 *
 * Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 10^4].
 * - 0 <= Node.val <= 10^4
 *
 * Example 1:
 * Input: root = [3,2,3,null,3,null,1]
 * Output: 7
 *
 * Example 2:
 * Input: root = [3,4,5,1,3,null,1]
 * Output: 9
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun helper(node: TreeNode?): Pair<Int, Int> {
        if (node == null) return 0 to 0

        val left = helper(node.left)
        val right = helper(node.right)
        val taken = node.`val` + left.second + right.second
        val notTaken = maxOf(left.first, left.second) + maxOf(right.first, right.second)

        return taken to notTaken
    }

    fun rob(root: TreeNode?): Int {
        val (taken, notTaken) = helper(root)
        return maxOf(taken, notTaken)
    }

    val root1 = TreeNode(3).apply {
        left = TreeNode(2).apply {
            right = TreeNode(3).apply {
                right = TreeNode(1)
            }
        }
    }
    println(rob(root1)) // 7

    val root2 = TreeNode(3).apply {
        left = TreeNode(4).apply {
            left = TreeNode(1)
            right = TreeNode(3)
        }
        right = TreeNode(5).apply {
            right = TreeNode(1)
        }
    }
    println(rob(root2)) // 9
}