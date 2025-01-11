package medium

/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all
 * the original letters exactly once.
 *
 * Constraints:
 * - 1 <= strs.length <= 10^4
 * - 0 <= strs[i].length <= 100
 * - strs[i] consists of lower-case English letters.
 *
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 *
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 *
 */
fun main() {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        // Solution 1.
//        val map = mutableMapOf<MutableMap<Char, Int>, MutableList<String>>()
//        for (word in strs) {
//            val charMap = mutableMapOf<Char, Int>()
//            for (char in word) {
//                charMap[char] = charMap.getOrDefault(char, 0) + 1
//            }
//            map[charMap] = map.getOrDefault(charMap, mutableListOf()).apply { add(word) }
//        }
//
//        return map.values.toList()

        // Solution 2.
        val res = mutableMapOf<List<Int>, MutableList<String>>()

        for (s in strs) {
            val count = MutableList(26) { 0 }
            for (c in s) {
                count[c - 'a']++
            }
            res.getOrPut(count) { mutableListOf() }.add(s)
        }

        return res.values.toList()
    }

    val input = arrayOf("eat", "tea", "tan", "ate", "nat", "bat")
    println(groupAnagrams(input)) // [[bat], [nat, tan], [ate, eat, tea]]

    val input2 = arrayOf("")
    println(groupAnagrams(input2)) // [[]]

    val input3 = arrayOf("a")
    println(groupAnagrams(input3)) // [[a]]
}
