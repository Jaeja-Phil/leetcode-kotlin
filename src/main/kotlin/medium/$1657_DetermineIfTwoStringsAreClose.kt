package medium

/**
 * Two strings are considered close if you can attain one from the other using the following operations:
 * - Operation 1: Swap any two existing characters.
 *  - For example, abcde -> aecdb
 * - Operation 2: Transform every occurrence of one existing character into another existing character,
 *   and do the same with the other character.
 *  - For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
 *
 *  You can use the operations on either string as many times as necessary.
 *
 *  Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
 *
 *  constraints:
 *  - 1 <= word1.length, word2.length <= 10^5
 *  - word1 and word2 contain only lowercase English letters.
 *
 *  Example 1:
 *  Input: word1 = "abc", word2 = "bca"
 *  Output: true
 *  Explanation: You can attain word2 from word1 in 2 operations.
 *  Apply Operation 1: "abc" -> "acb"
 *  Apply Operation 1: "acb" -> "bca"
 *
 *  Example 2:
 *  Input: word1 = "a", word2 = "aa"
 *  Output: false
 *  Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
 *
 *  Example 3:
 *  Input: word1 = "cabbba", word2 = "abbccc"
 *  Output: true
 *  Explanation: You can attain word2 from word1 in 3 operations.
 *  Apply Operation 1: "cabbba" -> "caabbb"
 *  Apply Operation 2: "caabbb" -> "baaccc"
 *  Apply Operation 2: "baaccc" -> "abbccc"
 *
 */
fun main() {
    val res1 = `Determine if Two Strings are Close`("abc", "bca")
    println(res1) // true

    val res2 = `Determine if Two Strings are Close`("a", "aa")
    println(res2) // false

    val res3 = `Determine if Two Strings are Close`("cabbba", "abbccc")
    println(res3) // true
}

fun `Determine if Two Strings are Close`(word1: String, word2: String): Boolean {
    /**
     * return false if the lengths of the two words are not equal
     */
    if (word1.length != word2.length) {
        return false
    }

    /**
     * create a frequency map for each word
     */
    val freqMap1 = mutableMapOf<Char, Int>()
    val freqMap2 = mutableMapOf<Char, Int>()
    word1.forEach {
        freqMap1[it] = freqMap1.getOrDefault(it, 0) + 1
    }
    word2.forEach {
        freqMap2[it] = freqMap2.getOrDefault(it, 0) + 1
    }

    /**
     * return false if the two freqMaps have different characters
     * you can also use iteration to check if the two freqMaps have the same characters:
     * ex) freqMap1.keys.forEach { if (!freqMap2.containsKey(it)) return false }
     */
    if (freqMap1.keys.sorted() != freqMap2.keys.sorted()) {
        return false
    }

    /**
     * if the two freqMaps have the same characters, check if sorted frequencies are the same
     */
    if (freqMap1.values.sorted() != freqMap2.values.sorted()) {
        return false
    }

    /**
     * if the two freqMaps have the same characters and the same frequencies, return true
     */
    return true
}