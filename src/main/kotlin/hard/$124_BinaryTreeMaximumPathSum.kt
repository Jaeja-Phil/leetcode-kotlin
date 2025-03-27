package hard

/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge
 * connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass
 * through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 3 * 10^4].
 * - -1000 <= Node.val <= 1000
 *
 * Example 1:
 * Input: root = [1,2,3]
 * Output: 6
 *
 * Example 2:
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun maxPathSum(root: TreeNode?): Int {
        if (root?.left == null && root?.right == null) {
            return root!!.`val`
        }

        var result = -1000

        fun backtrack(root: TreeNode?): Int {
            // base case for backtrack
            if (root == null) {
                return -1000
            }

            val left = backtrack(root.left)
            val right = backtrack(root.right)

            val currNodeValue = root.`val`
            var greatestPathSumAtCurr = currNodeValue
            if (left > 0) {
                greatestPathSumAtCurr += left
            }
            if (right > 0) {
                greatestPathSumAtCurr += right
            }

            result = maxOf(result, greatestPathSumAtCurr)

            return maxOf(
                currNodeValue,
                currNodeValue + left,
                currNodeValue + right,
            )
        }

        backtrack(root)

        return result
    }

    val root1 = TreeNode(1).apply {
        left = TreeNode(2)
        right = TreeNode(3)
    }
    println(maxPathSum(root1)) // 6

    val root2 = TreeNode(-10).apply {
        left = TreeNode(9)
        right = TreeNode(20).apply {
            left = TreeNode(15)
            right = TreeNode(7)
        }
    }
    println(maxPathSum(root2)) // 42
}