package medium

/**
 * Given the root of a binary tree, return the maximum width of the given tree.
 *
 * The maximum width of a tree is the maximum width among all levels.
 *
 * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes),
 * where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that
 * level are also counted into the length calculation.
 *
 * It is guaranteed that the answer will in the range of a 32-bit signed integer.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 3000].
 * - -100 <= Node.val <= 100
 *
 * Example 1:
 * Input: root = [1,3,2,5,3,null,9]
 * Output: 4
 *
 * Example 2:
 * Input: root = [1,3,2,5,null,null,9,6,null,7]
 * Output: 7
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun widthOfBinaryTree(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }

        var result = 0
        val queue = ArrayDeque<Pair<TreeNode, Int>>()
        queue.add(root to 0)
        while (queue.isNotEmpty()) {
            val currLevelSize = queue.size
            var first = Int.MAX_VALUE
            var last = Int.MIN_VALUE
            for (i in 0..<currLevelSize) {
                val (node, loc) = queue.removeFirst()
                if (i == 0) {
                    first = loc
                }
                if (i == currLevelSize - 1) {
                    last = loc
                }

                node.left?.let {
                    queue.add(it to loc * 2 + 1)
                }
                node.right?.let {
                    queue.add(it to loc * 2 + 2)
                }
            }

            result = maxOf(result, last - first + 1)
        }

        return result
    }

    val root = TreeNode(1).apply {
        left = TreeNode(3).apply {
            left = TreeNode(5)
            right = TreeNode(3)
        }
        right = TreeNode(2).apply {
            right = TreeNode(9)
        }
    }
    println(widthOfBinaryTree(root)) // Output: 4

    val root2 = TreeNode(1).apply {
        left = TreeNode(3).apply {
            left = TreeNode(5)
        }
        right = TreeNode(2).apply {
            right = TreeNode(9).apply {
                left = TreeNode(6)
                right = TreeNode(7)
            }
        }
    }
    println(widthOfBinaryTree(root2)) // Output: 7
}
