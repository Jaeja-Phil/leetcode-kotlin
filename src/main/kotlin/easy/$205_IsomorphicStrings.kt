package easy

/**
 * Given two strings s and t, determine if they are isomorphic. Two strings s and t are isomorphic if the characters in
 * s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character, but a character may map to itself.
 *
 * constraints:
 * - 1 <= s.length <= 5 * 10^4
 * - t.length == s.length
 * - s and t consist of any valid ascii character.
 *
 * Example 1:
 * Input: s = "egg", t = "add"
 * Output: true
 *
 * Example 2:
 * Input: s = "foo", t = "bar"
 * Output: false
 *
 * Example 3:
 * Input: s = "paper", t = "title"
 * Output: true
 */
fun main() {
    fun isIsomorphic(s: String, t: String): Boolean {
        val charReplaceMap = mutableMapOf(s[0] to t[0])
        for (i in 1..<s.length) {
            if (charReplaceMap.containsKey(s[i])) {
                if (charReplaceMap[s[i]] != t[i]) return false
                continue
            }
            if (charReplaceMap.values.contains(t[i])) return false
            charReplaceMap[s[i]] = t[i]
        }
        return true
    }

    val s = "egg"
    val t = "add"
    println(isIsomorphic(s, t)) // true

    val s2 = "foo"
    val t2 = "bar"
    println(isIsomorphic(s2, t2)) // false

    val s3 = "paper"
    val t3 = "title"
    println(isIsomorphic(s3, t3)) // true

    val s4 = "badc"
    val t4 = "baba"
    println(isIsomorphic(s4, t4)) // false
}
