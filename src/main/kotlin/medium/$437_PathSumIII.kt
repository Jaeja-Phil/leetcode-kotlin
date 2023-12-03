package medium

import java.util.Deque

/**
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values
 * along the path equals targetSum.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only
 * from parent nodes to child nodes).
 *
 * constraints:
 * - The number of nodes in the tree is in the range [0, 1000].
 * - -10^9 <= Node.val <= 10^9
 * - -1000 <= targetSum <= 1000
 *
 * Example 1:
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 *
 * Example 2:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: 3
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun pathSum(root: TreeNode?, targetSum: Int): Int {
        if (root == null) return 0

        fun pathSumToNode(root: TreeNode?, targetSum: Long): Int {
            /**
             * base case: if root is null, return 0
             */
            if (root == null) return 0

            /**
             * create a variable to store the count of paths that sum to targetSum
             */
            var count = 0

            /**
             * if the current node's value is equal to targetSum, increment count
             */
            if (targetSum - root.`val` == 0L) {
                count++
            }

            /**
             * recursively call pathSumToNode on the left and right subtrees
             * and add the returned values to count
             * - subtract the current node's value from targetSum
             */
            count += pathSumToNode(root.left, targetSum - root.`val`.toLong())
            count += pathSumToNode(root.right, targetSum - root.`val`.toLong())

            /**
             * return count
             */
            return count
        }

        /**
         * recursively call pathSumToNode on
         * - the current node
         * - the left subtree
         * - the right subtree
         * why do we need to call pathSumToNode on the left and right subtrees on top of the current node?
         * - this is to have current node included version vs current node excluded version
         */
        return pathSumToNode(root, targetSum.toLong()) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum)
    }

    val root = TreeNode(10)
    root.left = TreeNode(5)
    root.right = TreeNode(-3)
    root.left?.left = TreeNode(3)
    root.left?.right = TreeNode(2)
    root.right?.right = TreeNode(11)
    root.left?.left?.left = TreeNode(3)
    root.left?.left?.right = TreeNode(-2)
    root.left?.right?.right = TreeNode(1)
    println("Path Sum III: ${pathSum(root, 8)}") // 3

    val root2 = TreeNode(5)
    root2.left = TreeNode(4)
    root2.right = TreeNode(8)
    root2.left?.left = TreeNode(11)
    root2.right?.left = TreeNode(13)
    root2.right?.right = TreeNode(4)
    root2.left?.left?.left = TreeNode(7)
    root2.left?.left?.right = TreeNode(2)
    root2.right?.right?.left = TreeNode(5)
    root2.right?.right?.right = TreeNode(1)
    println("Path Sum III: ${pathSum(root2, 22)}") // 3
}
