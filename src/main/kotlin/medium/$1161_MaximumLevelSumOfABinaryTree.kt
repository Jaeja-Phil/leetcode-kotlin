package medium

/**
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 *
 * Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
 *
 * constraints:
 * - The number of nodes in the tree is in the range [1, 10^4].
 * - -10^5 <= Node.val <= 10^5
 *
 * Example 1:
 * Input: root = [1,7,0,7,-8,null,null]
 * Output: 2
 *
 * Example 2:
 * Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
 * Output: 2
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun maxLevelSum(root: TreeNode?): Int {
        /**
         * base case:
         * - if root is null, return 0
         * - if root has no children, the max level sum is the root value
         */
        if (root == null) return 0
        if (root.left == null && root.right == null) return root.`val`

        /**
         * create a queue to store the nodes
         */
        val queue = ArrayDeque<TreeNode>()

        /**
         * add the root to the queue to start the BFS
         */
        queue.add(root)

        /**
         * Need to keep track of...
         * - maxLevelSum: the max sum of a level so far
         * - maxLevel: the level where the max sum is found
         * - currentLevel: the current level we are on
         */
        var maxLevelSum = Int.MIN_VALUE
        var maxLevel = 0
        var currentLevel = 0

        /**
         * start doing bfs
         */
        while (queue.isNotEmpty()) {
            /**
             * increment the current level (since currentLevel is initialized to 0, it will be 1 on the first iteration)
             */
            currentLevel++

            /**
             * keep track of the number of nodes on current level to know when to stop the dequeue process
             */
            val size = queue.size

            /**
             * initialize the level sum to 0
             */
            var levelSum = 0

            /**
             * start dequeueing nodes on the current level and
             * - get the sum of the nodes on the current level
             * - add the children of the nodes to the queue
             */
            repeat(size) {
                val node = queue.removeFirst()
                levelSum += node.`val`
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }

            /**
             * if the level sum is greater than the max level sum, update the max level sum and max level
             */
            if (levelSum > maxLevelSum) {
                maxLevelSum = levelSum
                maxLevel = currentLevel
            }
        }

        /**
         * return the max level
         */
        return maxLevel
    }

    val root = TreeNode(1)
    root.left = TreeNode(7)
    root.right = TreeNode(0)
    root.left?.left = TreeNode(7)
    root.left?.right = TreeNode(-8)
    println(maxLevelSum(root)) // 2

    val root2 = TreeNode(989)
    root2.right = TreeNode(10250)
    root2.right?.left = TreeNode(98693)
    root2.right?.right = TreeNode(-89388)
    root2.right?.left?.left = TreeNode(-32127)
    println(maxLevelSum(root2)) // 2
}