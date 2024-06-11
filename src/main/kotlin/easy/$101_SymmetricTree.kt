package easy

/**
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 */
fun main() {

}

class `$101_SymmetricTree` {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun isSymmetric(root: TreeNode?): Boolean {
        if (root == null) return true
        if (root.left?.`val` != root.right?.`val`) return false

        val leftQueue = ArrayDeque<TreeNode?>()
        val rightQueue = ArrayDeque<TreeNode?>()

        leftQueue.add(root.left)
        rightQueue.add(root.right)

        while (leftQueue.isNotEmpty() && rightQueue.isNotEmpty()) {
            val left = leftQueue.removeFirst()
            val right = rightQueue.removeFirst()

            if (left == null && right == null) continue
            if (left == null || right == null) return false
            if (left.`val` != right.`val`) return false

            // for the left queue, add left first then right
            leftQueue.add(left.left)
            leftQueue.add(left.right)
            // for the right queue, add right first then left
            rightQueue.add(right.right)
            rightQueue.add(right.left)
        }

        return true
    }
}
