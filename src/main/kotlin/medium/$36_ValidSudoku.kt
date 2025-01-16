package medium

/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * 1. Each row must contain the digits 1-9 without repetition.
 * 2. Each column must contain the digits 1-9 without repetition.
 * 3. Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * Note:
 * - A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * - Only the filled cells need to be validated according to the mentioned rules.
 *
 * Constraints:
 * - board.length == 9
 * - board[i].length == 9
 * - board[i][j] is a digit or '.'
 *
 * Example 1:
 * Input: board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: true
 *
 * Example 2:
 * Input: board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: true
 */
fun main() {
    class Solution {
        // Solution 1.
//        fun isValidSudoku(board: Array<CharArray>): Boolean {
//            return areRowsValid(board) && areColsValid(board) && areSubBoxesValid(board)
//        }
//
//        fun areRowsValid(board: Array<CharArray>): Boolean {
//            for (row in board) {
//                val set = mutableSetOf<Char>()
//                for (cell in row) if (cell.isDigit() && !set.add(cell)) return false
//            }
//
//            return true
//        }
//
//        fun areColsValid(board: Array<CharArray>): Boolean {
//            for (col in board.indices) {
//                val set = mutableSetOf<Char>()
//                for (row in board.indices) {
//                    val cell = board[row][col]
//                    if (cell.isDigit() && !set.add(cell)) return false
//                }
//            }
//
//            return true
//        }
//
//        fun areSubBoxesValid(board: Array<CharArray>): Boolean {
//            for (subBoxRow in 0..2) {
//                for (subBoxCol in 0..2) {
//                    val set = mutableSetOf<Char>()
//                    for (i in 0..2) {
//                        for (j in 0..2) {
//                            val cell = board[subBoxRow * 3 + i][subBoxCol * 3 + j]
//                            if (cell.isDigit() && !set.add(cell)) return false
//                        }
//                    }
//                }
//            }
//
//            return true
//        }

        // Solution 2.
        fun isValidSudoku(board: Array<CharArray>): Boolean {
            val cols = mutableMapOf<Int, MutableSet<Char>>()
            val rows = mutableMapOf<Int, MutableSet<Char>>()
            val squares = mutableMapOf<Pair<Int, Int>, MutableSet<Char>>()

            for (row in board.indices) {
                for (col in board.indices) {
                    val cell = board[row][col]
                    if (cell == '.') continue

                    if (!rows.getOrPut(row) { mutableSetOf() }.add(cell) ||
                        !cols.getOrPut(col) { mutableSetOf() }.add(cell) ||
                        !squares.getOrPut(Pair(row / 3, col / 3)) { mutableSetOf() }.add(cell)
                    ) {
                        return false
                    }
                }
            }

            return true
        }
    }


    val board1 = arrayOf(
        charArrayOf('5','3','.','.','7','.','.','.','.'),
        charArrayOf('6','.','.','1','9','5','.','.','.'),
        charArrayOf('.','9','8','.','.','.','.','6','.'),
        charArrayOf('8','.','.','.','6','.','.','.','3'),
        charArrayOf('4','.','.','8','.','3','.','.','1'),
        charArrayOf('7','.','.','.','2','.','.','.','6'),
        charArrayOf('.','6','.','.','.','.','2','8','.'),
        charArrayOf('.','.','.','4','1','9','.','.','5'),
        charArrayOf('.','.','.','.','8','.','.','7','9')
    )
    println(Solution().isValidSudoku(board1)) // true

    val board2 = arrayOf(
        charArrayOf('8','3','.','.','7','.','.','.','.'),
        charArrayOf('6','.','.','1','9','5','.','.','.'),
        charArrayOf('.','9','8','.','.','.','.','6','.'),
        charArrayOf('8','.','.','.','6','.','.','.','3'),
        charArrayOf('4','.','.','8','.','3','.','.','1'),
        charArrayOf('7','.','.','.','2','.','.','.','6'),
        charArrayOf('.','6','.','.','.','.','2','8','.'),
        charArrayOf('.','.','.','4','1','9','.','.','5'),
        charArrayOf('.','.','.','.','8','.','.','7','9')
    )
    println(Solution().isValidSudoku(board2)) // false
}