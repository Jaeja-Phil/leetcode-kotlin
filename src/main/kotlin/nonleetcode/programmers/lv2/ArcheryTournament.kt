package nonleetcode.programmers.lv2

fun main() {
    val n = 5
    val info = intArrayOf(2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0)
    println(ArcheryTournament().solution(n, info).contentToString()) // [0,2,2,0,1,0,0,0,0,0,0]
    println(ArcheryTournament().solution(n, info).contentEquals(intArrayOf(0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0)))

    val n2 = 1
    val info2 = intArrayOf(1,0,0,0,0,0,0,0,0,0,0)
    println(ArcheryTournament().solution(n2, info2).contentToString()) // [-1]
    println(ArcheryTournament().solution(n2, info2).contentEquals(intArrayOf(-1)))

    val n3 = 9
    val info3 = intArrayOf(0,0,1,2,0,1,1,1,1,1,1)
    println(ArcheryTournament().solution(n3, info3).contentToString()) // [1,1,2,0,1,2,2,0,0,0,0]
    println(ArcheryTournament().solution(n3, info3).contentEquals(intArrayOf(1, 1, 2, 0, 1, 2, 2, 0, 0, 0, 0)))

    val n4 = 10
    val info4 = intArrayOf(0,0,0,0,0,0,0,0,3,4,3)
    println(ArcheryTournament().solution(n4, info4).contentToString()) // [1,1,1,1,1,1,1,1,0,0,2]
    println(ArcheryTournament().solution(n4, info4).contentEquals(intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2)))
}

class ArcheryTournament {
    var maxDiff = Int.MIN_VALUE
    lateinit var bestShot: IntArray

    fun solution(n: Int, info: IntArray): IntArray {
        bestShot = IntArray(11)
        val currentShot = IntArray(11)
        backtrack(n, 0, currentShot, info)

        return if (maxDiff <= 0) intArrayOf(-1) else bestShot
    }

    private fun backtrack(remainingArrows: Int, index: Int, currentShot: IntArray, info: IntArray) {
        if (index == 11 || remainingArrows == 0) {
            if (remainingArrows > 0) {
                currentShot[10] += remainingArrows
            }
            val diff = calculateScoreDifference(currentShot, info)
            if (diff > maxDiff || (diff == maxDiff && isBetterShot(currentShot, bestShot))) {
                maxDiff = diff
                bestShot = currentShot.copyOf()
            }
            if (remainingArrows > 0) {
                currentShot[10] -= remainingArrows
            }
            return
        }

        if (remainingArrows > info[index]) {
            currentShot[index] = info[index] + 1
            backtrack(remainingArrows - currentShot[index], index + 1, currentShot, info)
            currentShot[index] = 0
        }

        backtrack(remainingArrows, index + 1, currentShot, info)
    }

    private fun calculateScoreDifference(ryanShot: IntArray, apeachShot: IntArray): Int {
        var ryanScore = 0
        var apeachScore = 0
        for (i in 0..10) {
            if (ryanShot[i] > apeachShot[i]) {
                ryanScore += 10 - i
            } else if (apeachShot[i] > 0) {
                apeachScore += 10 - i
            }
        }
        return ryanScore - apeachScore
    }

    private fun isBetterShot(candidate: IntArray, currentBest: IntArray): Boolean {
        for (i in 10 downTo 0) {
            if (candidate[i] > currentBest[i]) return true
            if (candidate[i] < currentBest[i]) return false
        }
        return false
    }
}
