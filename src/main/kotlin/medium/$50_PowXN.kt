package medium

/**
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 *
 * Constraints:
 * - -100.0 < x < 100.0
 * - -2^31 <= n <= 2^31-1
 * - n is an integer
 * - Either x is not zero or n > 0
 * - -10^4 <= x^n <= 10^4
 *
 * Example 1:
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 *
 * Example 2:
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 *
 * Example 3:
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2^-2 = 1/(2^2) = 1/4 = 0.25
 */
fun main() {
    fun myPow(x: Double, n: Int): Double {
        if (n == 0) return 1.0

        var targetX = x
        var targetN = n.toLong()
        if (n < 0) {
            targetX = 1 / x
            targetN = -n.toLong()
        }

        return if (targetN % 2 == 0L) {
            myPow(targetX * targetX, (targetN / 2L).toInt())
        } else {
            targetX * myPow(targetX * targetX, (targetN / 2).toInt())
        }
    }

    println(myPow(2.00000, 10)) // 1024.00000
    println(myPow(2.10000, 3)) // 9.26100
    println(myPow(2.00000, -2)) // 0.25000
}
