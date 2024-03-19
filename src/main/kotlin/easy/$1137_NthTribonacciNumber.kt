package easy

/**
 * The Tribonacci sequence Tn is defined as follows:
 *
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 *
 * Given n, return the value of Tn.
 *
 * constraints:
 * - 0 <= n <= 37
 * - The answer is guaranteed to fit within a 32-bit integer, ie. the answer is in the range of [-2^31, 2^31 - 1].
 *
 * Example 1:
 * Input: n = 4
 * Output: 4
 * Explanation:
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 */
fun main() {
    fun tribonacci(n: Int): Int {
        // base case
        if (n == 0) return 0
        if (n == 1 || n == 2) return 1

        var t0 = 0
        var t1 = 1
        var t2 = 1
        var t3 = 0

        for (i in 3..n) {
            t3 = t0 + t1 + t2
            t0 = t1
            t1 = t2
            t2 = t3
        }

        return t3

    }

    println(tribonacci(4)) // 4
}
