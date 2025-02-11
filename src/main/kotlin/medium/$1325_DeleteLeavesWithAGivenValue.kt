package medium

/**
 * Given a binary tree root and an integer target, delete all the leaf nodes with value target.
 *
 * Note that once you delete a leaf node with value target, if its parent node becomes a leaf node and has the value
 * target, it should also be deleted (you need to continue doing that until you cannot).
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 1000].
 * - 1 <= Node.val <= 1000
 *
 * Example 1:
 * - Input: root = [1,2,3,2,null,2,4], target = 2
 * - Output: [1,null,3,null,4]
 *
 * Example 2:
 * - Input: root = [1,3,3,3,2], target = 3
 * - Output: [1,3,null,null,2]
 *
 * Example 3:
 * - Input: root = [1,2,null,2,null,2], target = 2
 * - Output: [1]
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun removeLeafNodes(root: TreeNode?, target: Int): TreeNode? {
        if (root == null) return null
        if (root.left != null) root.left = removeLeafNodes(root.left, target)
        if (root.right != null) root.right = removeLeafNodes(root.right, target)

        return if (root.left == null && root.right == null && root.`val` == target) null else root
    }

    val root1 = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(2)
            right = TreeNode(4)
        }
        right = TreeNode(3).apply {
            left = TreeNode(2)
        }
    }
    println(removeLeafNodes(root1, 2)) // [1,null,3,null,4]

    val root2 = TreeNode(1).apply {
        left = TreeNode(3).apply {
            left = TreeNode(3)
            right = TreeNode(2)
        }
        right = TreeNode(3).apply {
            left = TreeNode(3)
            right = TreeNode(2)
        }
    }
    println(removeLeafNodes(root2, 3)) // [1,3,null,null,2]

    val root3 = TreeNode(1).apply {
        left = TreeNode(2).apply {
            right = TreeNode(2)
        }
    }
    println(removeLeafNodes(root3, 2)) // [1]
}
