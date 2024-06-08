package nonleetcode.baekjoon

import kotlin.math.abs

fun main() {
    val ingredients = mutableListOf<Pair<Long, Long>>()
    repeat(readln().toInt()) {
        val (sourness, bitterness) = readln().split(" ").map { it.toLong() }
        ingredients.add(sourness to bitterness)
    }
    // ex)
    // 4
    // 1 7
    // 2 6
    // 3 8
    // 4 9
    // output: 1
    // explanataion: 2,3,4번째 재료를 사용했을 때, 신맛과 쓴맛의 차이가 가장 작다.
    println(`$2961_DoyoungFoodComb`().solve(ingredients))
}

class `$2961_DoyoungFoodComb` {
    /**
     * 음식의 신맛: 사용한 재료의 신맛의 곱
     * 음식의 쓴맛: 사용한 재료의 쓴맛의 합
     * 신맛과 쓴맛의 차이가 가장 작은 요리를 만들었을 때, 그 차이를 구하라.
     */
    fun solve(ingredients: MutableList<Pair<Long, Long>>): Int {
        val n = ingredients.size
        var minDiff = Long.MAX_VALUE

        fun dfs(idx: Int, currentSour: Long, currentBitter: Long, used: Boolean) {
            // base case, add used to ensure at least one ingredient is used
            if (idx == n) {
                if (used) {
                    minDiff = minOf(minDiff, abs(currentSour - currentBitter))
                }
                return
            }

            // use current ingredient
            dfs(idx + 1, currentSour * ingredients[idx].first, currentBitter + ingredients[idx].second, true)

            // skip current ingredient
            dfs(idx + 1, currentSour, currentBitter, used)
        }

        dfs(0, 1, 0, false)
        return minDiff.toInt()
    }
}