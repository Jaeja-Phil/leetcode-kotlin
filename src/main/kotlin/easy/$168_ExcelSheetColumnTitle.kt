package easy

/**
 * Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.
 *
 * Constraints:
 * - 1 <= columnNumber <= 2^31 - 1
 *
 * Example 1:
 * Input: columnNumber = 1
 * Output: "A"
 *
 * Example 2:
 * Input: columnNumber = 28
 * Output: "AB"
 *
 * Example 3:
 * Input: columnNumber = 701
 */
fun main() {
    fun convertToTitle(columnNumber: Int): String {
        val result = StringBuilder()
        var num = columnNumber

        while (num > 0) {
            num--
            val rem = num % 26
            result.append('A' + rem)
            num /= 26
        }

        return result.reverse().toString()
    }

    println(convertToTitle(1)) // Output: "A"
    println(convertToTitle(28)) // Output: "AB"
    println(convertToTitle(701)) // Output: "ZY"
}

