package easy

/**
 * Given a positive integer num, return true if num is a perfect square or false otherwise.
 *
 * A perfect square is an integer that is the square of an integer. In other words, it is the product of some integer
 * with itself.
 *
 * You must not use any built-in library function, such as sqrt.
 *
 * constraints:
 * - 1 <= num <= 2^31 - 1
 *
 * Example 1:
 * Input: num = 16
 * Output: true
 * Explanation: 4 * 4 = 16
 *
 * Example 2:
 * Input: num = 14
 * Output: false
 */
fun main() {
    fun isPerfectSquare(num: Int): Boolean {
        var left = 1L
        var right = num.toLong()
        val target = num.toLong()
        while (left <= right) {
            val mid = left + (right - left) / 2
            val square = mid * mid
            when {
                square == target -> return true
                square < target -> left = mid + 1
                else -> right = mid - 1
            }
        }

        return false
    }

    println(isPerfectSquare(16)) // true
    println(isPerfectSquare(14)) // false
}