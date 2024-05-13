package hard

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring
 * of s such that every character in t (including duplicates) is included in the window. If there is no such substring,
 * return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 *
 * constraints:
 * - m == s.length
 * - n == t.length
 * - 1 <= m, n <= 10^5
 * - s and t consist of uppercase and lowercase English letters.
 *
 * Example 1:
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 *
 * Example 2:
 * Input: s = "a", t = "a"
 * Output: "a"
 *
 * Example 3:
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 */

fun main() {
    fun minWindow(subject: String, target: String): String {
        var targetCharMap = target.groupingBy { it }.eachCount().toMutableMap()
        var (start, end) = 0 to 0
        var result = ""
        var count = target.length
        while (end < subject.length) {
            val currentChar = subject[end]
            if (targetCharMap.containsKey(currentChar)) {
                targetCharMap[currentChar] = targetCharMap[currentChar]!! - 1
                if (targetCharMap[currentChar]!! >= 0) {
                    count--
                }
            }

            while (count == 0) {
                if (result.isEmpty() || result.length > end - start + 1) {
                    result = subject.substring(start, end + 1)
                }
                val startChar = subject[start++]
                if (targetCharMap.containsKey(startChar)) {
                    targetCharMap[startChar] = targetCharMap[startChar]!! + 1
                    if (targetCharMap[startChar]!! > 0) {
                        count++
                    }
                }
            }

            end++
        }

        return result
    }

    println(minWindow("ADOBECODEBANC", "ABC")) // BANC
    println(minWindow("a", "a")) // a
    println(minWindow("a", "aa")) // ""
}
