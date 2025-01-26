package easy

/**
 * Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the
 * functions of a normal stack (push, top, pop, and empty).
 *
 * Implement the MyStack class:
 * - void push(int x) Pushes element x to the top of the stack.
 * - int pop() Removes the element on the top of the stack and returns it.
 * - int top() Returns the element on the top of the stack.
 * - boolean empty() Returns true if the stack is empty, false otherwise.
 *
 * Notes:
 * - You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and
 *   is empty operations are valid.
 * - Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque
 *   (double-ended queue) as long as you use only a queue's standard operations.
 *
 * Constraints:
 * - 1 <= x <= 9
 * - At most 100 calls will be made to push, pop, top, and empty.
 * - All the calls to pop and top are valid.
 *
 * Example 1:
 * Input
 * ["MyStack", "push", "push", "top", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * Output
 * [null, null, null, 2, 2, false]
 * Explanation
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // return 2
 * myStack.pop(); // return 2
 * myStack.empty(); // return False
 *
 * Example 2:
 * Input
 * ["MyStack", "push", "pop", "empty"]
 * [[], [1], [], []]
 * Output
 * [null, null, 1, true]
 * Explanation
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.pop(); // return 1
 * myStack.empty(); // return True
 */
fun main() {
    class MyStack {
        private var q1 = ArrayDeque<Int>()
        private var q2 = ArrayDeque<Int>()
        var top = 0

        fun push(x: Int) {
            q1.add(x)
            top = x
        }

        fun pop(): Int {
            while (q1.size > 1) {
                val element = q1.removeFirst()
                top = element
                q2.add(element)
            }

            val result = q1.removeFirst()
            q1 = q2
            q2 = ArrayDeque()
            return result
        }

        fun top(): Int {
            return top
        }

        fun empty(): Boolean {
            return q1.isEmpty()
        }
    }

    val myStack = MyStack()
    myStack.push(1)
    myStack.push(2)
    println(myStack.top()) // 2
    println(myStack.pop()) // 2
    println(myStack.empty()) // false

    val myStack2 = MyStack()
    myStack2.push(1)
    println(myStack2.pop()) // 1
    println(myStack2.empty()) // true
}