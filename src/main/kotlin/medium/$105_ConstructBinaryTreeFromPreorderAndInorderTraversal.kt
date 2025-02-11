package medium

/**
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder
 * is the inorder traversal of the same tree, construct and return the binary tree.
 *
 * Constraints:
 * - 1 <= preorder.length <= 3000
 * - inorder.length == preorder.length
 * - -3000 <= preorder[i], inorder[i] <= 3000
 * - preorder and inorder consist of unique values.
 * - Each value of inorder also appears in preorder.
 * - preorder is guaranteed to be the preorder traversal of the tree.
 * - inorder is guaranteed to be the inorder traversal of the tree.
 *
 * Example 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 *
 * Example 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        if (preorder.isEmpty() || inorder.isEmpty()) return null

        val root = TreeNode(preorder[0]) // first element of preorder is the root
        val mid = inorder.indexOf(preorder[0]) // find the root in inorder, the root divides inorder into left and right subtrees
        root.left = buildTree(preorder.copyOfRange(1, mid + 1), inorder.copyOfRange(0, mid))
        root.right = buildTree(preorder.copyOfRange(mid + 1, preorder.size), inorder.copyOfRange(mid + 1, inorder.size))

        return root
    }

    println(buildTree(intArrayOf(3,9,20,15,7), intArrayOf(9,3,15,20,7))) // [3,9,20,null,null,15,7]
    println(buildTree(intArrayOf(-1), intArrayOf(-1))) // [-1]
}