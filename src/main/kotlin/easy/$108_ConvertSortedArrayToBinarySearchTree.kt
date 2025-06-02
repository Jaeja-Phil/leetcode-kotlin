package easy

/**
 * Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary
 * search tree.
 *
 * Constraints:
 * - 1 <= nums.length <= 10^4
 * - -10^4 <= nums[i] <= 10^4
 * - nums is sorted in a strictly increasing order.
 *
 * Example 1:
 * Input: nums = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 *
 * Example 2:
 * Input: nums = [1,3]
 * Output: [3,1]
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        if (nums.isEmpty()) return null

        fun buildTree(left: Int, right: Int): TreeNode? {
            if (left > right) return null

            val mid = left + (right - left) / 2
            val node = TreeNode(nums[mid])
            node.left = buildTree(left, mid - 1)
            node.right = buildTree(mid + 1, right)
            return node
        }

        return buildTree(0, nums.size - 1)
    }

    fun printTree(tree: TreeNode) {
        val queue = ArrayDeque<TreeNode?>()
        queue.add(tree)

        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val node = queue.removeFirst()
                if (node != null) {
                    print("${node.`val`} ")
                    queue.add(node.left)
                    queue.add(node.right)
                } else {
                    print("null ")
                }
            }

            println()
        }

        println()
    }

    val nums1 = intArrayOf(-10, -3, 0, 5, 9)
    val res1 = sortedArrayToBST(nums1)
    println("Tree for nums1:")
    printTree(res1!!)
}