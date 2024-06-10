package nonleetcode.baekjoon

import kotlin.math.abs

fun main() {
    val n = readln().toInt()
    val arr = Array(n) { readln().split(" ").map { it.toInt() } }
    println(`$14889_StartAndLink`().solve(arr))
}

class `$14889_StartAndLink` {
    fun solve(arr: Array<List<Int>>): Int {
        val n = arr.size
        var minDiff = Int.MAX_VALUE

        fun dfs(playerIdx: Int, startMembers: MutableList<Int>, linkMembers: MutableList<Int>) {
            // base case
            if (playerIdx == n) {
                val startPower = calculatePower(arr, startMembers)
                val linkPower = calculatePower(arr, linkMembers)
                minDiff = minOf(minDiff, abs(startPower - linkPower))
                return
            }

            if (startMembers.size < n / 2) {
                startMembers.add(playerIdx)
                dfs(playerIdx + 1, startMembers, linkMembers)
                startMembers.remove(playerIdx)
            }

            if (linkMembers.size < n / 2) {
                linkMembers.add(playerIdx)
                dfs(playerIdx + 1, startMembers, linkMembers)
                linkMembers.remove(playerIdx)
            }
        }

        dfs(0, mutableListOf(), mutableListOf())
        return minDiff
    }

    private fun calculatePower(arr: Array<List<Int>>, members: List<Int>): Int {
        var power = 0
        for (i in 0..<members.lastIndex) {
            for (j in i + 1..members.lastIndex) {
                power += arr[members[i]][members[j]] + arr[members[j]][members[i]]
            }
        }
        return power
    }
}
