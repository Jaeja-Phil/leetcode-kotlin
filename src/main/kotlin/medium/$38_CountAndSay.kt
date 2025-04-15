package medium

/**
 * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
 * - countAndSay(1) = "1"
 * - countAndSay(n) is the run-length encoding of countAndSay(n - 1).
 *
 * Run-length encoding (RLE) is a string compression method that works by replacing consecutive identical characters
 * (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters
 * (length of the run). For example, to compress the string "3322251" we replace "33" with "23", replace "222" with
 * "32", replace "5" with "15" and replace "1" with "11". Thus the compressed string becomes "23321511".
 *
 * Given a positive integer n, return the nth element of the count-and-say sequence.
 *
 * Constraints:
 * - 1 <= n <= 30
 *
 * Example 1:
 * Input: n = 1
 * Output: "1"
 * Explanation: This is the base case.
 *
 * Example 2:
 * Input: n = 4
 * Output: "1211"
 * Explanation:
 * countAndSay(1) = "1"
 * countAndSay(2) = RLE of "1" = "11"
 * countAndSay(3) = RLE of "11" = "21"
 * countAndSay(4) = RLE of "21" = "1211"
 */
fun main() {
    fun countAndSay(n: Int): String {
        if (n == 1) return "1"

        var previous = countAndSay(n - 1)
        val result = StringBuilder()
        var count = 1

        for (i in 1..<previous.length) {
            if (previous[i] == previous[i - 1]) {
                count++
            } else {
                result.append(count).append(previous[i - 1])
                count = 1
            }
        }
        result.append(count).append(previous.last())

        return result.toString()
    }

    println(countAndSay(1)) // Output: "1"
    println(countAndSay(4)) // Output: "1211"
}