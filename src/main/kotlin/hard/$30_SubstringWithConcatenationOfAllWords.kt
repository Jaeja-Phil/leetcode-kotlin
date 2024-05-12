package hard

/**
 * You are given a string s and an array of strings words. All the strings of words are of the same length.
 *
 * A concatenated string is a string that exactly contains all the strings of any permutation of words concatenated.
 * - For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are all
 *   concatenated strings. "acdbef" is not a concatenated string because it is not the concatenation of any permutation of
 *   words.
 *
 * Return an array of the starting indices of all the concatenated substrings in s. You can return the answer in any
 * order.
 *
 * Constraints:
 * - 1 <= s.length <= 10^4
 * - s consists of lower-case English letters.
 * - 1 <= words.length <= 5000
 * - 1 <= words[i].length <= 30
 * - words[i] consists of lower-case English letters.
 *
 * Example 1:
 * Input: s = "barfoothefoobarman", words = ["foo","bar"]
 * Output: [0,9]
 *
 * Example 2:
 * Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * Output: []
 *
 * Example 3:
 * Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * Output: [6,9,12]
 */
fun main() {
    fun findSubstring(s: String, words: Array<String>): List<Int> {
        val result = mutableListOf<Int>()
        val wordLength = words[0].length
        val substrLength = words.size * wordLength
        val wordCountMap = mutableMapOf<String, Int>()
        for (word in words) {
            wordCountMap[word] = wordCountMap.getOrDefault(word, 0) + 1
        }
        var matched = 0
        var windowStart = 0
        var windowEnd = 0
        while (windowEnd <= s.length - substrLength) {
            val used = mutableMapOf<String, Int>()
            val currentWord = s.substring(windowEnd, windowEnd + wordLength)
            if (wordCountMap.containsKey(currentWord)) {
                used[currentWord] = used.getOrDefault(currentWord, 0) + 1
                matched++
                windowEnd += wordLength
                while (matched < words.size) {
                    val nextWord = s.substring(windowEnd, windowEnd + wordLength)
                    if (wordCountMap.containsKey(nextWord)) {
                        used[nextWord] = used.getOrDefault(nextWord, 0) + 1
                        matched++
                        windowEnd += wordLength
                    } else {
                        break
                    }
                }
                if (used == wordCountMap) {
                    result.add(windowStart)
                }
                windowStart++
                windowEnd = windowStart
                matched = 0
            } else {
                windowEnd++
                windowStart = windowEnd
            }
        }

        return result
    }

    println(findSubstring("barfoothefoobarman", arrayOf("foo", "bar"))) // [0, 9]
    println(findSubstring("wordgoodgoodgoodbestword", arrayOf("word", "good", "best", "word"))) // []
    println(findSubstring("barfoofoobarthefoobarman", arrayOf("bar", "foo", "the"))) // [6, 9, 12]

    println("lingmindraboofooowingdingbarrwingmonkeypoundcake".length)
    println("fooowingdingbarrwing".length)
    println("lingmindraboofooowingdingbarrwingmonkeypoundcake".indexOf("fooowingdingbarrwing"))
}