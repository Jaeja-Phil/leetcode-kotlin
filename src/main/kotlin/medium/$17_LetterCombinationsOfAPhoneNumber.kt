package medium

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could
 * represent. Return the answer in any order.
 *
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any
 * letters.
 *
 * constraints:
 * - 0 <= digits.length <= 4
 * - digits[i] is a digit in the range ['2', '9']
 *
 * Example 1:
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * Example 2:
 * Input: digits = ""
 * Output: []
 *
 * Example 3:
 * Input: digits = "2"
 * Output: ["a","b","c"]
 */
fun main() {
    val letterMap = mapOf(
        '2' to "abc",
        '3' to "def",
        '4' to "ghi",
        '5' to "jkl",
        '6' to "mno",
        '7' to "pqrs",
        '8' to "tuv",
        '9' to "wxyz"
    )

    fun letterCombinations(digits: String): List<String> {
        // base case
        if (digits.isEmpty()) return emptyList()

        val result = mutableListOf<String>()
        fun dfs(index: Int, path: StringBuilder) {
            if (index == digits.length) {
                result.add(path.toString())
                return
            }
            val letters = letterMap[digits[index]]!! // constraint is that digits[i] is a digit in the range ['2', '9']
            for (i in letters.indices) {
                path.append(letters[i])
                dfs(index + 1, path)
                path.deleteCharAt(path.lastIndex)
            }
        }
        dfs(0, StringBuilder())
        return result
    }

    val result = letterCombinations("23")
    println(result) // ["ad","ae","af","bd","be","bf","cd","ce","cf"]

    val result2 = letterCombinations("")
    println(result2) // []

    val result3 = letterCombinations("2")
    println(result3) // ["a","b","c"]
}
