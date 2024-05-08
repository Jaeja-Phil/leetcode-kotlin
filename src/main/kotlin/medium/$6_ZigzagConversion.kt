package medium

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 * - string convert(string s, int numRows);
 *
 * constraints:
 * - 1 <= s.length <= 1000
 * - s consists of English letters (lower-case and upper-case), ',' and '.'.
 * - 1 <= numRows <= 1000
 *
 * Example 1:
 * - Input: s = "PAYPALISHIRING", numRows = 3
 * - Output: "PAHNAPLSIIGYIR"
 *
 * Example 2:
 * - Input: s = "PAYPALISHIRING", numRows = 4
 * - Output: "PINALSIGYAHRPI"
 *
 * Example 3:
 * - Input: s = "A", numRows = 1
 * - Output: "A"
 *
 */
fun main() {
    fun convert(s: String, numRows: Int): String {
        if (numRows == 1) return s

        val rows = Array(numRows) { StringBuilder() }
        var goingDown = false
        var currentRow = 0

        s.forEach { char ->
            rows[currentRow].append(char)
            if (currentRow == 0 || currentRow == numRows - 1) goingDown = !goingDown
            currentRow += if (goingDown) 1 else -1
        }

        return rows.joinToString("")
    }

    val s1 = "PAYPALISHIRING"
    val numRows1 = 3
    println(convert(s1, numRows1)) // PAHNAPLSIIGYIR

    val s2 = "PAYPALISHIRING"
    val numRows2 = 4
    println(convert(s2, numRows2)) // PINALSIGYAHRPI

    val s3 = "A"
    val numRows3 = 1
    println(convert(s3, numRows3)) // A
}
