package easy

/**
 * Given the root of a complete binary tree, count the number of nodes in the tree.
 *
 * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and
 * all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last
 * level h.
 *
 * Design an algorithm that runs in less than O(n) time complexity.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 5 * 104].
 * - 0 <= Node.val <= 5 * 104
 * - The tree is guaranteed to be complete.
 *
 * Example 1:
 * Input: root = [1,2,3,4,5,6]
 * Output: 6
 *
 * Example 2:
 * Input: root = []
 * Output: 0
 *
 * Example 3:
 * Input: root = [1]
 * Output: 1
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun countNodes(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }

        val q = ArrayDeque<TreeNode>()
        q.add(root)
        var count = 0

        while (q.isNotEmpty()) {
            val size = q.size
            count += size
            repeat(size) { idx ->
                val node = q.removeFirst()
                node.left?.let { q.add(it) }
                node.right?.let { q.add(it) }
            }
        }

        return count
    }

    fun printTree(root: TreeNode?) {
        if (root == null) {
            println("null")
            return
        }

        val q = ArrayDeque<TreeNode?>()
        q.add(root)

        while (q.isNotEmpty()) {
            val size = q.size
            repeat(size) { idx ->
                val node = q.removeFirst()
                if (node != null) {
                    print("${node.`val`} ")
                    q.add(node.left)
                    q.add(node.right)
                } else {
                    print("null ")
                }
            }
            println()
        }
    }

    val root = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(4)
            right = TreeNode(5)
        }
        right = TreeNode(3).apply {
            left = TreeNode(6)
        }
    }
    val result = countNodes(root)
    println("Count of nodes: $result")

    println("Tree structure:")
    printTree(root)
}