package hard

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to
 * a string and this string can be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily
 * need to follow this format, so please be creative and come up with different approaches yourself.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 10^4].
 * - -1000 <= Node.val <= 1000
 *
 * Example 1:
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 *
 * Example 2:
 * Input: root = []
 * Output: []
 */
fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    class Codec() {
        fun serialize(root: TreeNode?): String {
            if (root == null) return "[]"
            val queue = ArrayDeque<TreeNode?>()
            queue.add(root)
            val result = StringBuilder("[")
            while (queue.isNotEmpty()) {
                val node = queue.removeFirst()
                if (node != null) {
                    result.append(node.`val`).append(",")
                    queue.add(node.left)
                    queue.add(node.right)
                } else {
                    result.append("null,")
                }
            }
            result.deleteCharAt(result.length - 1).append("]")
            return result.toString()
        }

        fun deserialize(data: String): TreeNode? {
            if (data == "[]") return null
            val values = data.substring(1, data.length - 1).split(",")
            val root = TreeNode(values[0].toInt())
            val queue = ArrayDeque<TreeNode?>()
            queue.add(root)
            var i = 1
            while (queue.isNotEmpty() && i < values.size) {
                val node = queue.removeFirst()
                if (values[i] != "null") {
                    node!!.left = TreeNode(values[i].toInt())
                    queue.add(node.left)
                }
                i++
                if (i < values.size && values[i] != "null") {
                    node!!.right = TreeNode(values[i].toInt())
                    queue.add(node.right)
                }
                i++
            }
            return root
        }
    }

    // Example usage:
    val codec = Codec()
    val root = TreeNode(1).apply {
        left = TreeNode(2)
        right = TreeNode(3).apply {
            left = TreeNode(4)
            right = TreeNode(5)
        }
    }
    val serialized = codec.serialize(root)

    println("Serialized: $serialized") // Serialized: [1,2,3,null,null,4,5]

    val deserialized = codec.deserialize(serialized)
    println("Deserialized: ${codec.serialize(deserialized)}") // Deserialized: [1,2,3,null,null,4,5]
}
