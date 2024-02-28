package medium

/**
 * You are given two positive integer arrays spells and potions, of length n and m respectively, where spells[i]
 * represents the strength of the ith spell and potions[j] represents the strength of the jth potion.
 *
 * You are also given an integer success. A spell and potion pair is considered successful if the product of
 * their strengths is at least success.
 *
 * Return an integer array pairs of length n where pairs[i] is the number of potions that will form a successful
 * pair with the ith spell.
 *
 * constraints:
 * - n == spells.length
 * - m == potions.length
 * - 1 <= n, m <= 10^5
 * - 1 <= spells[i], potions[j] <= 10^5
 * - 1 <= success <= 10^10
 *
 * Example 1:
 * Input: spells = [5,1,3], potions = [1,2,3,4,5], success = 7
 * Output: [4,0,3]
 * Explanation:
 * - 0th spell: 5 * [1,2,3,4,5] = [5,10,15,20,25]. 4 pairs are successful.
 * - 1st spell: 1 * [1,2,3,4,5] = [1,2,3,4,5]. 0 pairs are successful.
 * - 2nd spell: 3 * [1,2,3,4,5] = [3,6,9,12,15]. 3 pairs are successful.
 * Thus, [4,0,3] is returned.
 *
 * Example 2:
 * Input: spells = [3,1,2], potions = [8,5,8], success = 16
 * Output: [2,0,2]
 * Explanation:
 * - 0th spell: 3 * [8,5,8] = [24,15,24]. 2 pairs are successful.
 * - 1st spell: 1 * [8,5,8] = [8,5,8]. 0 pairs are successful.
 * - 2nd spell: 2 * [8,5,8] = [16,10,16]. 2 pairs are successful.
 * Thus, [2,0,2] is returned.
 */
fun main() {
    fun successfulPairs(spells: IntArray, potions: IntArray, success: Long): IntArray {
        // sort the potions array
        potions.sort()

        // init n and m to the length of the spells and potions array respectively
        val n = spells.size
        val m = potions.size

        // create an array to store the number of successful pairs
        val pairs = IntArray(n)

        // iterate through the spells array
        for (i in 0 ..< n) {
            // find the index in the potions array where the product of the spell and potion is greater than or equal to
            // the success value
            var left = 0
            var right = m
            while (left < right) {
                val mid = left + (right - left) / 2
                if (spells[i].toLong() * potions[mid] >= success) {
                    right = mid
                } else {
                    left = mid + 1
                }
            }
            pairs[i] = m - left
        }

        return pairs
    }

    val spells1 = intArrayOf(5, 1, 3)
    val potions1 = intArrayOf(1, 2, 3, 4, 5)
    val success1 = 7L
    println(successfulPairs(spells1, potions1, success1).contentToString()) // [4, 0, 3]

    val spells2 = intArrayOf(3, 1, 2)
    val potions2 = intArrayOf(8, 5, 8)
    val success2 = 16L
    println(successfulPairs(spells2, potions2, success2).contentToString()) // [2, 0, 2]
}