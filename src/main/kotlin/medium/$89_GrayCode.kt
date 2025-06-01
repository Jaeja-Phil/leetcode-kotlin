package medium

/**
 * An n-bit gray code sequence is a sequence of 2n integers where:
 * - Every integer is in the inclusive range [0, 2n - 1],
 * - The first integer is 0,
 * - An integer appears no more than once in the sequence,
 * - The binary representation of every pair of adjacent integers differs by exactly one bit, and
 * - The binary representation of the first and last integers differs by exactly one bit.
 *
 * Given an integer n, return any valid n-bit gray code sequence.
 *
 * Constraints:
 * - 1 <= n <= 16
 *
 * Example 1:
 * Input: n = 2
 * Output: [0,1,3,2]
 * Explanation:
 * The binary representation of [0,1,3,2] is [00,01,11,10].
 * - 00 and 01 differ by one bit
 * - 01 and 11 differ by one bit
 * - 11 and 10 differ by one bit
 * - 10 and 00 differ by one bit
 * [0,2,3,1] is also a valid gray code sequence, whose binary representation is [00,10,11,01].
 * - 00 and 10 differ by one bit
 * - 10 and 11 differ by one bit
 * - 11 and 01 differ by one bit
 * - 01 and 00 differ by one bit
 *
 * Example 2:
 * Input: n = 1
 * Output: [0,1]
 */
fun main() {
    fun grayCode(n: Int): List<Int> {
        val result = mutableListOf<Int>()
        for (i in 0..<(1 shl n)) {
            result.add(i xor (i shr 1))
        }
        return result
    }

    listOf(
        2 to listOf(0, 1, 3, 2),
        1 to listOf(0, 1),
    ).forEach { (n, expected) ->
        val result = grayCode(n)
        println("Gray code for n=$n: $result")
        assert(result == expected) { "Expected $expected but got $result" }
    }
}