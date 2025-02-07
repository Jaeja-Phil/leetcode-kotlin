package easy

/**
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 100].
 * - -100 <= Node.val <= 100
 *
 * Example 1:
 * Input: root = [1,null,2,3]
 * Output: [1,2,3]
 *
 * Example 2:
 * Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
 * Output: [1,2,4,5,3,6,7,9,8]
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun preorderTraversal(root: TreeNode?): List<Int> {
        // Solution 1. Recursive
//        val result = mutableListOf<Int>()
//
//        fun recursion(node: TreeNode?) {
//            if (node == null) return
//            result.add(node.`val`)
//            recursion(node.left)
//            recursion(node.right)
//        }
//
//        recursion(root)
//        return result

        // Solution 2. Iterative
        val result = mutableListOf<Int>()
        val stack = mutableListOf<TreeNode>()
        var current = root
        while (current != null || stack.isNotEmpty()) {
            while (current != null) {
                result.add(current.`val`)
                current.right?.let { stack.add(it) }
                current = current.left
            }

            if (stack.isNotEmpty()) {
                current = stack.removeLast()
            }
        }

        return result
    }

    val root1 = TreeNode(1).apply {
        right = TreeNode(2).apply {
            left = TreeNode(3)
        }
    }
    println(preorderTraversal(root1)) // [1,2,3]

    val root2 = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(4)
            right = TreeNode(5)
        }
        right = TreeNode(3).apply {
            left = TreeNode(6).apply {
                left = TreeNode(7)
                right = TreeNode(9).apply {
                    left = TreeNode(8)
                }
            }
        }
    }
    println(preorderTraversal(root2)) // [1,2,4,5,3,6,7,9,8]
}