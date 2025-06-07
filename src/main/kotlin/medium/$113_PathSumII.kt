package medium

/**
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node
 * values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.
 *
 * A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 5000].
 * - -1000 <= Node.val <= 1000
 * - -1000 <= targetSum <= 1000
 *
 * Example 1:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 *
 * Example 2:
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 *
 * Example 3:
 * Input: root = [1,2], targetSum = 0
 * Output: []
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun dfs(node: TreeNode?, currentPath: MutableList<Int>, currentSum: Int) {
            if (node == null) return

            currentPath.add(node.`val`)
            val newSum = node.`val` + currentSum

            // Check if it's a leaf node and the sum matches
            if (node.left == null && node.right == null && newSum == targetSum) {
                result.add(ArrayList(currentPath)) // Add a copy of the current path
            } else {
                // Continue to traverse left and right children
                dfs(node.left, currentPath, newSum)
                dfs(node.right, currentPath, newSum)
            }

            // Backtrack
            currentPath.removeAt(currentPath.size - 1)
        }

        dfs(root, mutableListOf(), 0)
        return result
    }

    // Example usage:
    val root1 = TreeNode(5).apply {
        left = TreeNode(4).apply {
            left = TreeNode(11).apply {
                left = TreeNode(7)
                right = TreeNode(2)
            }
        }
        right = TreeNode(8).apply {
            left = TreeNode(13)
            right = TreeNode(4).apply {
                right = TreeNode(1)
            }
        }
    }
    val targetSum1 = 22
    println(pathSum(root1, targetSum1)) // Output: [[5, 4, 11, 2], [5, 8, 4, 5]]

    val root2 = TreeNode(1).apply {
        left = TreeNode(2)
        right = TreeNode(3)
    }
    val targetSum2 = 5
    println(pathSum(root2, targetSum2)) // Output: []

    val root3 = TreeNode(1).apply {
        left = TreeNode(2)
    }
    val targetSum3 = 0
    println(pathSum(root3, targetSum3)) // Output: []
}
