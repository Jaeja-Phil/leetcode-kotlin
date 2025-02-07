package easy

/**
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 100].
 * - -100 <= Node.val <= 100
 *
 * Example 1:
 * Input: root = [1,null,2,3]
 * output: [1,3,2]
 *
 * Example 2:
 * Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
 * Output: [4,2,6,5,7,1,3,9,8]
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun inorderTraversal(root: TreeNode?): List<Int> {
        // Solution 1. Recursive
        val result = mutableListOf<Int>()

        fun inorder(node: TreeNode?) {
            if (node == null) return
            inorder(node.left)
            result.add(node.`val`)
            inorder(node.right)
        }

        inorder(root)
        return result

        // Solution 2. Iterative
//        val result = mutableListOf<Int>()
//        val stack = mutableListOf<TreeNode>()
//        var current = root
//
//        while (current != null || stack.isNotEmpty()) {
//            while (current != null) {
//                stack.add(current)
//                current = current.left
//            }
//            current = stack.removeLast()
//            result.add(current.`val`)
//            current = current.right
//        }
//
//        return result
    }

    val root1 = TreeNode(1).apply {
        right = TreeNode(2).apply {
            left = TreeNode(3)
        }
    }
    println(inorderTraversal(root1)) // [1,3,2]

    val root2 = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(4)
            right = TreeNode(5).apply {
                left = TreeNode(6)
                right = TreeNode(7)
            }
        }
        right = TreeNode(3).apply {
            right = TreeNode(8).apply {
                left = TreeNode(9)
            }
        }
    }
    println(inorderTraversal(root2)) // [4,2,6,5,7,1,3,9,8]
}