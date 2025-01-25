package easy

import java.util.Stack

/**
 * You are keeping the scores for a baseball game with strange rules. At the beginning of the game, you start with an
 * empty record.
 *
 * You are given a list of strings operations, where operations[i] is the ith operation you must apply to the record and
 * is one of the following:
 * - An integer x.
 *   - Record a new score of x.
 * - '+'.
 *   - Record a new score that is the sum of the previous two scores.
 * - 'D'.
 *   - Record a new score that is the double of the previous score.
 * - 'C'.
 *   - Invalidate the previous score, removing it from the record.
 *
 * Return the sum of all the scores on the record after applying all the operations.
 *
 * The test cases are generated such that the answer and all intermediate calculations fit in a 32-bit integer and that
 * all operations are valid.
 *
 * Constraints:
 * - 1 <= operations.length <= 1000
 * - operations[i] is 'C', 'D', '+', or a string representing an integer in the range [-3 * 10^4, 3 * 10^4].
 * - For operations[i] = 'D', there will always be at least one previous score on the record.
 * - For operations[i] = '+', there will always be at least two previous scores on the record.
 *
 * Example 1:
 * Input: operations = ["5","2","C","D","+"]
 * Output: 30
 *
 * Example 2:
 * Input: operations = ["5","-2","4","C","D","9","+","+"]
 * Output: 27
 *
 * Example 3:
 * Input: operations = ["1", "C"]
 * Output: 0
 *
 *
 */
fun main() {
    fun calPoints(operations: Array<String>): Int {
        val stack = Stack<Int>()
        for (operation in operations) {
            when (operation) {
                "C" -> stack.pop()
                "D" -> stack.push(stack.peek() * 2)
                "+" -> {
                    val top = stack.pop()
                    val newTop = top + stack.peek()
                    stack.push(top)
                    stack.push(newTop)
                }
                else -> stack.push(operation.toInt())
            }
        }

        return stack.sum()
    }

    println(calPoints(arrayOf("5", "2", "C", "D", "+"))) // 30
    println(calPoints(arrayOf("5", "-2", "4", "C", "D", "9", "+", "+"))) // 27
    println(calPoints(arrayOf("1", "C"))) // 0
}