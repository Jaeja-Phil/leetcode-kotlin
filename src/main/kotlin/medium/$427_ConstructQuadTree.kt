package medium

/**
 * Given a n * n matrix grid of 0's and 1's only. We want to represent grid with a Quad-Tree.
 *
 * Return the root of the Quad-Tree representing grid.
 *
 * A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has
 * two attributes:
 * - val: True if the node represents a grid of 1's or False if the node represents a grid of 0's. Notice that you can
 *   assign the val to True or False when isLeaf is False, and both are accepted in the answer.
 * - isLeaf: True if the node is a leaf node on the tree or False if the node has four children.
 *
 * class Node {
 *     public boolean val;
 *     public boolean isLeaf;
 *     public Node topLeft;
 *     public Node topRight;
 *     public Node bottomLeft;
 *     public Node bottomRight;
 * }
 *
 * We can construct a Quad-Tree from a two-dimensional area using the following steps:
 * - If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the
 *   grid and set the four children to Null and stop.
 * - If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid
 *   into four sub-grids as shown in the photo.
 * - Recurse for each of the children with the proper sub-grid.
 *
 * If you want to know more about the Quad-Tree, you can refer to the wiki.
 *
 * Quad-Tree format:
 * You don't need to read this section for solving the problem. This is only if you want to understand the output format
 * here. The output represents the serialized format of a Quad-Tree using level order traversal, where null signifies a
 * path terminator where no node exists below.
 *
 * It is very similar to the serialization of the binary tree. The only difference is that the node is represented as a
 * list [isLeaf, val].
 *
 * If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val] and if the value of isLeaf or
 * val is False we represent it as 0.
 *
 * Constraints:
 * - n == grid.length == grid[i].length
 * - n == 2^x where 0 <= x <= 6
 *
 * Example 1:
 * Input: grid = [[0,1],[1,0]]
 * Output: [[0,1],[1,0],[1,1],[1,1],[1,0]]
 *
 * Example 2:
 * Input: grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],
 *                [1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
 * Output: [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
 */
fun main() {
    class Node(
        var `val`: Boolean,
        var isLeaf: Boolean,
    ) {
        var topLeft: Node? = null
        var topRight: Node? = null
        var bottomLeft: Node? = null
        var bottomRight: Node? = null
    }

    fun construct(grid: Array<IntArray>): Node {
        fun isLeaf(grid: Array<IntArray>, row: Int, col: Int, size: Int): Boolean {
            val value = grid[row][col]
            for (i in row..<row + size) {
                for (j in col..<col + size) {
                    if (grid[i][j] != value) {
                        return false
                    }
                }
            }
            return true
        }

        fun constructNode(grid: Array<IntArray>, row: Int, col: Int, size: Int): Node {
            if (isLeaf(grid, row, col, size)) {
                return Node(grid[row][col] == 1, true)
            }

            val node = Node(false, false)
            val newSize = size / 2
            node.topLeft = constructNode(grid, row, col, newSize)
            node.topRight = constructNode(grid, row, col + newSize, newSize)
            node.bottomLeft = constructNode(grid, row + newSize, col, newSize)
            node.bottomRight = constructNode(grid, row + newSize, col + newSize, newSize)

            return node
        }

        return constructNode(grid, 0, 0, grid.size)
    }

    val grid1 = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0))
    println(construct(grid1)) // [[0,1],[1,0],[1,1],[1,1],[1,0]]

    val grid2 = arrayOf(
        intArrayOf(1, 1, 1, 1, 0, 0, 0, 0),
        intArrayOf(1, 1, 1, 1, 0, 0, 0, 0),
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
        intArrayOf(1, 1, 1, 1, 0, 0, 0, 0),
        intArrayOf(1, 1, 1, 1, 0, 0, 0, 0),
        intArrayOf(1, 1, 1, 1, 0, 0, 0, 0),
        intArrayOf(1, 1, 1, 1, 0, 0, 0, 0),
    )
    println(construct(grid2)) // [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
}