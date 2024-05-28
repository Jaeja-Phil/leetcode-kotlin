package medium

/**
 * Given an absolute path for a Unix-style file system, which begins with a slash '/', transform this path into its
 * simplified canonical path.
 *
 * In Unix-style file system context, a single period '.' signifies the current directory, a double period ".." denotes
 * moving up one directory level, and multiple slashes such as "//" are interpreted as a single slash. In this problem,
 * treat sequences of periods not covered by the previous rules (like "...") as valid names for files or directories.
 *
 * The simplified canonical path should adhere to the following rules:
 * - It must start with a single slash '/'.
 * - Directories within the path should be separated by only one slash '/'.
 * - It should not end with a slash '/', unless it's the root directory.
 * - It should exclude any single or double periods used to denote current or parent directories.
 *
 * Return the new path.
 *
 * Constraints:
 * - 1 <= path.length <= 3000
 * - path consists of English letters, digits, period '.', slash '/' or '_'.
 * - path is a valid absolute Unix path.
 *
 * Example 1:
 * Input: path = "/home/"
 * Output: "/home"
 *
 * Example 2:
 * Input: path = "/../"
 * Output: "/"
 *
 * Example 3:
 * Input: path = "/home//foo/"
 * Output: "/home/foo"
 */
fun main() {
    val solution = `$71_SimplifyPath`()
    val tests = listOf(
        "/home/" to "/home",
        "/../" to "/",
        "/home//foo/" to "/home/foo"
    )

    for ((input, value) in tests) {
        val output = solution.simplifyPath(input)
        println("output: $output, expected: $value, result: ${output == value}")
    }
}

class `$71_SimplifyPath` {
    fun simplifyPath(path: String): String {
        val stack = mutableListOf<String>()

        var i = 0
        while (i < path.length) {
            if (path[i] == '/') {
                i++
                continue
            }

            val (directory, next) = findNextDirectory(path, i)
            i = next

            when (directory) {
                "." -> {
                    // Do nothing
                }
                ".." -> {
                    if (stack.isNotEmpty()) {
                        stack.removeAt(stack.lastIndex)
                    }
                }
                else -> {
                    stack.add(directory)
                }
            }
        }

        val result = StringBuilder("/")
        result.append(stack.joinToString("/"))
        return result.toString()
    }

    fun findNextDirectory(path: String, start: Int): Pair<String, Int> {
        var i = start
        while (i < path.length && path[i] != '/') {
            i++
        }
        return path.substring(start, i) to i
    }
}
