package easy

/**
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of
 * 1's in the binary representation of i.
 *
 * Constraints:
 * - 0 <= n <= 10^5
 *
 * Example 1:
 * Input: n = 2
 * Output: [0,1,1]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 *
 * Example 2:
 * Input: n = 5
 * Output: [0,1,1,2,1,2]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 */
fun main() {
    fun countBits(n: Int): IntArray {
        val answer = IntArray(n + 1)
        var offset = 1

        for (i in 1..n) {
            if (offset * 2 == i) {
                offset *= 2
            }
            answer[i] = answer[i - offset] + 1
        }

        return answer
    }

    println(countBits(2).contentToString()) // [0,1,1]
    println(countBits(5).contentToString()) // [0,1,1,2,1,2]
}