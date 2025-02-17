package medium

/**
 * Given a 2-D grid of characters board and a string word, return true if the word is present in the grid, otherwise
 * return false.
 *
 * For the word to be present it must be possible to form it with a path in the board with horizontally or vertically
 * neighboring cells. The same cell may not be used more than once in a word.
 *
 * Constraints:
 * - 1 <= board.length, board[i].length <= 5
 * - 1 <= word.length <= 10
 * - board and word consists of only lowercase and uppercase English letters.
 *
 * Example 1:
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 */
fun main() {
    fun exist(board: Array<CharArray>, word: String): Boolean {
        val rows = board.size
        val cols = board[0].size
        val visited = Array(rows) { BooleanArray(cols) }

        fun dfs(row: Int, col: Int, index: Int): Boolean {
            if (index == word.length) {
                return true
            }

            if (
            // check if current cell is in bounds
                row < 0 || row >= rows || col < 0 || col >= cols ||
                // check if current cell is visited
                visited[row][col] ||
                // check if current cell is not equal to the current character in the word
                board[row][col] != word[index]
            ) {
                return false
            }

            visited[row][col] = true
            val nextIdx = index + 1
            val found = dfs(row + 1, col, nextIdx) || dfs(row - 1, col, nextIdx) ||
                    dfs(row, col + 1, nextIdx) || dfs(row, col - 1, nextIdx)
            visited[row][col] = false

            return found
        }

        for (row in 0 until rows) {
            for (col in 0 until cols) {
                if (dfs(row, col, 0)) {
                    return true
                }
            }
        }

        return false
    }

    val board = arrayOf(
        charArrayOf('A', 'B', 'C', 'E'),
        charArrayOf('S', 'F', 'C', 'S'),
        charArrayOf('A', 'D', 'E', 'E')
    )
    val word = "ABCCED"
    println(exist(board, word)) // true
}