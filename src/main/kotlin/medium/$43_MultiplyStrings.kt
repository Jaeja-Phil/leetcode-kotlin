package medium

/**
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also
 * represented as a string.
 *
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 * Constraints:
 * - 1 <= num1.length, num2.length <= 200
 * - num1 and num2 consist of digits only.
 * - Both numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Explanation: 2 * 3 = 6
 *
 * Example 2:
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * Explanation: 123 * 456 = 56088
 */
fun main() {
    fun multiply(num1: String, num2: String): String {
        val n1Len = num1.length
        val n2Len = num2.length
        val resultDigits = IntArray(n1Len + n2Len) { 0 }

        for (i in n1Len - 1 downTo 0) {
            for (j in n2Len - 1 downTo 0) {
                val curN1 = num1[i] - '0'
                val curN2 = num2[j] - '0'
                val multiplied = curN1 * curN2
                val p1 = i + j
                val p2 = i + j + 1
                val sum = multiplied + resultDigits[p2]
                resultDigits[p1] += sum / 10
                resultDigits[p2] = sum % 10
            }
        }

        val sb = StringBuilder()
        for (digit in resultDigits) {
            if (sb.isEmpty() && digit == 0) continue
            sb.append(digit)
        }

        return if (sb.isEmpty()) "0" else sb.toString()
    }

    println(multiply("2", "3")) // Output: "6"
    println(multiply("123", "456")) // Output: "56088"
}