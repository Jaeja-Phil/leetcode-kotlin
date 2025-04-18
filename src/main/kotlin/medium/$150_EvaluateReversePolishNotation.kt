package medium

import java.util.Stack

/**
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 * Evaluate the expression. Return an integer that represents the value of the expression.
 *
 * Note that:
 * - The valid operators are '+', '-', '*', and '/'.
 * - Each operand may be an integer or another expression.
 * - The division between two integers always truncates toward zero.
 * - There will not be any division by zero.
 * - The input represents a valid arithmetic expression in a reverse polish notation.
 * - The answer and all the intermediate calculations can be represented in a 32-bit integer.
 *
 * Constraints:
 * - 1 <= tokens.length <= 10^4
 * - tokens[i] is either an operator: '+', '-', '*', or '/', or an integer in the range [-200, 200]
 *
 * Example 1:
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 *
 * Example 2:
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 *
 * Example 3:
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) / -11))) + 17) + 5
 */
fun main() {
    fun evalRPN(tokens: Array<String>): Int {
        // Solution 1.
//        if (tokens.size == 1) return tokens[0].toInt()
//
//        val operators = setOf("+", "-", "*", "/")
//        val numberStack = mutableListOf<Int>()
//        val operatorStack = mutableListOf<String>()
//        for (token in tokens) {
//            when {
//                operators.contains(token) -> operatorStack.add(token)
//                else -> numberStack.add(token.toInt())
//            }
//            if (token in operators) {
//                val operator = operatorStack.removeLast()
//                val second = numberStack.removeLast()
//                val first = numberStack.removeLast()
//                when (operator) {
//                    "+" -> numberStack.add(first + second)
//                    "-" -> numberStack.add(first - second)
//                    "*" -> numberStack.add(first * second)
//                    "/" -> numberStack.add(first / second)
//                }
//            }
//        }
//
//        return numberStack.last()

        // Solution 2.
        val stack = Stack<Int>()
        for (token in tokens) {
            when (token) {
                "+" -> stack.push(stack.pop() + stack.pop())
                "-" -> {
                    val second = stack.pop()
                    val first = stack.pop()
                    stack.push(first - second)
                }
                "*" -> stack.push(stack.pop() * stack.pop())
                "/" -> {
                    val second = stack.pop()
                    val first = stack.pop()
                    stack.push(first / second)
                }
                else -> stack.push(token.toInt())
            }
        }

        return stack.pop()
    }

    val result = evalRPN(arrayOf("2", "1", "+", "3", "*"))
    println(result) // 9

    val result2 = evalRPN(arrayOf("4", "13", "5", "/", "+"))
    println(result2) // 6

    val result3 = evalRPN(arrayOf("10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"))
    println(result3) // 22
}
