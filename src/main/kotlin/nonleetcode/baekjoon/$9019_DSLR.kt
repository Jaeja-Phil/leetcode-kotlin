package nonleetcode.baekjoon

fun main() {
    val cases = readln().toInt()
    repeat(cases) {
        val (from, to) = readln().split(" ").map { it.toInt() }
        // change to string with 4 digits
        val solution = `$9019_DSLR`()
        println(solution.solve(from, to))
    }
}

class `$9019_DSLR` {

    data class Node(val value: Int, val path: String)

    enum class Strategy {
        D, S, L, R
    }
    fun solve(from: Int, to: Int): String {
        val visited = BooleanArray(10000)
        val queue = ArrayDeque<Node>()
        queue.add(Node(from, ""))
        visited[from] = true
        while (queue.isNotEmpty()) {
            val size = queue.size
            repeat(size) {
                val (value, path) = queue.removeFirst()
                if (value == to) return path
                for (strategy in Strategy.entries) {
                    val next = applyStrategy(value, strategy)
                    if (!visited[next]) {
                        visited[next] = true
                        queue.add(Node(next, path + strategy.name))
                    }
                }
            }
        }

        throw IllegalStateException()
    }

    private fun applyStrategy(from: Int, strategy: Strategy): Int {
        when (strategy) {
            Strategy.D -> return (from * 2) % 10000
            Strategy.S -> return if (from == 0) 9999 else from - 1
            Strategy.L -> return (from % 1000) * 10 + from / 1000
            Strategy.R -> return (from % 10) * 1000 + from / 10
            else -> throw IllegalArgumentException()
        }
    }
}
