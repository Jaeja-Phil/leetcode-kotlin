package easy

/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all
 * the original letters exactly once.
 *
 * Constraints:
 * - 1 <= s.length, t.length <= 5 * 10^4
 * - s and t consist of lowercase English letters.
 *
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 *
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 */
fun main() {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val sMap = s.groupingBy { it }.eachCount()
        val tMap = mutableMapOf<Char, Int>()

        for (char in t) {
            tMap.getOrDefault(char, 0)
                .let {
                    if (it >= sMap.getOrDefault(char, 0)) return false
                    tMap[char] = it + 1
                }
        }

        return true
    }

    val tests = listOf(
        listOf("anagram", "nagaram") to true,
        listOf("rat", "car") to false,
    )
    for ((input, value) in tests) {
        println("Input: s = ${input[0]}, t = ${input[1]}")
        println("Output: ${isAnagram(input[0], input[1])}")
        println("Expected: $value")
        println()
    }
}
