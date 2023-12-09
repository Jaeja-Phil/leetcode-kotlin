package medium

/**
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST.
 * Return the root node reference (possibly updated) of the BST.
 *
 * Basically, the deletion can be divided into two stages:
 * 1. Search for a node to remove.
 * 2. If the node is found, delete the node.
 *
 * constraints:
 * - The number of nodes in the tree is in the range [0, 10^4].
 * - -10^5 <= Node.val <= 10^5
 * - Each node has a unique value.
 * - root is a valid binary search tree.
 * - -10^5 <= key <= 10^5
 *
 * Example 1:
 * Input: root = [5,3,6,2,4,null,7], key = 3
 * Output: [5,4,6,2,null,null,7]
 *
 * Example 2:
 * Input: root = [5,3,6,2,4,null,7], key = 0
 * Output: [5,3,6,2,4,null,7]
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
        override fun toString(): String {
            return "TreeNode(`val`=$`val`, left=$left, right=$right)"
        }


    }

    fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
        /**
         * base case: if root is null, return null
         */
        if (root == null) return null

        /**
         * if the key is less than the root's value, search the left subtree for deletion,
         * if the key is greater than the root's value, search the right subtree for deletion,
         */
        if (key < root.`val`) {
            root.left = deleteNode(root.left, key)
        } else if (key > root.`val`) {
            root.right = deleteNode(root.right, key)
        } else {
            /**
             * if the key is equal to the root's value, we have found the node to delete
             * 3 cases:
             * - both left and right are null
             *   - return null
             * - both left and right are not null
             *   - find the minimum value in the right subtree
             *   - set the root's value to the minimum value
             *   - delete the minimum value from the right subtree
             *   - return the root
             * - only one child is null
             *   - return the non-null child
             */
            if (root.left == null && root.right == null) {
                return null
            } else if (root.left != null && root.right != null) {
                var min = root.right
                while (min?.left != null) {
                    min = min.left
                }
                root.`val` = min!!.`val`
                root.right = deleteNode(root.right, min.`val`)
            } else {
                return root.left ?: root.right
            }

        }

        return root
    }

    val root1 = TreeNode(5)
    root1.left = TreeNode(3)
    root1.right = TreeNode(6)
    root1.left?.left = TreeNode(2)
    root1.left?.right = TreeNode(4)
    root1.right?.right = TreeNode(7)
    println(deleteNode(root1, 3)) // TreeNode(`val`=5, left=TreeNode(`val`=4, left=2, right=), right=TreeNode(`val`=6, left=, right=7))

    val root2 = TreeNode(5)
    root2.left = TreeNode(3)
    root2.right = TreeNode(6)
    root2.left?.left = TreeNode(2)
    root2.left?.right = TreeNode(4)
    root2.right?.right = TreeNode(7)
    println(deleteNode(root2, 0)) // TreeNode(`val`=5, left=TreeNode(`val`=3, left=2, right=4), right=TreeNode(`val`=6, left=, right=7))
}