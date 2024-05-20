package easy

/**
 * Given a pattern and a string s, find if s follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
 *
 * Constraints:
 * - 1 <= pattern.length <= 300
 * - pattern contains only lower-case English letters.
 * - 1 <= s.length <= 3000
 * - s contains only lower-case English letters and spaces ' '.
 * - s does not contain any leading or trailing spaces.
 * - All the words in s are separated by a single space.
 *
 * Example 1:
 * Input: pattern = "abba", s = "dog cat cat dog"
 * Output: true
 *
 * Example 2:
 * Input: pattern = "abba", s = "dog cat cat fish"
 * Output: false
 *
 * Example 3:
 * Input: pattern = "aaaa", s = "dog cat cat dog"
 * Output: false
 */
fun main() {
    fun wordPattern(pattern: String, s: String): Boolean {
        val charWordMap = mutableMapOf<Char, String>()
        var sIdx = -1

        for (char in pattern) {
            if (sIdx == s.lastIndex) return false

            var currentWordSb = StringBuilder()
            while (sIdx < s.lastIndex && s[++sIdx] != ' ') {
                currentWordSb.append(s[sIdx])
            }
            val currentWord = currentWordSb.toString()
            if (charWordMap.containsKey(char)) {
                if (charWordMap[char] != currentWord) return false
                continue
            } else if (charWordMap.containsValue(currentWord)) {
                return false
            }
            charWordMap[char] = currentWord
        }

        return sIdx == s.lastIndex
    }

    val pattern = "abba"
    val s = "dog cat cat dog"
    println(wordPattern(pattern, s)) // true

    val pattern2 = "abba"
    val s2 = "dog cat cat fish"
    println(wordPattern(pattern2, s2)) // false

    val pattern3 = "aaaa"
    val s3 = "dog cat cat dog"
    println(wordPattern(pattern3, s3)) // false

    val pattern4 = "abba"
    val s4 = "dog dog dog dog"
    println(wordPattern(pattern4, s4)) // false
}
