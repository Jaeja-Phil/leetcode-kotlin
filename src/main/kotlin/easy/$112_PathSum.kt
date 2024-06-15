package easy

/**
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that
 * adding up all the values along the path equals targetSum.
 *
 * A leaf is a node with no children.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 5000].
 * - -1000 <= Node.val <= 1000
 * - -1000 <= targetSum <= 1000
 *
 * Example 1:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * Output: true
 *
 * Example 2:
 * Input: root = [1,2,3], targetSum = 5
 * Output: false
 *
 * Example 3:
 * Input: root = [], targetSum = 0
 * Output: false
 */
fun main() {
    val root1 = `$112_PathSum`.TreeNode(5)
    root1.left = `$112_PathSum`.TreeNode(4)
    root1.right = `$112_PathSum`.TreeNode(8)
    root1.left?.left = `$112_PathSum`.TreeNode(11)
    root1.left?.left?.left = `$112_PathSum`.TreeNode(7)
    root1.left?.left?.right = `$112_PathSum`.TreeNode(2)
    root1.right?.left = `$112_PathSum`.TreeNode(13)
    root1.right?.right = `$112_PathSum`.TreeNode(4)
    root1.right?.right?.right = `$112_PathSum`.TreeNode(1)
    println(`$112_PathSum`().hasPathSum(root1, 22)) // true

    val root2 = `$112_PathSum`.TreeNode(1)
    root2.left = `$112_PathSum`.TreeNode(2)
    root2.right = `$112_PathSum`.TreeNode(3)
    println(`$112_PathSum`().hasPathSum(root2, 5)) // false

    val root3 = `$112_PathSum`.TreeNode(1)
    println(`$112_PathSum`().hasPathSum(root3, 0)) // false
}

class `$112_PathSum` {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
        if (root == null) return false
        if (root.left == null && root.right == null) return root.`val` == targetSum

        return hasPathSum(root.left, targetSum - root.`val`) ||
                hasPathSum(root.right, targetSum - root.`val`)
    }
}
