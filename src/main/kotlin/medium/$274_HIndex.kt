package medium

/**
 * Given an array of integers citations where citations[i] is the number of citations a researcher received for their
 * ith paper, return the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of h such that the
 * given researcher has published at least h papers that have each been cited at least h times.
 *
 * constraints:
 * - n == citations.length
 * - 1 <= n <= 5000
 * - 0 <= citations[i] <= 1000
 *
 * Example 1:
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5
 * citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with
 * no more than 3 citations, the researcher's h-index is 3.
 *
 * Example 2:
 * Input: citations = [1,3,1]
 * Output: 1
 */
fun main() {
    fun hIndex(citations: IntArray): Int {
        citations.sort()
        var hIndex = 0
        while (
            // while within the bounds of the array...
            hIndex < citations.size &&
            // check if current citation is greater than the hIndex
            citations[citations.size - hIndex - 1] > hIndex
        ) {
            hIndex++
        }
        return hIndex
    }

    println(hIndex(intArrayOf(3, 0, 6, 1, 5))) // 3
    println(hIndex(intArrayOf(1, 3, 1))) // 1
}
