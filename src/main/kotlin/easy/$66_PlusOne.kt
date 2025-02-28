package easy

/**
 * You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the
 * integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer
 * does not contain any leading 0's.
 *
 * Increment the large integer by one and return the resulting array of digits.
 *
 * Constraints:
 * - 1 <= digits.length <= 100
 * - 0 <= digits[i] <= 9
 * - digits does not contain any leading 0's.
 *
 * Example 1:
 * Input: digits = [1,2,3]
 * Output: [1,2,4]
 *
 * Example 2:
 * Input: digits = [4,3,2,1]
 * Output: [4,3,2,2]
 *
 * Example 3:
 * Input: digits = [9]
 * Output: [1,0]
 */
fun main() {
    fun plusOne(digits: IntArray): IntArray {
        var carry = 1
        val answer = IntArray(digits.size)

        for (idx in digits.lastIndex downTo 0) {
            val sum = digits[idx] + carry
            answer[idx] = sum % 10
            carry = sum / 10
        }

        return if (carry == 0) answer else intArrayOf(1) + answer
    }

    println(plusOne(intArrayOf(1, 2, 3)).toList()) // [1, 2, 4]
    println(plusOne(intArrayOf(4, 3, 2, 1)).toList()) // [4, 3, 2, 2]
    println(plusOne(intArrayOf(9)).toList()) // [1, 0]
}