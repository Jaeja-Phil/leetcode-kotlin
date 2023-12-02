package easy

/**
 * Consider all the leaves of a binary tree, from left to right order, the values of those leaves form
 * a leaf value sequence.
 *
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 *
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 *
 * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
 *
 * Constraints:
 * - The number of nodes in each tree will be in the range [1, 200].
 * - Both of the given trees will have values in the range [0, 200].
 *
 * Example 1:
 * Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * Output: true
 *
 * Example 2:
 * Input: root1 = [1,2,3], root2 = [1,3,2]
 * Output: false
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun `Leaf Similar Trees`(head1: TreeNode?, head2: TreeNode?): Boolean {
        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()

        fun dfs(node: TreeNode?, list: MutableList<Int>) {
            if (node == null) return

            if (node.left == null && node.right == null) {
                list.add(node.`val`)
            }

            dfs(node.left, list)
            dfs(node.right, list)
        }

        dfs(head1, list1)
        dfs(head2, list2)

        return list1 == list2
    }

    val head1 = TreeNode(3)
    head1.left = TreeNode(5)
    head1.right = TreeNode(1)
    head1.left?.left = TreeNode(6)
    head1.left?.right = TreeNode(2)
    head1.right?.left = TreeNode(9)
    head1.right?.right = TreeNode(8)
    head1.left?.right?.left = TreeNode(7)
    head1.left?.right?.right = TreeNode(4)
    println(`Leaf Similar Trees`(head1, head1)) // true

    val head2 = TreeNode(1)
    head2.left = TreeNode(2)
    head2.right = TreeNode(3)
    println(`Leaf Similar Trees`(head1, head2)) // false
}