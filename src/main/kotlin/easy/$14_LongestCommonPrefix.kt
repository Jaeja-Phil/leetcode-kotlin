package easy

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 *
 * constraints:
 * - 1 <= strs.length <= 200
 * - 0 <= strs[i].length <= 200
 * - strs[i] consists of only lower-case English letters.
 *
 * Example 1:
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 *
 * Example 2:
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 */
fun main() {
    fun longestCommonPrefix(strs: Array<String>): String {
        // Solution 1.
//        // find the min length string
//        val minLength = strs.minOf { it.length }
//
//        // iterate through the min length string
//        repeat(minLength) { i ->
//            // get the first character of the first string
//            val char = strs[0][i]
//
//            // check if the character is not the same in the rest of the strings
//            if (strs.any { it[i] != char }) {
//                return strs[0].take(i)
//            }
//        }
//
//        return strs[0].take(minLength)

        // Solution 2.
        if (strs.size == 1) return strs[0]

        val firstStr = strs.first()
        for (i in firstStr.indices) {
            for (j in 1..<strs.size) {
                if (i == strs[j].length || firstStr[i] != strs[j][i]) {
                    return firstStr.substring(0, i)
                }
            }
        }

        return firstStr
    }

    val tests = listOf(
        arrayOf("flower", "flow", "flight"), // "fl"
        arrayOf("dog", "racecar", "car"), // ""
    )

    tests.forEach { test ->
        println(longestCommonPrefix(test))
    }
}