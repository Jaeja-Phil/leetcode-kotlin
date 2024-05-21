package nonleetcode.baekjoon

fun main() {
    val casesCount = readln().toInt()
    val solution = `유기농배추`()
    repeat(casesCount) {
        val (m, n, k) = readln().split(" ").map { it.toInt() }
        val land = Array(m) { IntArray(n) }
        repeat(k) {
            val (x, y) = readln().split(" ").map { it.toInt() }
            land[x][y] = 1
        }
        println(solution.solve(land))
    }
}

class `유기농배추` {
    fun solve(land: Array<IntArray>): Int {
        val visited = Array(land.size) { BooleanArray(land[0].size) }
        var count = 0
        for (i in land.indices) {
            for (j in land[0].indices) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    dfs(land, i, j, visited)
                    count++
                }
            }
        }
        return count
    }

    fun dfs(land: Array<IntArray>, x: Int, y: Int, visited: Array<BooleanArray>) {
        if (x < 0 || x >= land.size || y < 0 || y >= land[0].size) return
        if (land[x][y] == 0) return
        land[x][y] = 0
        dfs(land, x + 1, y, visited)
        dfs(land, x - 1, y, visited)
        dfs(land, x, y + 1, visited)
        dfs(land, x, y - 1, visited)
    }
}