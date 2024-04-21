package medium

/**
 * Given an array of integers citations where citations[i] is the number of citations a researcher received for their
 * ith paper and citations is sorted in ascending order, return the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of h such that the
 * given researcher has published at least h papers that have each been cited at least h times.
 *
 * You must write an algorithm that runs in logarithmic time.
 *
 * constraints:
 * - n == citations.length
 * - 1 <= n <= 10^5
 * - 0 <= citations[i] <= 1000
 * - citations is sorted in ascending order.
 *
 * Example 1:
 * Input: citations = [0,1,3,5,6]
 * Output: 3
 * Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had received 0, 1, 3, 5, 6
 * citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with
 * no more than 3 citations, the researcher's h-index is 3.
 *
 * Example 2:
 * Input: citations = [1,2,100]
 * Output: 2
 */
fun main() {
    fun hIndex(citations: IntArray): Int {
        var left = 0
        var right = citations.lastIndex
        while (left <= right) {
            val mid = left + (right - left) / 2
            val currentPublishedCount = citations.size - mid
            val currentCitationCount = citations[mid]
            if (currentCitationCount >= currentPublishedCount) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }

        return citations.size - left
    }

    println(hIndex(intArrayOf(0, 1, 3, 5, 6))) // 3
    println(hIndex(intArrayOf(1, 2, 100))) // 2
    println(hIndex(intArrayOf(0, 0, 0, 0, 0))) // 0
    println(hIndex(intArrayOf(0, 0, 0, 0, 1))) // 1
    println(hIndex(intArrayOf(1, 2))) // 1
    println(hIndex(intArrayOf(100))) // 1
}
