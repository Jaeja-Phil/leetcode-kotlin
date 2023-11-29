package easy

/**
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * constraints:
 * - The number of nodes in the tree is in the range [0, 10^4].
 * - -100 <= Node.val <= 100
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 * Explanation:
 *        3
 *       / \
 *      9  20
 *        /  \
 *       15   7
 *
 * Example 2:
 * Input: root = [1,null,2]
 * Output: 2
 * Explanation:
 *    1
 *     \
 *      2
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun `Maximum Depth of Binary Tree`(root: TreeNode?): Int {
        /**
         * base case: if root is null, return 0,
         * if root.left and root.right are null, return 1
         */
        if (root == null) {
            return 0
        }
        if (root.left == null && root.right == null) {
            return 1
        }

        /**
         * create a variable to keep track of the maximum depth
         */
        var maxDepth = 0

        /**
         * BFS approach, create a queue to store the nodes
         */
        val queue = ArrayDeque<TreeNode>()

        /**
         * add current node to the queue
         */
        queue.add(root)

        /**
         * iterate through the queue while it is not empty
         */
        while (queue.isNotEmpty()) {
            /**
             * current depth scan - add 1 to the max depth
             */
            maxDepth++

            /**
             * hold count of current nodes in the queue
             * - this is to ensure that we only iterate through the current level of nodes
             */
            val count = queue.size

            /**
             * iterate through the current level of nodes
             */
            for (i in 0..< count) {
                /**
                 * take the current node out of the queue
                 */
                queue.removeFirst().let { currNode ->
                    /**
                     * add the left and right nodes to the queue if they are not null
                     */
                    currNode.left?.let { queue.add(it) }
                    currNode.right?.let { queue.add(it) }
                }
            }
        }

        /**
         * return the maximum depth
         */
        return maxDepth
    }

    val head1 = TreeNode(3)
    head1.left = TreeNode(9)
    head1.right = TreeNode(20)
    head1.right?.left = TreeNode(15)
    head1.right?.right = TreeNode(7)
    println(`Maximum Depth of Binary Tree`(head1)) // 3

    val head2 = TreeNode(1)
    head2.right = TreeNode(2)
    println(`Maximum Depth of Binary Tree`(head2)) // 2
}