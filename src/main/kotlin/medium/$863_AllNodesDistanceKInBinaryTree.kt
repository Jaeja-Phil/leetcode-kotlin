package medium

/**
 * Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values
 * of all nodes that have a distance k from the target node.
 *
 * You can return the answer in any order.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 500].
 * - 0 <= Node.val <= 500
 * - All the values in the tree are unique.
 * - target is the value of one of the nodes in the tree.
 * - 0 <= k <= 1000
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
 * Output: [7,4,1]
 * Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
 *
 * Example 2:
 * Input: root = [1], target = 1, k = 3
 * Output: []
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun distanceK(root: TreeNode?, target: TreeNode?, k: Int): List<Int> {
        val result = mutableListOf<Int>()
        val parentMap = mutableMapOf<TreeNode, TreeNode?>()
        val visited = mutableSetOf<TreeNode>()

        fun dfs(node: TreeNode?, parent: TreeNode?) {
            if (node == null) return
            parentMap[node] = parent
            dfs(node.left, node)
            dfs(node.right, node)
        }

        dfs(root, null)

        fun bfs(node: TreeNode?, distance: Int) {
            if (node == null || visited.contains(node)) return
            visited.add(node)
            if (distance == 0) {
                result.add(node.`val`)
                return
            }
            bfs(node.left, distance - 1)
            bfs(node.right, distance - 1)
            bfs(parentMap[node], distance - 1)
        }

        bfs(target, k)
        return result
    }

    val root = TreeNode(3).apply {
        left = TreeNode(5).apply {
            left = TreeNode(6)
            right = TreeNode(2).apply {
                left = TreeNode(7)
                right = TreeNode(4)
            }
        }
        right = TreeNode(1).apply {
            left = TreeNode(0)
            right = TreeNode(8)
        }
    }

    val target = root.left // Node with value 5
    val k = 2

    val result = distanceK(root, target, k)
    println(result) // Output: [7, 4, 1]

    val root2 = TreeNode(1)
    val target2 = root2 // Node with value 1
    val k2 = 3
    val result2 = distanceK(root2, target2, k2)
    println(result2) // Output: []
}
