package medium

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array
 * prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course ai first if you want to take
 * course bi.
 * - For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
 *
 * Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of
 * course c, then course a is a prerequisite of course c.
 *
 * You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether
 * course uj is a prerequisite of course vj or not.
 *
 * Return a boolean array answer, where answer[j] is the answer to the jth query.
 *
 * Constraints:
 * - 2 <= numCourses <= 100
 * - 0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
 * - prerequisites[i].length == 2
 * - 0 <= ai, bi < numCourses
 * - ai != bi
 * - All the pairs [ai, bi] are distinct.
 * - The prerequisites graph has no cycles.
 * - 1 <= queries.length <= 10^4
 * - 0 <= uj, vj <= numCourses - 1
 * - uj != vj
 *
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1, 0]], queries = [[0, 1], [1, 0]]
 * Output: [false, true]
 * Explanation: The pair [1, 0] indicates that you have to take course 1 before you can take course 0.
 * Therefore, course 0 is not a prerequisite of course 1, but the opposite is true.
 *
 * Example 2:
 * Input: numCourses = 2, prerequisites = [], queries = [[1, 0], [0, 1]]
 * Output: [false, false]
 * Explanation: There are no prerequisites and each course is independent.
 *
 * Example 3:
 * Input: numCourses = 3, prerequisites = [[1, 2], [1, 0], [2, 0]], queries = [[1, 0], [1, 2]]
 * Output: [true, true]
 */
fun main() {
    fun checkIfPrerequisite(numCourses: Int, prerequisites: Array<IntArray>, queries: Array<IntArray>): List<Boolean> {
        val prerequisiteMap = mutableMapOf<Int, MutableSet<Int>>()
        repeat(numCourses) { course ->
            prerequisiteMap[course] = mutableSetOf()
        }
        prerequisites.forEach { (prerequisite, course) ->
            prerequisiteMap[course]!!.add(prerequisite)
        }

        val dp = mutableMapOf<Int, MutableSet<Int>>()

        fun dfs(course: Int): MutableSet<Int> {
            if (course in dp) return dp[course]!!
            if (prerequisiteMap[course]!!.isEmpty()) {
                dp[course] = mutableSetOf()
                return dp[course]!!
            }

            val directAndIndirectPrerequisites = mutableSetOf<Int>()

            for (prerequisite in prerequisiteMap[course]!!) {
                val currDirectAndIndirectPrerequisites = dfs(prerequisite) + prerequisite
                directAndIndirectPrerequisites.addAll(currDirectAndIndirectPrerequisites)
            }

            dp[course] = directAndIndirectPrerequisites

            return dp[course]!!
        }

        repeat(numCourses) {
            dfs(it)
        }


        return queries.map { (prerequisite, course) ->
            prerequisite in dp[course]!!
        }
    }

    println(
        checkIfPrerequisite(
            2,
            arrayOf(intArrayOf(1, 0)),
            arrayOf(intArrayOf(0, 1), intArrayOf(1, 0))
        )
    ) // [false, true]
    println(
        checkIfPrerequisite(
            2,
            arrayOf(),
            arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))
        )
    ) // [false, false]
    println(
        checkIfPrerequisite(
            3,
            arrayOf(intArrayOf(1, 2), intArrayOf(1, 0), intArrayOf(2, 0)),
            arrayOf(intArrayOf(1, 0), intArrayOf(1, 2))
        )
    ) // [true, true]
}
