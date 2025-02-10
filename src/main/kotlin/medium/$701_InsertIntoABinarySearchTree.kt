package medium

/**
 * You are given the root node of a binary search tree (BST) and a value to insert into the tree. Return the root node
 * of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
 *
 * Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion.
 * You can return any of them.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 10^4].
 * - -10^8 <= Node.val <= 10^8
 * - All the values Node.val are unique.
 * - -10^8 <= val <= 10^8
 * - It's guaranteed that val does not exist in the original BST.
 *
 * Example 1:
 * Input: root = [4,2,7,1,3], val = 5
 * Output: [4,2,7,1,3,5]
 *
 * Example 2:
 * Input: root = [40,20,60,10,30,50,70], val = 25
 * Output: [40,20,60,10,30,50,70,null,null,25]
 *
 * Example 3:
 * Input: root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
 * Output: [4,2,7,1,3,5]
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun insertIntoBST(root: TreeNode?, `val`: Int): TreeNode? {
        if (root == null) {
            return TreeNode(`val`)
        }

        var curr = root
        while (true) {
            requireNotNull(curr) { "Current node should not be null" }
            if (`val` < curr.`val`) {
                if (curr.left == null) {
                    curr.left = TreeNode(`val`)
                    return root
                }

                curr = curr.left
            } else {
                if (curr.right == null) {
                    curr.right = TreeNode(`val`)
                    return root
                }

                curr = curr.right
            }
        }
    }

    val root1 = TreeNode(4).apply {
        left = TreeNode(2).apply {
            left = TreeNode(1)
            right = TreeNode(3)
        }
        right = TreeNode(7)
    }
    println(insertIntoBST(root1, 5)?.`val`) // 4

    val root2 = TreeNode(40).apply {
        left = TreeNode(20).apply {
            left = TreeNode(10)
            right = TreeNode(30)
        }
        right = TreeNode(60).apply {
            left = TreeNode(50)
            right = TreeNode(70)
        }
    }
    println(insertIntoBST(root2, 25)?.`val`) // 40

    val root3 = TreeNode(4).apply {
        left = TreeNode(2).apply {
            left = TreeNode(1)
            right = TreeNode(3)
        }
        right = TreeNode(7)
    }
    println(insertIntoBST(root3, 5)?.`val`) // 4
}