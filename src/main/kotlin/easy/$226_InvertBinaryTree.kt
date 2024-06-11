package easy

/**
 * Given the root of a binary tree, invert the tree, and return its root.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 100].
 * - -100 <= Node.val <= 100
 *
 * Example 1:
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 *
 * Example 2:
 * Input: root = [2,1,3]
 * Output: [2,3,1]
 *
 */
fun main() {
    val root = `$226_InvertBinaryTree`.TreeNode(4)
    root.left = `$226_InvertBinaryTree`.TreeNode(2)
    root.right = `$226_InvertBinaryTree`.TreeNode(7)
    root.left?.left = `$226_InvertBinaryTree`.TreeNode(1)
    root.left?.right = `$226_InvertBinaryTree`.TreeNode(3)
    root.right?.left = `$226_InvertBinaryTree`.TreeNode(6)
    root.right?.right = `$226_InvertBinaryTree`.TreeNode(9)
}

class `$226_InvertBinaryTree` {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun invertTree(root: TreeNode?): TreeNode? {
        if (root == null) return null

        val left = invertTree(root.left)
        val right = invertTree(root.right)

        root.left = right
        root.right = left

        return root
    }
}