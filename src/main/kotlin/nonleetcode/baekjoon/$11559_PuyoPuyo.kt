package nonleetcode.baekjoon

fun main() {
    val matrix = Array(12) { readln().toCharArray() }
    val solution = `$11559_PuyoPuyo`()
    println(solution.solve(matrix))
}

class `$11559_PuyoPuyo` {
    data class Point(val x: Int, val y: Int)

    enum class Direction(val x: Int, val y: Int) {
        UP(-1, 0),
        DOWN(1, 0),
        LEFT(0, -1),
        RIGHT(0, 1)
    }

    fun solve(matrix: Array<CharArray>): Int {
        var result = 0
        while (true) {
            val visited = Array(12) { BooleanArray(6) }
            var isPopped = false
            for (i in 0 until 12) {
                for (j in 0 until 6) {
                    if (matrix[i][j] != '.' && !visited[i][j]) {
                        val popped = pop(matrix, visited, i, j)
                        if (popped.size >= 4) {
                            isPopped = true
                            popped.forEach { (x, y) -> matrix[x][y] = '.' }
                        }
                    }
                }
            }
            if (!isPopped) break
            result++
            drop(matrix)
        }
        return result
    }

    fun pop(matrix: Array<CharArray>, visited: Array<BooleanArray>, x: Int, y: Int): List<Point> {
        val color = matrix[x][y]
        val queue = mutableListOf(Point(x, y))
        val popped = mutableListOf(Point(x, y))
        visited[x][y] = true
        while (queue.isNotEmpty()) {
            val (curX, curY) = queue.removeAt(0)
            for (dir in Direction.entries) {
                val nextX = curX + dir.x
                val nextY = curY + dir.y
                if (
                    nextX in 0..< 12 &&
                    nextY in 0 ..< 6 &&
                    matrix[nextX][nextY] == color &&
                    !visited[nextX][nextY]
                ) {
                    queue.add(Point(nextX, nextY))
                    popped.add(Point(nextX, nextY))
                    visited[nextX][nextY] = true
                }
            }
        }
        return popped
    }

    fun drop(matrix: Array<CharArray>) {
        for (i in 0 ..< 6) {
            val queue = ArrayDeque<Char>()
            for (j in 11 downTo 0) {
                if (matrix[j][i] != '.') {
                    queue.add(matrix[j][i])
                }
            }
            for (j in 11 downTo 0) {
                matrix[j][i] = if (queue.isNotEmpty()) queue.removeFirst() else '.'
            }
        }
    }
}