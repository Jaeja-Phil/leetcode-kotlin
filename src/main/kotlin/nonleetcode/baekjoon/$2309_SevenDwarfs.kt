package nonleetcode.baekjoon

fun main() {
    val heights = IntArray(9) { readln().toInt() }
    val sol = `$2309_SevenDwarfs`()
    sol.solve(heights).forEach(::println)
}

class `$2309_SevenDwarfs`() {
    fun solve(heights: IntArray): IntArray {
        val sum = heights.sum()
        for (i in 0..<9) {
            for (j in i + 1..<9) {
                if (sum - heights[i] - heights[j] == 100) {
                    return heights.filterIndexed { index, _ -> index != i && index != j }
                        .sorted()
                        .toIntArray()
                }
            }
        }
        return intArrayOf()
    }
}
