package easy

/**
 * You are given the root of a binary search tree (BST) and an integer val.
 *
 * Find the node in the BST that the node's value equals val and return the subtree rooted with that node. If such a node does not exist, return null.
 *
 * constraints:
 * - The number of nodes in the tree is in the range [1, 5000].
 * - 1 <= Node.val <= 10^7
 * - root is a binary search tree.
 * - 1 <= val <= 10^7
 *
 * Example 1:
 * Input: root = [4,2,7,1,3], val = 2
 * Output: [2,1,3]
 *
 * Example 2:
 * Input: root = [4,2,7,1,3], val = 5
 * Output: []
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
        override fun toString(): String {
            return "TreeNode(`val`=$`val`, left=${left?.`val`}, right=${right?.`val`})"
        }


    }

    fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
        /**
         * base case:
         * - if root is null, return null
         * - if root's value is equal to val, return root
         */
        if (root == null || root.`val` == `val`) return root

        /**
         * if val is less than root's value, search the left subtree,
         * otherwise search the right subtree
         */
        if (`val` < root.`val`) {
            return searchBST(root.left, `val`)
        } else {
            return searchBST(root.right, `val`)
        }
    }

    val root1 = TreeNode(4)
    root1.left = TreeNode(2)
    root1.right = TreeNode(7)
    root1.left?.left = TreeNode(1)
    root1.left?.right = TreeNode(3)
    println(searchBST(root1, 2)) // TreeNode(`val`=2, left=1, right=3)

    val root2 = TreeNode(4)
    root2.left = TreeNode(2)
    root2.right = TreeNode(7)
    root2.left?.left = TreeNode(1)
    root2.left?.right = TreeNode(3)
    println(searchBST(root2, 5)) // null
}
