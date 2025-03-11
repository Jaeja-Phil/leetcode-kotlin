package easy

/**
 * Given a positive integer n, write a function that returns the number of set bits in its binary representation
 * (also known as the Hamming weight).
 *
 * Constraints:
 * - 1 <= n <= 231 - 1
 *
 * Example 1:
 * Input: n = 11
 * Output: 3
 *
 * Example 2:
 * Input: n = 128
 * Output: 1
 *
 * Example 3:
 * Input: n = 2147483645
 * Output: 30
 */
fun main() {
    fun hammingWeight(n: Int): Int {
        // Solution 1
//         return n.countOneBits()

        // Solution 2
//        var count = 0
//        var num = n
//        while (num != 0) {
//            count += num % 2
//            num = num shr 1
//        }
//
//        return count

        // Solution 3
        var count = 0
        var num = n
        while (num != 0) {
            count++
            num = num and (num - 1)
        }

        return count
    }

    println(hammingWeight(11)) // 3
    println(hammingWeight(128)) // 1
    println(hammingWeight(2147483645)) // 30
}