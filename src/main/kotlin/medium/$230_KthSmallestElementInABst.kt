package medium

/**
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values
 * of the nodes in the tree.
 *
 * Constraints:
 * - The number of nodes in the tree is n.
 * - 1 <= k <= n <= 10^4
 * - 0 <= Node.val <= 10^4
 *
 * Example 1:
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 *
 * Example 2:
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun kthSmallest(root: TreeNode?, k: Int): Int {
        val stack = mutableListOf<TreeNode>()
        var curr = root
        var count = 0

        while (curr != null || stack.isNotEmpty()) {
            while (curr != null) {
                stack.add(curr)
                curr = curr.left
            }

            curr = stack.removeLast()
            count++
            if (count == k) return curr.`val`
            curr = curr.right
        }

        return -1
    }

    val root1 = TreeNode(3).apply {
        left = TreeNode(1)
        right = TreeNode(4).apply {
            left = TreeNode(2)
        }
    }
    println(kthSmallest(root1, 1)) // 1

    val root2 = TreeNode(5).apply {
        left = TreeNode(3).apply {
            left = TreeNode(2).apply {
                left = TreeNode(1)
            }
            right = TreeNode(4)
        }
        right = TreeNode(6)
    }
    println(kthSmallest(root2, 3)) // 3
}