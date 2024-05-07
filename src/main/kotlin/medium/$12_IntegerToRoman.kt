package medium

import java.lang.StringBuilder
import kotlin.math.pow

/**
 * Seven different symbols represent Roman numerals with the following values:
 *
 * Symbol	Value
 * I	1
 * V	5
 * X	10
 * L	50
 * C	100
 * D	500
 * M	1000
 * Roman numerals are formed by appending the conversions of decimal place values from highest to lowest.
 * Converting a decimal place value into a Roman numeral has the following rules:
 * - If the value does not start with 4 or 9, select the symbol of the maximal value that can be subtracted from the
 *   input, append that symbol to the result, subtract its value, and convert the remainder to a Roman numeral.
 * - If the value starts with 4 or 9 use the subtractive form representing one symbol subtracted from the following
 *   symbol, for example, 4 is 1 (I) less than 5 (V): IV and 9 is 1 (I) less than 10 (X): IX. Only the following
 *   subtractive forms are used: 4 (IV), 9 (IX), 40 (XL), 90 (XC), 400 (CD) and 900 (CM).
 * - Only powers of 10 (I, X, C, M) can be appended consecutively at most 3 times to represent multiples of 10.
 *   You cannot append 5 (V), 50 (L), or 500 (D) multiple times. If you need to append a symbol 4 times use the
 *   subtractive form.
 *
 * Given an integer, convert it to a Roman numeral.
 *
 * constraints:
 * - 1 <= num <= 3999
 *
 * Example 1:
 * Input: num = 3749
 * Output: "MMMDCCXLIX"
 * Explanation:
 * 3000 = MMM as 1000 (M) + 1000 (M) + 1000 (M)
 *  700 = DCC as 500 (D) + 100 (C) + 100 (C)
 *   40 = XL as 10 (X) less of 50 (L)
 *    9 = IX as 1 (I) less of 10 (X)
 * Note: 49 is not 1 (I) less of 50 (L) because the conversion is based on decimal places
 *
 * Example 2:
 * Input: num = 58
 * Output: "LVIII"
 * Explanation:
 * 50 = L as 50 (L)
 * 8 = VIII as 5 (V) + 1 (I) + 1 (I) + 1 (I)
 *
 * Example 3:
 * Input: num = 1994
 * Output: "MCMXCIV"
 * Explanation:
 * 1000 = M as 1000 (M)
 * 900 = CM as 100 (C) less of 1000 (M)
 * 90 = XC as 10 (X) less of 100 (C)
 * 4 = IV as 1 (I) less of 5 (V)
 */
fun main() {
    class Solution {
        private val romanIntToSymbols = listOf(
            1000 to "M",
            900 to "CM",
            500 to "D",
            400 to "CD",
            100 to "C",
            90 to "XC",
            50 to "L",
            40 to "XL",
            10 to "X",
            9 to "IX",
            5 to "V",
            4 to "IV",
            1 to "I",
        )

        fun intToRoman(num: Int): String {
            val sb = StringBuilder()
            var n = num

            romanIntToSymbols.forEach { (value, symbol) ->
                while (n >= value) {
                    sb.append(symbol)
                    n -= value
                }
            }

            return sb.toString()
        }
    }

    val tests = listOf(
        3749 to "MMMDCCXLIX",
        58 to "LVIII",
        1994 to "MCMXCIV"
    )
    val s = Solution()
    tests.forEach {
        println("Input: ${it.first}")
        println("Output: ${s.intToRoman(it.first)}")
        println("Expected: ${it.second}")
        println()
    }
}
