package easy

/**
 * Write an algorithm to determine if a number n is happy.
 * A happy number is a number defined by the following process:
 * - Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * - Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not
 *   include 1.
 * - Those numbers for which this process ends in 1 are happy.
 *
 * Return true if n is a happy number, and false if not.
 *
 * Constraints:
 * - 1 <= n <= 2^31 - 1
 *
 * Example 1:
 * Input: n = 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */
fun main() {
    fun isHappy(n: Int): Boolean {
        val evaluated = mutableSetOf<Int>()
        var num = n
        while (num != 1) {
            if (evaluated.add(num).not()) {
                return false
            }
            var sum = 0
            while (num > 0) {
                sum += (num % 10) * (num % 10)
                num /= 10
            }
            num = sum
        }

        return true
    }

    println(isHappy(19)) // true
}
