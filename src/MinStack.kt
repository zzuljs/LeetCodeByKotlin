/**
 * - [LeetCode第155题](https://leetcode.cn/problems/min-stack/)
 *
 * @since 2025-2-19 14:05:51
 * */
class MinStack() {

    private val data = ArrayList<Pair<Int,Int>>()

    private var min = Int.MAX_VALUE

    fun push(`val`: Int) {
        min = minOf(min,`val`)
        data.add(Pair(`val`,min))
    }

    fun pop() {
        if (data.isNotEmpty()) data.removeLast()
        min = if (data.isNotEmpty()) data.last().second else Int.MAX_VALUE
    }

    fun top(): Int {
        return data.last().first
    }

    fun getMin(): Int {
        return data.last().second
    }

}

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = MinStack()
 * obj.push(`val`)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */