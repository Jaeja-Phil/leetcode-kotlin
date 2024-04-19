package easy

/**
 * Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer
 * should be non-negative as well.
 *
 * You must not use any built-in exponent function or operator.
 *
 * For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
 *
 * constraints:
 * - 0 <= x <= 2^31 - 1
 *
 * Example 1:
 * Input: x = 4
 * Output: 2
 * Explanation: The square root of 4 is 2.
 *
 * Example 2:
 * Input: x = 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.
 */
fun main() {
    fun mySqrt(x: Int): Int {
        // base case
        if (x == 0) return 0
        if (x <= 2) return 1

        val target = x.toLong()
        var left = 1L
        var right = x.toLong() / 2 // divide by 2 because the square root of x is guaranteed to be less than or equal to x / 2
        while (left <= right) {
            val mid = left + (right - left) / 2
            val currentSquare = mid * mid
            when {
                currentSquare == target -> return mid.toInt()
                currentSquare < target -> left = mid + 1
                else -> right = mid - 1
            }
        }

        return right.toInt()
    }

    println(mySqrt(4)) // 2
    println(mySqrt(8)) // 2
}