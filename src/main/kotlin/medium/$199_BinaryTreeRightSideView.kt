package medium

/**
 * Given the root of a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 *
 * constraints:
 * - The number of nodes in the tree is in the range [0, 100].
 * - -100 <= Node.val <= 100
 *
 * Example 1:
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 *
 * Example 2:
 * Input: root = [1,null,3]
 * Output: [1,3]
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun rightSideView(root: TreeNode?): List<Int> {
        /**
         * create a list to store the right side view of the tree
         */
        val result = mutableListOf<Int>()

        /**
         * if the root is null, return the empty list
         */
        if (root == null) return result

        /**
         * create a queue which will be storing the nodes of the tree on each level
         */
        val queue = ArrayDeque<TreeNode>()

        /**
         * add the root node (first level) to the queue
         */
        queue.add(root)

        /**
         * while the queue is not empty, iterate through the queue
         */
        while (queue.isNotEmpty()) {
            /**
             * get the size of the current level - this will be used to keep track of the node count on current level
             */
            val currentLevelSize = queue.size

            /**
             * add the last node of the current level to the result list
             */
            val lastNode = queue.last()
            result.add(lastNode.`val`)

            /**
             * remove all the nodes from the queue on the current level and add the nodes on the next level
             */
            repeat(currentLevelSize) {
                val node = queue.removeFirst()
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }
        }

        /**
         * finally return the result list
         */
        return result
    }

    val root1 = TreeNode(1)
    root1.left = TreeNode(2)
    root1.right = TreeNode(3)
    root1.left?.right = TreeNode(5)
    root1.right?.right = TreeNode(4)
    println(rightSideView(root1)) // [1, 3, 4]

    val root2 = TreeNode(1)
    root2.right = TreeNode(3)
    println(rightSideView(root2)) // [1, 3]
}