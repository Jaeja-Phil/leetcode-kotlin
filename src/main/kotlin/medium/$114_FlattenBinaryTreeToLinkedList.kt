package medium

/**
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the
 * list and the left child pointer is always null.
 *
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 2000].
 * - -100 <= Node.val <= 100
 *
 * Example 1:
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 *
 * Example 2:
 * Input: root = []
 * Output: []
 *
 * Example 3:
 * Input: root = [0]
 * Output: [0]
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun flatten(root: TreeNode?) {
        val dummy = TreeNode(-1)
        var curr = dummy

        fun dfs(node: TreeNode?) {
            if (node == null) {
                return
            }

            val rightNode = node.right

            curr.right = node
            curr.left = null
            curr = curr.right!!

            node.left?.let { dfs(it) }
            dfs(rightNode)
        }

        dfs(root)
    }

    val root = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(3)
            right = TreeNode(4)
        }
        right = TreeNode(5).apply {
            right = TreeNode(6)
        }
    }
    flatten(root)
    var curr: TreeNode? = root
    while (curr != null) {
        print("${curr?.`val`} -> ")
        curr = curr?.right
    }
    println("null")
    // Output: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null

    val root2: TreeNode? = null
    flatten(root2)
    curr = root2
    while (curr != null) {
        print("${curr?.`val`} -> ")
        curr = curr?.right
    }
    println("null")
    // Output: null

    val root3 = TreeNode(0)
    flatten(root3)
    curr = root3
    while (curr != null) {
        print("${curr?.`val`} -> ")
        curr = curr?.right
    }
    println("null")
    // Output: 0 -> null
}