package medium

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between
 * two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a
 * node to be a descendant of itself).”
 *
 * constraints:
 * - The number of nodes in the tree is in the range [2, 10^5].
 * - -10^9 <= Node.val <= 10^9
 * - All Node.val are unique.
 * - p != q
 * - p and q will exist in the tree.
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation:
 *         3 <--
 *      5     1
 *    6  2   0  8
 *      7 4
 *
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation:
 *        3
 * --> 5     1
 *   6  2   0  8
 *     7 4
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        /**
         * create a variable to keep track of the lowest common ancestor
         */
        var lca: TreeNode? = null
        fun dfs(node: TreeNode?): Boolean {
            /**
             * base case: if the node is null, return false
             */
            if (node == null) return false

            /**
             * create a variable to keep track of the number of matches
             */
            var matches = 0

            /**
             * if the node is the same as p or q, increment matches
             */
            if (node == p || node == q) matches++

            /**
             * if the left child is not null, recursively call dfs on the left child
             * if the left child is the lowest common ancestor, increment matches
             */
            if (node.left != null) {
                if (dfs(node.left)) matches++
            }

            /**
             * if the right child is not null, recursively call dfs on the right child
             * if the right child is the lowest common ancestor, increment matches
             */
            if (node.right != null) {
                if (dfs(node.right)) matches++
            }

            /**
             * if matches is equal to 2, set the lowest common ancestor to the current node
             * this
             */
            if (matches == 2) lca = node

            /**
             * return true if matches is greater than 0
             */
            return matches > 0
        }
        /**
         * call dfs on the root
         */
        dfs(root)
        /**
         * return the lowest common ancestor
         */
        return lca
    }

    val root = TreeNode(3)
    root.left = TreeNode(5)
    root.right = TreeNode(1)
    root.left?.left = TreeNode(6)
    root.left?.right = TreeNode(2)
    root.right?.left = TreeNode(0)
    root.right?.right = TreeNode(8)
    root.left?.right?.left = TreeNode(7)
    root.left?.right?.right = TreeNode(4)
    println(lowestCommonAncestor(root, root.left, root.right)?.`val`) // 3
    println(lowestCommonAncestor(root, root.left, root.left?.right?.right)?.`val`) // 5
}
