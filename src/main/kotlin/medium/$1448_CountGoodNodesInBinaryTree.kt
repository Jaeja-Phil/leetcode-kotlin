package medium

/**
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes
 * with a value greater than X.
 *
 * Return the number of good nodes in the binary tree.
 *
 * constraints:
 * - The number of nodes in the binary tree is in the range [1, 10^5].
 * - Each node's value is between [-10^4, 10^4].
 *
 * Example 1:
 * Input: root = [3,1,4,3,null,1,5]
 * Output: 4
 *
 * Example 2:
 * Input: root = [3,3,null,4,2]
 * Output: 3
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

    fun `Count Good Nodes in Binary Tree`(root: TreeNode?): Int {
        /**
         * base case: if root is null, return 0, if the left and right nodes are null, return 1
         */
        if (root == null) return 0
        if (root.left == null && root.right == null) return 1

        /**
         * create a recursive function to perform dfs on the tree
         * - take two parameters
         *   - the iterating node
         *   - the max value of the path so far
         */
        fun dfs(node: TreeNode?, max: Int): Int {
            // base case
            if (node == null) return 0
            if (node.`val` < max) {
                /**
                 * if current node's value is less than the max value of the path so far, then we don't need to
                 * further investigate the current node. move to the next node
                 */
                return dfs(node.left, max) + dfs(node.right, max)
            }

            /**
             * case when current node's value is greater or equal to the max value of the path so far
             * - update max value to current node's value (since it is equal or greater)
             */
            return 1 + dfs(node.left, node.`val`) + dfs(node.right, node.`val`)
        }

        return dfs(root, root.`val`)
    }

    val root1 = TreeNode(3)
    root1.left = TreeNode(1)
    root1.right = TreeNode(4)
    root1.left?.left = TreeNode(3)
    root1.right?.left = TreeNode(1)
    root1.right?.right = TreeNode(5)
    println(`Count Good Nodes in Binary Tree`(root1)) // 4

    val root2 = TreeNode(3)
    root2.left = TreeNode(3)
    root2.left?.right = TreeNode(4)
    root2.left?.right?.left = TreeNode(2)
    println(`Count Good Nodes in Binary Tree`(root2)) // 3

    val root3 = TreeNode(1)
    println(`Count Good Nodes in Binary Tree`(root3)) // 1
}