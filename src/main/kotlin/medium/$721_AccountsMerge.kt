package medium

/**
 * Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0]
 * is a name, and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common
 * email to both accounts. Note that even if two accounts have the same name, they may belong to different people as
 * people could have the same name. A person can have any number of accounts initially, but all of their accounts
 * definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the
 * name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 * Constraints:
 * - 1 <= accounts.length <= 1000
 * - 2 <= accounts[i].length <= 10
 * - 1 <= accounts[i][j] <= 30
 * - accounts[i][0] consists of English letters.
 * - accounts[i][j] (for j > 0) is a valid email.
 *
 * Example 1:
 * Input: accounts = [
 * ["John","johnsmith@mail.com","john_newyork@mail.com"],
 * ["John","johnsmith@mail.com","john00@mail.com"],
 * ["Mary","mary@mail.com"],
 * ["John","johnnybravo@mail.com"]
 * ]
 * Output: [
 * ["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],
 * ["Mary","mary@mail.com"],
 * ["John","johnnybravo@mail.com"]
 * ]
 * Explanation:
 * The first and second John's are the same person as they have the common email "johnsmith@mail.com".
 * The third John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 */
fun main() {
    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
        class UnionFind(n: Int) {
            private val parent = IntArray(n) { it }
            private val rank = IntArray(n) { 1 }

            fun find(x: Int): Int {
                var target = x
                while (target != parent[target]) {
                    parent[target] = parent[parent[target]]
                    target = parent[target]
                }

                return target
            }

            fun union(x: Int, y: Int): Boolean {
                val rootX = find(x)
                val rootY = find(y)

                // already in the same group
                if (rootX == rootY) return false

                // union by rank, attach the smaller to the larger
                // step 1. mark the smaller one's parent to the larger one
                // step 2. update the rank of the larger one by adding the smaller one's rank
                // - we don't need to update the rank of the smaller one because it's not the root and won't be used
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX
                    rank[rootX] += rank[rootY]
                } else {
                    parent[rootX] = rootY
                    rank[rootY] += rank[rootX]
                }

                return true
            }
        }

        val n = accounts.size
        val uf = UnionFind(n)
        val emailToAccount = mutableMapOf<String, Int>() // email to index of account

        for (i in accounts.indices) {
            val account = accounts[i]
            for (j in 1..<account.size) {
                val email = account[j]
                if (emailToAccount.containsKey(email)) {
                    uf.union(i, emailToAccount[email]!!)
                } else {
                    emailToAccount[email] = i
                }
            }
        }

        val indexToEmails = mutableMapOf<Int, MutableList<String>>()
        for ((email, index) in emailToAccount) {
            val rootIndex = uf.find(index)
            indexToEmails.getOrPut(rootIndex) { mutableListOf() }.add(email)
        }

        val result = mutableListOf<List<String>>()
        for ((index, emails) in indexToEmails) {
            val account = mutableListOf<String>()
            val name = accounts[index][0]
            account.add(name)
            account.addAll(emails.sorted())
            result.add(account)
        }

        return result
    }

    println(
        accountsMerge(
            listOf(
                listOf("John","johnsmith@mail.com","john_newyork@mail.com"),
                listOf("John","johnsmith@mail.com","john00@mail.com"),
                listOf("Mary","mary@mail.com"),
                listOf("John", "johnnybravo@mail.com"),
            )
        )
    )
}
