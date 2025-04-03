package medium

/**
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and
 * postorder is the postorder traversal of the same tree, construct and return the binary tree.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 3000].
 * - -1000 <= inorder[i], postorder[i] <= 1000
 * - inorder and postorder consist of unique values.
 * - Each value of postorder also appears in inorder.
 * - inorder is guaranteed to be the correct length.
 *
 * Example 1:
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        if (inorder.isEmpty() || postorder.isEmpty()) return null

        val rootValue = postorder.last()
        val root = TreeNode(rootValue)

        val inorderIndex = inorder.indexOf(rootValue)
        val leftInorder = inorder.sliceArray(0..<inorderIndex)
        val leftPostorder = postorder.sliceArray(0..<leftInorder.size)
        root.left = buildTree(leftInorder, leftPostorder)

        val rightInorder = inorder.sliceArray(inorderIndex + 1..<inorder.size)
        val rightPostorder = postorder.sliceArray(leftInorder.size..<postorder.size - 1)
        root.right = buildTree(rightInorder, rightPostorder)

        return root
    }

    val inorder = intArrayOf(9, 3, 15, 20, 7)
    val postorder = intArrayOf(9, 15, 7, 20, 3)
    val result = buildTree(inorder, postorder)
    println(result)
}