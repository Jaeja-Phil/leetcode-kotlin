package medium;

/**
 * You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats where
 * each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time, provided the
 * sum of the weight of those people is at most limit.
 *
 * Return the minimum number of boats to carry every given person.
 *
 * Constraints:
 * - 1 <= people.length <= 5 * 10^4
 * - 1 <= people[i] <= limit <= 3 * 10^4
 *
 * Example 1:
 * Input: people = [1,2], limit = 3
 * Output: 1
 *
 * Example 2:
 * Input: people = [3,2,2,1], limit = 3
 * Output: 3
 *
 * Example 3:
 * Input: people = [3,5,3,4], limit = 5
 * Output: 4
 */
fun main() {
    fun numRescueBoats(people: IntArray, limit: Int): Int {
        var left = 0
        var right = people.lastIndex
        var numBoats = 0
        people.sort()

        while (left <= right) {
            val remain = limit - people[right--]
            if (remain >= people[left]) left++

            numBoats++
        }

        return numBoats
    }

    println(numRescueBoats(intArrayOf(1, 2), 3)) // 1
    println(numRescueBoats(intArrayOf(3, 2, 2, 1), 3)) // 3
    println(numRescueBoats(intArrayOf(3, 5, 3, 4), 5)) // 4
}
