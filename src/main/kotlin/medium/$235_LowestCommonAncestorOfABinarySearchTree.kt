package medium

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as
 * the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [2, 10^5].
 * - -10^9 <= Node.val <= 10^9
 * - All Node.val are unique.
 * - p != q
 * - p and q will exist in the BST.
 *
 * Example 1:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 *
 * Example 2:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA
 * definition.
 *
 * Example 3:
 * Input: root = [2,1], p = 2, q = 1
 * Output: 2
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        val lower = minOf(p!!.`val`, q!!.`val`)
        val upper = maxOf(p.`val`, q.`val`)
        var curr = root
        while (curr != null) {
            curr = when {
                curr.`val` < lower -> curr.right
                curr.`val` > upper -> curr.left
                else -> return curr
            }
        }

        return null
    }

    val root1 = TreeNode(6).apply {
        left = TreeNode(2).apply {
            left = TreeNode(0)
            right = TreeNode(4).apply {
                left = TreeNode(3)
                right = TreeNode(5)
            }
        }
        right = TreeNode(8).apply {
            left = TreeNode(7)
            right = TreeNode(9)
        }
    }
    println(lowestCommonAncestor(root1, TreeNode(2), TreeNode(8))?.`val`) // 6
    println(lowestCommonAncestor(root1, TreeNode(2), TreeNode(4))?.`val`) // 2

    val root3 = TreeNode(2).apply {
        left = TreeNode(1)
    }
    println(lowestCommonAncestor(root3, TreeNode(2), TreeNode(1))?.`val`) // 2
}