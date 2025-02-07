package easy

/**
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 *
 * Constraints:
 * - The number of the nodes in the tree is in the range [0, 100].
 * - -100 <= Node.val <= 100
 *
 * Example 1:
 * Input: root = [1,null,2,3]
 * Output: [3,2,1]
 *
 * Example 2:
 * Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
 * Output: [4,6,7,5,9,8,2,3,1]
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun postorderTraversal(root: TreeNode?): List<Int> {
        // Solution 1. Recursive
        val result = mutableListOf<Int>()

        fun recursion(node: TreeNode?) {
            if (node == null) return
            recursion(node.left)
            recursion(node.right)
            result.add(node.`val`)
        }

        recursion(root)
        return result

        // Solution 2. Iterative
//        val result = mutableListOf<Int>()
//        val stack = mutableListOf<TreeNode>()
//        var current = root
//
//        while (current != null || stack.isNotEmpty()) {
//            while (current != null) {
//                current.right?.let { stack.add(it) }
//                stack.add(current)
//                current = current.left
//            }
//
//            current = stack.removeLast()
//
//            if (stack.isNotEmpty() && current.right == stack.last()) {
//                stack.removeLast()
//                stack.add(current)
//                current = current.right
//            } else {
//                result.add(current.`val`)
//                current = null
//            }
//        }
//
//        return result
    }

    val root1 = TreeNode(1).apply {
        right = TreeNode(2).apply {
            left = TreeNode(3)
        }
    }
    println(postorderTraversal(root1)) // [3, 2, 1]

    val root2 = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(4)
            right = TreeNode(5)
        }
        right = TreeNode(3).apply {
            left = TreeNode(6).apply {
                left = TreeNode(8)
                right = TreeNode(9)
            }
            right = TreeNode(7)
        }
    }
    println(postorderTraversal(root2)) // [4, 5, 2, 8, 9, 6, 7, 3, 1]
}