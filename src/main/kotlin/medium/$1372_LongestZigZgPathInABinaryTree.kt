package medium

/**
 * You are given the root of a binary tree.
 *
 * A ZigZag path for a binary tree is defined as follow:
 *
 * Choose any node in the binary tree and a direction (right or left).
 * If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
 * Change the direction from right to left or from left to right.
 * Repeat the second and third steps until you can't move in the tree.
 * Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
 *
 * Return the longest ZigZag path contained in that tree.
 *
 * constraints:
 * - The number of nodes in the tree is in the range [1, 5 * 10^4].
 * - 1 <= Node.val <= 100
 *
 * Example 1:
 * Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
 * Output: 3
 *   1
 *    \
 *     1 <--
 *    / \
 *   1   1 <--
 *      / \
 * --> 1   1
 *     \
 *      1 <--
 *       \
 *        1
 *
 * Example 2:
 * Input: root = [1,1,1,null,1,null,null,1,1,null,1]
 * Output: 4
 *       1 <--
 *      / \
 * --> 1   1
 *      \
 *   --> 1
 *      / \
 * --> 1   1
 *      \
 *   --> 1
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun longestZigZag(root: TreeNode?): Int {
        /**
         * create a variable to keep track of the max length
         */
        var max = 0
        fun dfs(node: TreeNode?, isLeft: Boolean, count: Int) {
            /**
             * base case for recursion
             */
            if (node == null) return

            /**
             * update the max length
             */
            max = maxOf(max, count)

            if (isLeft) {
                /**
                 * if the direction is left, then only going right has a chance to increase the length
                 * go right with count + 1, and go left with count = 1 (reset the count)
                 */
                dfs(node.right, false, count + 1)
                dfs(node.left, true, 1)
            } else {
                /**
                 * do the opposite if the direction is right
                 */
                dfs(node.left, true, count + 1)
                dfs(node.right, false, 1)
            }
        }

        /**
         * do dfs twice, one with the direction left, and one with the direction right
         */
        dfs(root, true, 0)
        dfs(root, false, 0)

        /**
         * return the max length
         */
        return max
    }

    val root = TreeNode(1)
    root.right = TreeNode(1)
    root.right?.left = TreeNode(1)
    root.right?.right = TreeNode(1)
    root.right?.right?.left = TreeNode(1)
    root.right?.right?.right = TreeNode(1)
    root.right?.right?.left?.right = TreeNode(1)
    root.right?.right?.left?.right?.right = TreeNode(1)
    println(longestZigZag(root)) // 3

    val root2 = TreeNode(1)
    root2.left = TreeNode(1)
    root2.right = TreeNode(1)
    root2.left?.right = TreeNode(1)
    root2.left?.right?.left = TreeNode(1)
    root2.left?.right?.right = TreeNode(1)
    root2.left?.right?.left?.right = TreeNode(1)
    println(longestZigZag(root2)) // 4

}
