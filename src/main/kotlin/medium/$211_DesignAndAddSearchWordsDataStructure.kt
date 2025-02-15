package medium

/**
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 * - WordDictionary() Initializes the object.
 * - void addWord(word) Adds word to the data structure, it can be matched later.
 * - bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.
 *   "word" may contain dots '.' where dots can be matched with any letter.
 *
 * Constraints:
 * - 1 <= word.length <= 25
 * - word in addWord consists of only lowercase English letters.
 * - word in search consist of lowercase English letters and '.'.
 * - There will be at most 2 dots in word for search queries
 * - At most 10^4 calls will be made to addWord and search.
 *
 * Example 1:
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 */
fun main() {
    class WordDictionary() {
        private val root = TrieNode()

        inner class TrieNode {
            val children = Array<TrieNode?>(26) { null }
            var isEnd = false
        }

        fun addWord(word: String) {
            var node = root
            for (char in word) {
                val index = char - 'a'
                if (node.children[index] == null) {
                    node.children[index] = TrieNode()
                }
                node = node.children[index]!!
            }
            node.isEnd = true
        }

        fun search(word: String): Boolean {
            return search(word, 0, root)
        }

        private fun search(word: String, index: Int, node: TrieNode): Boolean {
            if (index == word.length) {
                return node.isEnd
            }

            val char = word[index]
            if (char == '.') {
                for (child in node.children) {
                    if (child != null && search(word, index + 1, child)) {
                        return true
                    }
                }
                return false
            }

            val child = node.children[char - 'a']
            return child != null && search(word, index + 1, child)
        }
    }

    val wordDictionary = WordDictionary()
    wordDictionary.addWord("bad")
    wordDictionary.addWord("dad")
    wordDictionary.addWord("mad")
    println(wordDictionary.search("pad")) // false
    println(wordDictionary.search("bad")) // true
    println(wordDictionary.search(".ad")) // true
    println(wordDictionary.search("b..")) // true
}