package medium

/**
 * You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+').
 * You are also given the entrance of the maze, where entrance = [entranceRow, entranceCol] denotes the row and column
 * of the cell you are initially standing at.
 *
 * In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot
 * step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell
 * that is at the border of the maze. The entrance does not count as an exit.
 *
 * Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.
 *
 * constraints:
 * - maze.length == m
 * - maze[i].length == n
 * - 1 <= m, n <= 100
 * - maze[i][j] is either '.' or '+'.
 * - entrance.length == 2
 * - 0 <= entranceRow < m
 * - 0 <= entranceCol < n
 * - entrance will always be an empty cell.
 *
 * Example 1:
 * Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
 * Output: 1
 * Explanation:
 *   + + . +
 *   . . X +
 *   + + + .
 *
 * Example 2:
 * Input: maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
 * Output: 2
 * Explanation:
 *   + + +
 *   X . .
 *   + + +
 *
 * Example 3:
 * Input: maze = [[".","+"]], entrance = [0,0]
 * Output: -1
 * Explanation:
 *   X +
 */

fun main() {
    fun nearestExit(maze: Array<CharArray>, entrance: IntArray): Int {
        /**
         * store two variables for the row and column size of the maze
         */
        val row = maze.size
        val col = maze[0].size

        /**
         * create a set to keep track of visited cells and add the entrance to it
         */
        val visited = mutableSetOf<Pair<Int, Int>>()
        visited.add(Pair(entrance[0], entrance[1]))

        /**
         * create a queue to keep track of the cells to visit
         */
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(Pair(entrance[0], entrance[1]))

        /**
         * create a directions array to keep track of the directions to move
         */
        val directions = arrayOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0))

        /**
         * create a count variable to keep track of the number of steps
         */
        var count =  0

        /**
         * iterate through the queue until it is empty
         */
        while (queue.isNotEmpty()) {
            count++
            /**
             * consume the queue by the size of the queue
             */
            val size = queue.size
            repeat(size) {
                val current = queue.removeFirst()
                /**
                 * for each directions
                 * 1. check if the next cell is within the maze
                 * 2. check if the next cell is not a wall
                 * 3. check if the next cell is not visited
                 *
                 * if all the conditions are met, check if the next cell is an exit, if so, return the count
                 * otherwise, add the next cell to the visited set and queue
                 */
                directions.forEach { direction ->
                    val nextRow = current.first + direction.first
                    val nextCol = current.second + direction.second
                    if (nextRow in 0 ..< row &&
                        nextCol in 0 ..< col &&
                        maze[nextRow][nextCol] == '.' &&
                        !visited.contains(Pair(nextRow, nextCol))
                    ) {
                        if (nextRow == 0 || nextRow == row - 1 || nextCol == 0 || nextCol == col - 1) {
                            return count
                        }
                        visited.add(Pair(nextRow, nextCol))
                        queue.add(Pair(nextRow, nextCol))
                    }
                }
            }
        }

        /**
         * if the queue is empty and the exit is not found, return -1
         */
        return -1
    }


    val maze = arrayOf(
        charArrayOf('+', '+', '.', '+'),
        charArrayOf('.', '.', '.', '+'),
        charArrayOf('+', '+', '+', '.')
    )
    val entrance = intArrayOf(1, 2)
    println(nearestExit(maze, entrance)) // 1

    val maze2 = arrayOf(
        charArrayOf('+', '+', '+'),
        charArrayOf('.', '.', '.'),
        charArrayOf('+', '+', '+')
    )
    val entrance2 = intArrayOf(1, 0)
    println(nearestExit(maze2, entrance2)) // 2

    val maze3 = arrayOf(
        charArrayOf('.', '+')
    )
    val entrance3 = intArrayOf(0, 0)
    println(nearestExit(maze3, entrance3)) // -1

    val maze4 = arrayOf(
        charArrayOf('+', '.', '+', '+', '+', '+', '+'),
        charArrayOf('+', '.', '+', '.', '.', '.', '+'),
        charArrayOf('+', '.', '+', '.', '+', '.', '+'),
        charArrayOf('+', '.', '.', '.', '+', '.', '+'),
        charArrayOf('+', '+', '+', '+', '+', '.', '+')
    )
    val entrance4 = intArrayOf(0, 1)
    println(nearestExit(maze4, entrance4)) // 12
}