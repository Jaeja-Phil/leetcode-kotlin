package medium

/**
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated
 * sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Constraints:
 * - 1 <= s.length <= 300
 * - 1 <= wordDict.length <= 1000
 * - 1 <= wordDict[i].length <= 20
 * - s and wordDict[i] consist of only lowercase English letters.
 * - All the strings of wordDict are unique.
 *
 * Example 1:
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 *
 * Example 2:
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 *
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 */
fun main() {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        // Solution 1.
//        val wordSet = wordDict.toSet()
//        val dp = BooleanArray(s.length + 1)
//        dp[0] = true
//
//        for (i in 1..s.length) {
//            for (j in 0 until i) {
//                if (dp[j] && wordSet.contains(s.substring(j, i))) {
//                    dp[i] = true
//                    break
//                }
//            }
//        }
//
//        return dp[s.length]

        val dp = BooleanArray(s.length + 1) { false }
        dp[s.length] = true

        for (i in s.lastIndex downTo 0) {
            for (word in wordDict) {
                if (
                // check if i + current word's length is in bounds
                    i + word.length <= s.length &&
                    // check if current substring is equal to the word
                    s.substring(i, i + word.length) == word
                ) {
                    // if the substring is equal to the word, check if the next substring is true
                    dp[i] = dp[i + word.length]
                    if (dp[i]) break
                }
            }
        }

        return dp[0]
    }

    // Test cases
    println(wordBreak("leetcode", listOf("leet", "code"))) // Expected: true
    println(wordBreak("applepenapple", listOf("apple", "pen"))) // Expected: true
    println(wordBreak("catsandog", listOf("cats", "dog", "sand", "and", "cat"))) // Expected: false
}