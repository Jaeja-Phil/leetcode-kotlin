package easy

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 *
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may
 * not pass through the root.
 *
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 10^4].
 * - -100 <= Node.val <= 100
 *
 * Example 1:
 * Input: root = [1,2,3,4,5]
 * Output: 3
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun diameterOfBinaryTree(root: TreeNode?): Int {
        var result = 0
        fun dfs(root: TreeNode?): Int {
            if (root == null) {
                return 0
            }
            if (root.left == null && root.right == null) {
                return 1
            }

            // check left & right's diameter
            val left = dfs(root.left)
            val right = dfs(root.right)

            result = maxOf(result, left + right)

            return 1 + maxOf(left, right)
        }

        dfs(root)

        return result
    }

    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left?.left = TreeNode(4)
    root.left?.right = TreeNode(5)

    println(diameterOfBinaryTree(root)) // 3
}