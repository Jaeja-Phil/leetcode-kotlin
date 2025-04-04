package medium

/**
 * Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of
 * unique values from 1 to n. Return the answer in any order.
 *
 * Constraints:
 * - 1 <= n <= 8
 *
 * Example 1:
 * Input: n = 3
 * Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null

        override fun toString(): String {
            return "`val` = $`val`, left = $left, right = $right"
        }
    }

    fun generateTrees(n: Int): List<TreeNode?> {
        fun generate(left: Int, right: Int): List<TreeNode> {
            if (left > right) {
                return listOf(TreeNode(0)) // Dummy node to represent null
            }
            val result = mutableListOf<TreeNode>()
            for (i in left..right) {
                val leftTrees = generate(left, i - 1)
                val rightTrees = generate(i + 1, right)
                for (l in leftTrees) {
                    for (r in rightTrees) {
                        val root = TreeNode(i)
                        root.left = if (l.`val` == 0) null else l
                        root.right = if (r.`val` == 0) null else r
                        result.add(root)
                    }
                }
            }
            return result
        }

        return if (n == 0) {
            emptyList()
        } else {
            generate(1, n)
        }
    }

    fun printTrees(trees: List<TreeNode?>) {
        for (tree in trees) {
            println(tree)
        }
    }

    val n = 3
    val result = generateTrees(n)
    printTrees(result)
}
