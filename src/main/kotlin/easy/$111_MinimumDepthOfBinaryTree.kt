package easy

/**
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * Note: A leaf is a node with no children.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 10^5].
 * - -1000 <= Node.val <= 1000
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 2
 *
 * Example 2:
 * Input: root = [2,null,3,null,4,null,5,null,6]
 * Output: 5
 *
 * Example 3:
 * Input: root = []
 * Output: 0
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun isLeafNode(node: TreeNode?): Boolean =
        node != null && node.left == null && node.right == null

    fun minDepth(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }

        val q = ArrayDeque<TreeNode>()
        q.add(root)

        var height = 1
        while (q.isNotEmpty()) {
            val size = q.size
            repeat(size) {
                val node = q.removeFirst()
                if (isLeafNode(node)) {
                    return height
                }
                node.left?.let { q.add(it) }
                node.right?.let { q.add(it) }
            }

            height++
        }

        return height
    }

    // Example usage
    val root = TreeNode(3).apply {
        left = TreeNode(9)
        right = TreeNode(20).apply {
            left = TreeNode(15)
            right = TreeNode(7)
        }
    }
    println(minDepth(root)) // Output: 2
    val root2 = TreeNode(2).apply {
        right = TreeNode(3).apply {
            right = TreeNode(4).apply {
                right = TreeNode(5).apply {
                    right = TreeNode(6)
                }
            }
        }
    }
    println(minDepth(root2)) // Output: 5
}