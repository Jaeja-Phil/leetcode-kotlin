package medium

/**
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is
 * decoded back to the original list of strings.
 *
 * Please implement encode and decode methods.
 *
 * Constraints:
 * - 0 <= strs.length <= 100
 * - 0 <= strs[i].length <= 200
 * - strs[i] contains only UTF-8 characters.
 *
 * Example 1:
 * Input: ["lint","code","love","you"]
 * Output:["lint","code","love","you"]
 * Explanation:
 * One possible way of encoding is: "lint:;code:;love:;you"
 *
 * Example 2:
 * Input: ["we","say",":","yes"]
 * Output: ["we","say",":","yes"]
 *
 */
fun main() {
    fun encode(strs: List<String>): String {
        val sb = StringBuilder()
        strs.forEach { str ->
            sb.append(str.length)
                .append("#")
                .append(str)
        }

        return sb.toString()
    }

    fun decode(encoded: String): List<String> {
        var i = 0
        val length = encoded.length
        val res = mutableListOf<String>()
        while (i < length) {
            var j = i
            while (encoded[j] != '#') {
                j++
            }
            val currentStringLength = encoded.substring(i..<j).toInt()
            val currentString = encoded.substring(j + 1..<j + 1 + currentStringLength)
            res.add(currentString)
            i = j + 1 + currentStringLength
        }

        return res
    }

    val strs1 = listOf("lint", "code", "love", "you")
    val encoded1 = encode(strs1)
    val decoded1 = decode(encoded1)
    println(decoded1 == strs1) // true

    val strs2 = listOf("we", "say", ":", "yes")
    val encoded2 = encode(strs2)
    val decoded2 = decode(encoded2)
    println(decoded2 == strs2) // true
}