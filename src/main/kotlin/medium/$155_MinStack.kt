package medium

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 * - MinStack() initializes the stack object.
 * - void push(int val) pushes the element val onto the stack.
 * - void pop() removes the element on the top of the stack.
 * - int top() gets the top element of the stack.
 * - int getMin() retrieves the minimum element in the stack.
 *
 * You must implement a solution with O(1) time complexity for each function.
 *
 * Constraints:
 * - -2^31 <= val <= 2^31 - 1
 * - Methods pop, top and getMin operations will always be called on non-empty stacks.
 * - At most 3 * 10^4 calls will be made to push, pop, top, and getMin.
 *
 * Example 1:
 * Input:
 * ["MinStack", "push", "push", "push", "getMin", "pop", "top", "getMin"]
 * [[], [-2], [0], [-3], [], [], [], []]
 * Output:
 * [null, null, null, null, -3, null, 0, -2]
 * Explanation:
 * ```
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 * ```
 */
fun main() {
    val minStack = `$155_MinStack`()
    minStack.push(-2)
    minStack.push(0)
    minStack.push(-3)
    println(minStack.getMin()) // return -3
    minStack.pop()
    println(minStack.top())    // return 0
    println(minStack.getMin()) // return -2
}

class `$155_MinStack` {
    // Solution 1.
    // 1st is the actual value, 2nd is the minimum value with given value
    private val stack = mutableListOf<Pair<Int, Int>>()

    fun push(`val`: Int) {
        if (stack.isEmpty()) {
            stack.add(`val` to `val`)
            return
        }

        val min = minOf(`val`, stack.last().second)
        stack.add(`val` to min)
    }

    fun pop() {
        stack.removeLast()
    }

    fun top(): Int {
        return stack.last().first
    }

    fun getMin(): Int {
        return stack.last().second
    }

    // Solution 2.
//    private val stack = mutableListOf<Int>()
//    private val minStack = mutableListOf<Int>()
//
//    fun push(`val`: Int) {
//        stack.add(`val`)
//        val minVal = if (minStack.isEmpty()) `val` else minOf(`val`, minStack.last())
//        minStack.add(minVal)
//    }
//
//    fun pop() {
//        stack.removeLast()
//        minStack.removeLast()
//    }
//
//    fun top(): Int {
//        return stack.last()
//    }
//
//    fun getMin(): Int {
//        return minStack.last()
//    }
}
