package easy

/**
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if
 * needle is not part of haystack.
 *
 * constraints:
 * - 1 <= haystack.length, needle.length <= 10 ^ 4
 * - haystack and needle consist of lowercase English letters only.
 *
 * Example 1:
 *
 * Input: haystack = "sadbutsad", needle = "sad"
 * Output: 0
 * Explanation: "sad" occurs at index 0 and 6.
 * The first occurrence is at index 0, so we return 0.
 * Example 2:
 *
 * Input: haystack = "leetcode", needle = "leeto"
 * Output: -1
 * Explanation: "leeto" did not occur in "leetcode", so we return -1.
 */
fun main() {
    fun strStr(haysStack: String, needle: String): Int {
        val haysStackLen = haysStack.length
        val needleLen = needle.length
        if (needleLen == haysStackLen || needleLen == 0) return 0
        else if (needleLen > haysStackLen) return -1

        // KMP solution
        // build the longest border array
        val longestBorder = IntArray(needleLen)
        var prev = 0
        var i = 1
        while (i < needleLen) {
            if (needle[i] == needle[prev]) {
                prev++
                longestBorder[i] = prev
                i++
            } else {
                if (prev != 0) {
                    prev = longestBorder[prev - 1]
                } else {
                    longestBorder[i] = 0
                    i++
                }
            }
        }
        println(longestBorder.joinToString(","))

        // search for the needle in the haystack
        var haystackIndex = 0
        var needleIndex = 0
        while (haystackIndex < haysStackLen) {
            if (haysStack[haystackIndex] == needle[needleIndex]) {
                haystackIndex++
                needleIndex++
            }
            if (needleIndex == needleLen) return haystackIndex - needleLen
            else if (haystackIndex < haysStackLen && haysStack[haystackIndex] != needle[needleIndex]) {
                if (needleIndex != 0) {
                    needleIndex = longestBorder[needleIndex - 1]
                } else {
                    haystackIndex++
                }
            }
        }

        return -1
    }

    val haysStack1 = "sadbutsad"
    val needle1 = "sad"
    println(strStr(haysStack1, needle1)) // 0

    val haysStack2 = "leetcode"
    val needle2 = "leeto"
    println(strStr(haysStack2, needle2)) // -1

    val haysStack3 = "aaa"
    val needle3 = "aaaa"
    println(strStr(haysStack3, needle3)) // -1

    val haystack4 = "mississippi"
    val needle4 = "issip"
    println(strStr(haystack4, needle4)) // 4
}
