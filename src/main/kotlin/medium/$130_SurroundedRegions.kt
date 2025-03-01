package medium

/**
 * You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:
 * - Connect: A cell is connected to adjacent cells horizontally or vertically.
 * - Region: To form a region connect every 'O' cell.
 * - Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the
 *   region cells are on the edge of the board.
 *
 * To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to
 * return anything.
 *
 * Constraints:
 * - m == board.length
 * - n == board[i].length
 * - 1 <= m, n <= 200
 * - board[i][j] is 'X' or 'O'.
 *
 * Example 1:
 * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 *
 * Example 2:
 * Input: board = [["X"]]
 * Output: [["X"]]
 *
 * Example 3:
 * Input: board = [["O","O","O"],["O","O","O"],["O","O","O"]]
 * Output: [["O","O","O"],["O","O","O"],["O","O","O"]]
 */
fun main() {
    fun solve(board: Array<CharArray>) {
        val rows = board.size
        val cols = board[0].size

        fun dfs(row: Int, col: Int) {
            if (row < 0 || row >= rows || col < 0 || col >= cols || board[row][col] != 'O') return
            board[row][col] = 'T'
            dfs(row - 1, col)
            dfs(row + 1, col)
            dfs(row, col - 1)
            dfs(row, col + 1)
        }

        for (row in board.indices) {
            dfs(row, 0)
            dfs(row, cols - 1)
        }

        for (col in board[0].indices) {
            dfs(0, col)
            dfs(rows - 1, col)
        }

        for (row in board.indices) {
            for (col in board[0].indices) {
                board[row][col] = when (board[row][col]) {
                    'T' -> 'O'
                    else -> 'X'
                }
            }
        }
    }

    val board1 = arrayOf(
        charArrayOf('X', 'X', 'X', 'X'),
        charArrayOf('X', 'O', 'O', 'X'),
        charArrayOf('X', 'X', 'O', 'X'),
        charArrayOf('X', 'O', 'X', 'X')
    )
    solve(board1)
    println(board1.map { it.toList() }) // [[X, X, X, X], [X, X, X, X], [X, X, X, X], [X, O, X, X]]

    val board2 = arrayOf(charArrayOf('X'))
    solve(board2)
    println(board2.map { it.toList() }) // [[X]]
}