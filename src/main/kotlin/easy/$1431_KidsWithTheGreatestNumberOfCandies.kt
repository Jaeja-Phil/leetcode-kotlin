package easy

/**
 * There are n kids with candies. You are given an integer array candies, where each candies[i] represents the
 * number of candies the ith kid has, and an integer extraCandies, denoting the number of extra candies that you have.
 *
 * Return a boolean array result of length n, where result[i] is true if, after giving the ith kid all the extraCandies,
 * they will have the greatest number of candies among all the kids, or false otherwise.
 *
 * Note that multiple kids can have the greatest number of candies.
 *
 * constraints:
 * - n == candies.length
 * - 2 <= n <= 100
 * - 1 <= candies[i] <= 100
 * - 1 <= extraCandies <= 50
 *
 * Example 1:
 * Input: candies = [2,3,5,1,3], extraCandies = 3
 * Output: [true,true,true,false,true]
 * Explanation:
 * - Kid 1 has 2 candies and if he or she receives all extra candies (3) will have 5 candies --- the greatest number of candies among the kids.
 * - Kid 2 has 3 candies and if he or she receives at least 2 extra candies will have the greatest number of candies among the kids.
 * - Kid 3 has 5 candies and this is already the greatest number of candies among the kids.
 * - Kid 4 has 1 candy and even if he or she receives all extra candies will only have 4 candies.
 * - Kid 5 has 3 candies and if he or she receives at least 2 extra candies will have the greatest number of candies among the kids.
 *
 * Example 2:
 * Input: candies = [4,2,1,1,2], extraCandies = 1
 * Output: [true,false,false,false,false]
 * Explanation: There is only 1 extra candy.
 * - Kid 1 will always have the greatest number of candies, even if he or she receives the extra candy.
 *
 * Example 3:
 * Input: candies = [12,1,12], extraCandies = 10
 * Output: [true,false,true]
 */
fun main() {
    val res1 = `Kids With the Greatest Number of Candies`(intArrayOf(2, 3, 5, 1, 3), 3)
    println(res1) // [true,true,true,false,true]

    val res2 = `Kids With the Greatest Number of Candies`(intArrayOf(4, 2, 1, 1, 2), 1)
    println(res2) // [true,false,false,false,false]

    val res3 = `Kids With the Greatest Number of Candies`(intArrayOf(12, 1, 12), 10)
    println(res3) // [true,false,true]
}

fun `Kids With the Greatest Number of Candies`(candies: IntArray, extraCandies: Int): List<Boolean> {
    /**
     * find the maximum value of candies
     * maxOrNull
     * - Returns the largest element or `null` if there are no elements.
     */
    val max = candies.maxOrNull() ?: 0

    /** initialize a list to store the result **/
    val result = mutableListOf<Boolean>()

    /**
     * use for loop to iterate through the candies
     * - if the sum of candies[i] and extraCandies is greater than or equal to the maximum value of candies,
     *   then add true to the result list
     * - otherwise, add false to the result list
     */
    for (i in candies.indices) {
        result.add(candies[i] + extraCandies >= max)
    }

    return result
}