package easy

/**
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from
 * magazine and false otherwise.
 *
 * Each letter in magazine can only be used once in ransomNote.
 *
 * Constraints:
 * - 1 <= ransomNote.length, magazine.length <= 10^5
 * - ransomNote and magazine consist of lowercase English letters.
 *
 * Example 1:
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 *
 * Example 2:
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 *
 * Example 3:
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 *
 */
fun main() {
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val ransomNoteCharMap = ransomNote.groupingBy { it }.eachCount().toMap()
        val magazineCharMap = magazine.groupingBy { it }.eachCount().toMap()

        return ransomNoteCharMap.all {
            (magazineCharMap[it.key] ?: 0) >= it.value
        }
    }

    val ransomNote = "aa"
    val magazine = "aab"
    println(canConstruct(ransomNote, magazine)) // true

    val ransomNote2 = "a"
    val magazine2 = "b"
    println(canConstruct(ransomNote2, magazine2)) // false

    val ransomNote3 = "aa"
    val magazine3 = "ab"
    println(canConstruct(ransomNote3, magazine3)) // false
}
