package easy

import java.nio.channels.IllegalChannelGroupException

/**
 * At a lemonade stand, each lemonade costs $5. Customers are standing in a queue to buy from you and order one at a
 * time (in the order specified by bills). Each customer will only buy one lemonade and pay with either a $5, $10, or
 * $20 bill. You must provide the correct change to each customer so that the net transaction is that the customer pays
 * $5.
 *
 * Note that you do not have any change in hand at first.
 *
 * Given an integer array bills where bills[i] is the bill the ith customer pays, return true if you can provide every
 * customer with the correct change, or false otherwise.
 *w
 * Constraints:
 * - 1 <= bills.length <= 10^5
 * - bills[i] is either 5, 10, or 20.
 *
 * Example 1:
 * Input: bills = [5,5,5,10,20]
 * Output: true
 * Explanation:
 * From the first 3 customers, we collect three $5 bills in order.
 * From the fourth customer, we collect a $10 bill and give back a $5.
 * From the fifth customer, we give a $10 bill and a $5 bill.
 * Since all customers got correct change, we output true.
 *
 * Example 2:
 * Input: bills = [5,5,10,10,20]
 * Output: false
 * Explanation:
 * From the first two customers in order, we collect two $5 bills.
 * For the next two customers in order, we collect a $10 bill and give back a $5 bill.
 * For the last customer, we can not give the change of $15 back because we only have two $10 bills.
 * Since not every customer received the correct change, the answer is false.
 */
fun main() {
    fun lemonadeChange(bills: IntArray): Boolean {
        val remainingBills = mutableMapOf(5 to 0, 10 to 0, 20 to 0)

        for (bill in bills) {
            remainingBills[bill] = remainingBills.getOrDefault(bill, 0) + 1

            when (bill) {
                5 -> continue

                10 -> {
                    remainingBills.getOrDefault(5, 0).let {
                        if (it == 0) return false
                        remainingBills[5] = it - 1
                    }
                }

                20 -> {
                    if (remainingBills.getOrDefault(10, 0) > 0 && remainingBills.getOrDefault(5, 0) > 0) {
                        remainingBills[10] = remainingBills[10]!! - 1
                        remainingBills[5] = remainingBills[5]!! - 1
                    } else if (remainingBills.getOrDefault(5, 0) > 2) {
                        remainingBills[5] = remainingBills[5]!! - 3
                    } else {
                        return false
                    }
                }

                else -> throw IllegalStateException("Invalid bill amount: $bill")
            }
        }

        return true
    }

    println(lemonadeChange(intArrayOf(5, 5, 5, 10, 20))) // true
    println(lemonadeChange(intArrayOf(5, 5, 10, 10, 20))) // false
}

