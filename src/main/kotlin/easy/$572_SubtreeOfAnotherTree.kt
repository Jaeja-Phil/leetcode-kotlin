package easy

/**
 * Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same
 * structure and node values of subRoot and false otherwise.
 *
 * A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The
 * tree "tree" could also be considered as a subtree of itself.
 *
 * Constraints:
 * - The number of nodes in the root tree is in the range [1, 2000].
 * - The number of nodes in the subRoot tree is in the range [1, 1000].
 * - -10^4 <= root.val <= 10^4
 * - -10^4 <= subRoot.val <= 10^4
 *
 * Example 1:
 * Input: root = [3,4,5,1,2], subRoot = [4,1,2]
 * Output: true
 *
 * Example 2:
 * Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * Output: false
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
        fun isSameTree(node1: TreeNode?, node2: TreeNode?): Boolean {
            if (node1 == null && node2 == null) return true
            if (node1 == null || node2 == null || node1.`val` != node2.`val`) return false

            return isSameTree(node1.left, node2.left) && isSameTree(node1.right, node2.right)
        }

        fun dfs(node: TreeNode?): Boolean {
            if (node == null) return false
            if (isSameTree(node, subRoot)) return true

            return dfs(node.left) || dfs(node.right)
        }

        return dfs(root)
    }

    val root1 = TreeNode(3).apply {
        left = TreeNode(4)
        right = TreeNode(5).apply {
            left = TreeNode(1)
            right = TreeNode(2)
        }
    }
    val subRoot1 = TreeNode(4).apply {
        left = TreeNode(1)
        right = TreeNode(2)
    }
    println(isSubtree(root1, subRoot1)) // true

    val root2 = TreeNode(3).apply {
        left = TreeNode(4)
        right = TreeNode(5).apply {
            left = TreeNode(1)
            right = TreeNode(2).apply {
                left = TreeNode(0)
            }
        }
    }
    val subRoot2 = TreeNode(4).apply {
        left = TreeNode(1)
        right = TreeNode(2)
    }
}