package medium

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 * - The left subtree of a node contains only nodes with keys less than the node's key.
 * - The right subtree of a node contains only nodes with keys greater than the node's key.
 * - Both the left and right subtrees must also be binary search trees.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 10^4].
 * - -2^31 <= Node.val <= 2^31 - 1
 *
 * Example 1:
 * Input: root = [2,1,3]
 * Output: true
 *
 * Example 2:
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun isValidBST(root: TreeNode?): Boolean {
        fun isValid(node: TreeNode?, lower: Long, upper: Long): Boolean {
            if (node == null) return true
            if (node.`val` <= lower || node.`val` >= upper) return false
            return isValid(node.left, lower, node.`val`.toLong()) && isValid(node.right, node.`val`.toLong(), upper)
        }

        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE)
    }

    val root1 = TreeNode(2).apply {
        left = TreeNode(1)
        right = TreeNode(3)
    }
    println(isValidBST(root1)) // true

    val root2 = TreeNode(5).apply {
        left = TreeNode(1)
        right = TreeNode(4).apply {
            left = TreeNode(3)
            right = TreeNode(6)
        }
    }
    println(isValidBST(root2)) // false
}
