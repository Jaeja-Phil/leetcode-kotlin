package medium

/**
 * According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by
 * the British mathematician John Horton Conway in 1970."
 *
 * The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or
 * dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the
 * following four rules (taken from the above Wikipedia article):
 * - Any live cell with fewer than two live neighbors dies as if caused by under-population.
 * - Any live cell with two or three live neighbors lives on to the next generation.
 * - Any live cell with more than three live neighbors dies, as if by over-population.
 * - Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 *
 * The next state is created by applying the above rules simultaneously to every cell in the current state, where births
 * and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.
 *
 * Constraints:
 * - m == board.length
 * - n == board[i].length
 * - 1 <= m, n <= 25
 * - board[i][j] is 0 or 1.
 *
 * Example 1:
 * Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 */
fun main() {
    class Solution {
        fun gameOfLife(board: Array<IntArray>) {
            for (rowIdx in board.indices) {
                for (colIdx in board[rowIdx].indices) {
                    val count = liveNeighborCount(board, rowIdx, colIdx)
                    if (board[rowIdx][colIdx] == 1) {
                        if (count < 2 || count > 3) board[rowIdx][colIdx] = 2
                    } else {
                        if (count == 3) board[rowIdx][colIdx] = 3
                    }
                }
            }

            for (rowIdx in board.indices) {
                for (colIdx in board[rowIdx].indices) {
                    if (board[rowIdx][colIdx] == 2) board[rowIdx][colIdx] = 0
                    if (board[rowIdx][colIdx] == 3) board[rowIdx][colIdx] = 1
                }
            }
        }

        fun liveNeighborCount(board: Array<IntArray>, i: Int, j: Int): Int {
            var count = 0
            var rowStart = if (i > 0) i - 1 else 0
            var rowEnd = if (i < board.lastIndex) i + 1 else board.lastIndex
            while (rowStart <= rowEnd) {
                var colStart = if (j > 0) j - 1 else 0
                var colEnd = if (j < board[i].lastIndex) j + 1 else board[i].lastIndex
                while (colStart <= colEnd) {
                    if (
                        (rowStart == i && colStart == j).not() &&
                        board[rowStart][colStart] == 1 || board[rowStart][colStart] == 2
                    ) {
                        count++
                    }
                    colStart++
                }
                rowStart++
            }

            return count
        }
    }

    val sol = Solution()
    val board = arrayOf(
        intArrayOf(0, 1, 0),
        intArrayOf(0, 0, 1),
        intArrayOf(1, 1, 1),
        intArrayOf(0, 0, 0)
    )
    sol.gameOfLife(board)
    println(board.contentDeepToString()) // [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
}
