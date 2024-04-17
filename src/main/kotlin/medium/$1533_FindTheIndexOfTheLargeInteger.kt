package medium

/**
 * We have an integer array arr, where all the integers in arr are equal except for one integer which is larger
 * than the rest of the integers. You will not be given direct access to the array, instead, you will have an API
 * ArrayReader which have the following functions:
 *
 * - int compareSub(int l, int r, int x, int y): where 0 <= l, r, x, y < ArrayReader.length(), l <= r and x <= y.
 * The function compares the sum of sub-array arr[l..r] with the sum of the sub-array arr[x..y] and returns:
 *   - 1 if arr[l]+arr[l+1]+...+arr[r] > arr[x]+arr[x+1]+...+arr[y].
 *   - 0 if arr[l]+arr[l+1]+...+arr[r] == arr[x]+arr[x+1]+...+arr[y].
 *   - -1 if arr[l]+arr[l+1]+...+arr[r] < arr[x]+arr[x+1]+...+arr[y].
 * - int length(): Returns the size of the array.
 *
 * You are allowed to call compareSub() 20 times at most. You can assume both functions work in O(1) time.
 *
 * Return the index of the array arr which has the largest integer.
 *
 * constraints:
 * - 2 <= arr.length <= 5 * 10^5
 * - 1 <= arr[i] <= 100
 * - All elements of arr are equal except for one element which is larger than the rest.
 *
 * Example 1:
 * Input: arr = [7,7,7,7,10,7,7,7]
 * Output: 4
 * Explanation: The following calls to the API
 * reader.compareSub(0, 0, 1, 1) // returns 0 this is a query comparing the sub-array (0, 0) with the sub array (1, 1), (i.e. compares arr[0] with arr[1]).
 * Thus we know that arr[0] and arr[1] doesn't contain the largest element.
 * reader.compareSub(2, 2, 3, 3) // returns 0, we can exclude arr[2] and arr[3].
 * reader.compareSub(4, 4, 5, 5) // returns 1, thus for sure arr[4] is the largest element in the array.
 * Notice that we made only 3 calls, so the answer is valid.
 *
 */
fun main() {
    class ArrayReader(
        val arr: IntArray
    ) {
        fun compareSub(l: Int, r: Int, x: Int, y: Int): Int {
            return 0
        }
        fun length(): Int {
            return arr.size
        }
    }

    fun getIndex(reader: ArrayReader): Int {
        var left = 0
        var right = reader.length() - 1
        while (left < right) {
            // compare the sum of the first half and the second half
            val firstHalfEnd = left + (right - left) / 2
            // + 1 to make sure the second half has the same size as the first half
            val secondHalfStart = left + (right - left + 1) / 2
            val result = reader.compareSub(left, firstHalfEnd, secondHalfStart, right)
            if (result == 0) {
                // 0 means the largest element is in the overlapping area (which means the first half and the second half are the same)
                // so we can return any of firstHalfEnd or secondHalfStart
                return firstHalfEnd
            } else if (result < 0) {
                // -1 means the largest element is in the second half, shrink the first half
                left = secondHalfStart
            } else {
                // 1 means the largest element is in the first half, shrink the second half
                right = firstHalfEnd
            }
        }

        return left
    }

    val reader = ArrayReader(intArrayOf(7, 7, 7, 7, 10, 7, 7, 7))
    println(getIndex(reader)) // 4
}