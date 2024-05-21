package nonleetcode.baekjoon

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val res = `숨바꼭질2`().solve(n, k)
    println(res.first)
    println(res.second)
}

class `숨바꼭질2` {
    fun solve(n: Int, k: Int): Pair<Int, Int> {
        if (n == k) return Pair(0, 1)

        val queue = ArrayDeque<Int>()
        val dp = IntArray(100001) { -1 }
        queue.add(n)
        dp[n] = 0
        var time = 0
        while (queue.isNotEmpty()) {
            val size = queue.size
            var found = false
            var count = 0
            for (i in 0..<size) {
                val current = queue.removeFirst()
                if (current == k) {
                    found = true
                    count++
                    continue
                }
                if (current - 1 in 0..100000 &&
                    (dp[current - 1] == -1 || dp[current - 1] >= time + 1)) {
                    queue.add(current - 1)
                    dp[current - 1] = time + 1
                }
                if (current + 1 in 0..100000 &&
                    (dp[current + 1] == -1 || dp[current + 1] >= time + 1)) {
                    queue.add(current + 1)
                    dp[current + 1] = time + 1
                }
                if (current * 2 in 0..100000 &&
                    (dp[current * 2] == -1 || dp[current * 2] >= time + 1)) {
                    queue.add(current * 2)
                    dp[current * 2] = time + 1
                }
            }
            if (found) {
                return Pair(time, count)
            }
            time++
        }

        return Pair(-1, 0)
    }
}