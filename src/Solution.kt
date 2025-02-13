import java.math.BigInteger
import java.util.*
import kotlin.math.abs
import kotlin.math.max

class Solution {

    fun maxProfit(prices: IntArray): Int {
        var cost = Int.MAX_VALUE
        var profit = 0
        for (price in prices) {
            cost = cost.coerceAtMost(price)
            profit = profit.coerceAtLeast(price - cost)
        }

        return profit
    }


    fun canJump(nums: IntArray): Boolean {
        var step = nums[0]
        var index = 0
        while (index < nums.size && index <= step) {
            step = step.coerceAtLeast(index + nums[index])
            if (step >= nums.lastIndex) {
                return true
            }
            index++
        }
        return false
    }


    fun jump(nums: IntArray): Int {
        var end = 0
        var maxPos = 0
        var steps = 0
        for (i in 0..<nums.size - 1) {
            maxPos = max(maxPos.toDouble(), (i + nums[i]).toDouble()).toInt()
            if (i == end) {
                end = maxPos
                steps++
            }
        }

        return steps
    }


//    fun productExceptSelf(nums: IntArray): IntArray {
//        val answer = IntArray(nums.size)
//        val left = IntArray(nums.size)
//        val right = IntArray(nums.size)
//
//        left[0] = 1
//        for (index in 1..<nums.size){
//            left[index] = left[index-1] * nums[index-1]
//        }
//
//        right[nums.size-1] = 1
//        for (index in nums.size-2 downTo  0){
//            right[index] = right[index+1]*nums[index+1]
//        }
//
//        for (index in answer.indices){
//            answer[index] = left[index]*right[index]
//        }
//
//        return answer
//    }

    fun productExceptSelf(nums: IntArray): IntArray {
        val answer = IntArray(nums.size)
        val left = IntArray(nums.size)
        var right = 1

        left[0] = 1
        for (index in 1..<nums.size) {
            left[index] = left[index - 1] * nums[index - 1]
        }

        answer[nums.size - 1] = left[nums.size - 1]
        for (index in nums.size - 2 downTo 0) {
            right *= nums[index + 1]
            answer[index] = right * left[index]
        }

        return answer
    }


    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        var minDiff = Int.MAX_VALUE
        var minIndex = 0
        var sumCost = 0
        for (index in gas.indices) {
            val diff = gas[index] - cost[index]
            sumCost += diff
            if (sumCost < minDiff) {
                minIndex = index
                minDiff = sumCost
            }
        }

        return if (sumCost >= 0) (minIndex + 1) % gas.size else -1
    }


//    fun isSubsequence(s: String, t: String): Boolean {
//        var allSame = true
//        var current = 0
//        for (subChar in s){
//            var same = false
//            while (current<t.length){
//                if (subChar == t[current]){
//                    same = true
//                    current++
//                    break
//                }
//                current++
//            }
//
//            if (!same){
//                allSame = false
//                break
//            }
//        }
//
//        return allSame
//    }

    fun isSubsequence(s: String, t: String): Boolean {
        var sIndex = 0
        var tIndex = 0
        while (sIndex < s.length && tIndex < t.length) {
            if (s[sIndex] == t[tIndex]) {
                tIndex++
            }
            tIndex++
        }

        return sIndex == s.length
    }


    fun reverseWords(s: String): String {
        val strList = ArrayList<String>()
        var word = StringBuilder()
        for (index in s.indices) {
            //println("index = $index,s = ${s[index]},word = $word")
            if (s[index] != ' ') {
                word.append(s[index])
            } else {
                if (word.isNotEmpty()) {
                    strList.add(word.toString())
                }
                word = StringBuilder()
            }
        }

        if (word.isNotEmpty()) strList.add(word.toString())

        //println("strList  =${strList.joinToString()}")

        return strList.reversed().joinToString(" ")
    }

    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val noteMap = HashMap<Char, Int>()
        val magaMap = HashMap<Char, Int>()
        for (c in ransomNote) {
            val times = noteMap[c] ?: 0
            noteMap[c] = times + 1
        }

        for (z in magazine) {
            val times = magaMap[z] ?: 0
            magaMap[z] = times + 1
        }

        var res = true
        for ((k, v) in noteMap) {
            if (magaMap.containsKey(k)) {
                val times = magaMap[k] ?: -1
                if (times < v) {
                    res = false
                    break
                }
            } else {
                res = false
                break
            }
        }

        return res
    }


    fun isIsomorphic(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val s2t = HashMap<Char, Char>()
        val t2s = HashMap<Char, Char>()
        for (index in s.indices) {
            if (!s2t.containsKey(s[index])) {
                s2t[s[index]] = t[index]
                t2s[t[index]] = s[index]
            } else {
                val mapChar = s2t[s[index]]
                if (mapChar != t[index]) return false
            }
        }

        val isomorphic = StringBuilder()
        for (index in t.indices) {
            if (!t2s.containsKey(t[index])) return false
            isomorphic.append(t2s[t[index]])
        }

        println("isomorphic = $isomorphic")

        return s == isomorphic.toString()
    }


    fun plusOne(digits: IntArray): IntArray {
        val res = ArrayList<Int>()
        var extra = 1

        for (digit in digits.reversed()) {
            val sum = digit + extra
            res.add(sum % 10)
            extra = if (sum >= 10) 1 else 0
        }

        if (extra != 0) res.add(extra)

        return res.reversed().toIntArray()
    }


//    fun twoSum(numbers: IntArray, target: Int): IntArray {
//        val res = intArrayOf(1,2)
//        while (res[0]<=numbers.size&&res[1]<=numbers.size&&res[0]<res[1]){
//            while (res[1]<=numbers.size){
//                if (numbers[res[0]-1]+numbers[res[1]-1]==target) return res
//                res[1]++
//            }
//            res[0]++
//            res[1] = res[0]+1
//        }
//
//        return res
//    }

    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var low = 0
        var high = numbers.size - 1
        while (low < high) {
            val sum = numbers[low] + numbers[high]
            if (sum == target) return intArrayOf(low + 1, high + 1)
            else if (sum > target) high--
            else low++
        }

        return intArrayOf(low + 1, high + 1)
    }


    fun maxArea(height: IntArray): Int {
        var low = 0
        var high = height.size - 1
        var maxArea = -1
        while (low < high) {
            val area = (high - low) * height[low].coerceAtMost(height[high])
            if (maxArea < area) maxArea = area
            if (height[low] < height[high]) low++
            else high--
        }

        return maxArea
    }


    fun threeSum(nums: IntArray): List<List<Int>> {
        val res = ArrayList<ArrayList<Int>>()
        if (nums.size < 3) return res

        Arrays.sort(nums)
        for (target in nums.indices) {
            if (nums[target] > 0) return res
            else if (target > 0 && nums[target] == nums[target - 1]) continue
            var left = target + 1
            var right = nums.size - 1
            while (left < right) {
                if (nums[target] + nums[left] + nums[right] == 0) {
                    res.add(arrayListOf(nums[target], nums[left], nums[right]))
                    while (left < right && nums[left] == nums[left + 1]) left++
                    while (left < right && nums[right] == nums[right - 1]) right--
                    left++
                    right--
                } else if (nums[target] + nums[left] + nums[right] < 0) {
                    left++
                } else {
                    right--
                }
            }
        }

        return res
    }

    fun wordPattern(pattern: String, s: String): Boolean {
        val p2w = HashMap<Char, String>()
        val w2p = HashMap<String, Char>()
        val word = s.split(" ")
        if (pattern.length != word.size) return false
        for (index in pattern.indices) {
            if (!p2w.containsKey(pattern[index])) {
                p2w[pattern[index]] = word[index]
                w2p[word[index]] = pattern[index]
            } else {
                val p = p2w[pattern[index]]!!
                if (p != word[index]) {
                    return false
                }
            }
        }

        for (index in word.indices) {
            val w = w2p[word[index]] ?: ""
            if (w != pattern[index]) return false
        }

        return true
    }


    fun twoSum1(nums: IntArray, target: Int): IntArray {
        val map = HashMap<Int, Int>()

        for (index in nums.indices) {
            if (map.containsKey(target - nums[index]))
                return intArrayOf(map[target - nums[index]]!!, index)
            else map[nums[index]] = index
        }

        throw IllegalArgumentException()
    }


    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val alpha = IntArray(26)
        for (index in s.indices) {
            alpha[s[index] - 'a']++
            alpha[t[index] - 'a']--
        }

        for (index in alpha.indices) {
            if (alpha[index] != 0) return false
        }

        return true

    }


    /**
     * 解题思路：把每个字符串进行排序，这样异构字符创就拥有一个共同的key
     * */
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val map = HashMap<String, ArrayList<String>>()
        for (str in strs) {
            val charArray = str.toCharArray()
            Arrays.sort(charArray)
            val key = charArray.joinToString("")
            //println("key = $key")
            if (map.containsKey(key)) {
                map[key]!!.add(str)
            } else {
                map[key] = arrayListOf(str)
            }
        }

        //println("map.size = ${map.size}")

        val res = ArrayList<ArrayList<String>>()
        for ((_, v) in map) {
            res.add(v)
        }

        return res
    }


    fun isHappy(n: Int): Boolean {
        var fast = n
        var slow = n
        do {
            fast = getNext(getNext(fast))
            slow = getNext(slow)
            //println("fast = $fast, slow = $slow")
        } while (fast != slow && fast != 1)


        return fast == 1
    }

    private fun getNext(number: Int): Int {
        var res = 0
        var n = number
        do {
            res += (n % 10) * (n % 10)
            n /= 10
        } while (n > 0)

        //println("getNext.res = $res")
        return res
    }


    fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        val map = HashMap<Int, Int>()

        for (index in nums.indices) {
            if (map.containsKey(nums[index])
                && abs(index - map[nums[index]]!!) < k
            )
                return true
            map[nums[index]] = index
        }

        return false
    }


    fun longestConsecutive(nums: IntArray): Int {
        var max = 0
        val set = mutableSetOf<Int>()
        for (n in nums) set.add(n)
        for (num in set) {
            if (!set.contains(num - 1)) {
                var currentNum = num
                var currentConsecutive = 1
                while (set.contains(currentNum + 1)) {
                    currentNum++
                    currentConsecutive++
                }
                max = max.coerceAtLeast(currentConsecutive)
            }
        }

        return max
    }


    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val merge = intervals.toMutableList()
        merge.add(newInterval)
        merge.sortBy { it[0] }
        val res = arrayListOf<IntArray>()

        var index = 0
        while (index < merge.size) {
            var start = merge[index][0]
            var end = merge[index][1]
            while (index + 1 < merge.size
                && end >= merge[index + 1][0]
            ) {
                index++
                end = end.coerceAtLeast(merge[index][1])
            }
            index++
            res.add(intArrayOf(start, end))
        }

        return res.toTypedArray()

    }

    /**
     * - [LeetCode第452题](https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/description/)
     * @since 2025-2-11
     * */
    fun findMinArrowShots(points: Array<IntArray>): Int {
        points.sortBy { it[0] }
        var index = 0
        val res = ArrayList<IntArray>()
        while (index < points.size) {
            var start = points[index][0]
            var end = points[index][1]
            while (index + 1 < points.size
                && end >= points[index + 1][0]
            ) {
                start = start.coerceAtLeast(points[index + 1][0])
                end = end.coerceAtMost(points[index + 1][1])
                index++
            }
            res.add(intArrayOf(start, end))
            index++
        }

        return res.size
    }


    /**
     * - [LeetCode第141题](https://leetcode.cn/problems/linked-list-cycle/)
     * @since 2025-2-11
     * */
    fun hasCycle(head: ListNode?): Boolean {
        var slow: ListNode? = head
        var fast: ListNode? = head?.next
        var current = head
        while (slow != null && fast != null && fast != slow) {
            slow = slow.next
            fast = fast.next?.next
        }

        return slow == fast
    }

//    用下面的类，构建一个链表，要求数据如下顺序 [1,2,3,3,4,4,5]

    fun createLinkedList(values: List<Int>): ListNode? {
        if (values.isEmpty()) return null

        val head = ListNode(values[0])  // 创建头节点
        var current = head

        for (i in 1..<values.size) {
            current.next = ListNode(values[i])  // 连接下一个节点
            current = current.next!!
        }

        return head
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }


    /**
     * - [LeetCode第2题](https://leetcode.cn/problems/add-two-numbers/)
     * @since 2025-2-11 12:02:47
     * */
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var current: ListNode? = ListNode(0)
        val res = current
        var pre = current
        var carry = 0
        var list1 = l1
        var list2 = l2
        while (list1 != null || list2 != null) {
            val x = list1?.`val` ?: 0
            val y = list2?.`val` ?: 0
            val sum = x + y + carry

            carry = sum / 10
            current?.`val` = sum % 10
            current?.next = ListNode(0)
            pre = current
            current = current?.next

            list1 = list1?.next
            list2 = list2?.next
        }

        if (carry == 1) {
            current?.`val` = carry
        } else {
            pre?.next = null
        }

        return res
    }

    /**
     * - [LeetCode第21题](https://leetcode.cn/problems/merge-two-sorted-lists/)
     *
     * @since 2025-2-11 12:24:15
     * */
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        if (list1 == null) return list2
        if (list2 == null) return list1
        var res: ListNode? = ListNode(0)
        val head = res
        var pre = res
        var l1: ListNode? = list1
        var l2: ListNode? = list2
        while (l1 != null && l2 != null) {
            val x = l1?.`val` ?: Int.MAX_VALUE
            val y = l2?.`val` ?: Int.MAX_VALUE
            if (x > y) {
                res?.`val` = y
                l2 = l2?.next
            } else {
                res?.`val` = x
                l1 = l1?.next
            }
            res?.next = ListNode(0)
            pre = res
            res = res?.next
        }

        pre?.next = null

        return head
    }

    /**
     * - [LeetCode第9题](https://leetcode.cn/problems/palindrome-number/)
     * @since 2025-2-11 14:52:10
     * */
    fun isPalindrome(x: Int): Boolean {
        if (x < 10) return true
        val str = x.toString()
        var left = 0
        var right = str.length - 1
        while (left < right) {
            if (str[left] != str[right]) return false
            left++
            right--
        }
        return true

    }


    /**
     * - [LeetCode第69题](https://leetcode.cn/problems/sqrtx/)
     * @since 2025-2-11 15:08:08
     * */
    fun mySqrt(x: Int): Int {
        if (x < 2) return x
        var index: Long = 1
        while (index * index <= x) index++
        return (index - 1).toInt()
    }

    /**
     * - [LeetCode第172题](https://leetcode.cn/problems/factorial-trailing-zeroes/)
     * @since 2025-2-11 15:22:00
     * */
    fun trailingZeroes(n: Int): Int {
        var sum = BigInteger.ONE
        var count = 0
        for (index in 1..n) {
            sum = sum.multiply(BigInteger.valueOf(index.toLong()))
        }

        //println("str = $sum")

        val str = sum.toString()
        for (s in str.reversed()) {
            if (s == '0') count++
            else break
        }

        return count
    }


    /**
     * - [LeetCode第138题](https://leetcode.cn/problems/copy-list-with-random-pointer/)
     * - 解题思路：输入的是顺序表，无法做到随机读取，那么需要先把输入的顺序表转化蹭随机表，然后记录每一个random指向，记录到个map中，kv均是索引
     * 在生成新的Node链表时，先用顺序表生成，然后根据Map记录的映射关系，来构建random指向
     * @since 2025-2-12 10:11:53
     * */
    fun copyRandomList(node: Node?): Node? {
        if (node == null) return null
        val sourceNodeArrayList = ArrayList<Node?>()
        val originNodeArrayList = ArrayList<Node?>()
        val randomMap = HashMap<Int, Int>()
        var sourceList = node
        while (sourceList != null) {
            sourceNodeArrayList.add(sourceList)
            sourceList = sourceList.next
        }

        for (index in sourceNodeArrayList.indices) {
            val randomNode = sourceNodeArrayList[index]?.random
            val randomIndex = sourceNodeArrayList.indexOf(randomNode)
            randomMap[index] = randomIndex
        }

        var current: Node? = Node(0)
        var pre = current
        val head = current
        for (n in sourceNodeArrayList) {
            current?.apply {
                `val` = n!!.`val`
                next = Node(0)
            }
            originNodeArrayList.add(current)
            pre = current
            current = current?.next
        }

        pre?.next = null

        for (index in originNodeArrayList.indices) {
            val randomIndex = randomMap[index] ?: -1
            originNodeArrayList[index]?.random = if (randomIndex == -1) null else originNodeArrayList[randomIndex]
        }

        return head
    }

    fun copyRandomListFix(node: Node?): Node? {
        if (node == null) return null
        val nodeMap = HashMap<Node, Node>()
        var current = node
        while (current != null) {
            nodeMap[current] = Node(current.`val`)
            current = current.next
        }

        for ((k, v) in nodeMap) {
            v.random = if (k.random == null) null else nodeMap[k.random]
            v.next = if (k.next == null) null else nodeMap[k.next]
        }

        return nodeMap[node]
    }


    class Node(var `val`: Int) {
        var next: Node? = null
        var random: Node? = null
    }


    /**
     * - [LeetCode第92题](https://leetcode.cn/problems/reverse-linked-list-ii/)
     * @since 2025-2-12 11:49:35
     * */
    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        val reTemp = IntArray(right - left + 1)
        var index = 1
        var current = head
        var leftNode: ListNode? = null
        while (current != null) {
            when {
                index in left..right -> {
                    if (index == left) leftNode = current
                    reTemp[index - left] = current.`val`
                }

                index > right -> break
            }
            current = current.next
            index++
        }

        for (i in reTemp.reversed()) {
            leftNode?.`val` = i
            leftNode = leftNode?.next
        }

        return head
    }


    /**
     * - [LeetCode第25题](https://leetcode.cn/problems/reverse-nodes-in-k-group/)
     * - 解题思路：先统计链表长度，对于length<k的情况，特判返回head，然后从0开始进行链表反转即可
     * @since 2025-2-12 12:01:53
     * */
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        var length = 0
        var current = head
        var pre: ListNode? = null
        var finallyHead: ListNode? = null
        while (current != null) {
            length++
            current = current.next
        }
        if (length < k) return head

        // 开始进行反转
        val remain = length % k
        current = head
        var index = 0
        while (index < length - remain) {
            index += k
            val reHead = reverseKListNode(current, k)
            if (finallyHead == null) finallyHead = reHead
            pre?.next = reHead
            pre = getNextKNode(reHead, k)
            current = pre?.next
        }

        return finallyHead
    }

    private fun getNextKNode(head: ListNode?, k: Int): ListNode? {
        var current = head
        var index = 0
        while (index < k - 1) {
            current = current?.next
            index++
        }

        return current
    }


    /**
     * 从第 head个节点开始反转之后的k个节点
     * @return 返回反转后的头结点
     * */
    private fun reverseKListNode(head: ListNode?, k: Int): ListNode? {
        var pre = head
        var current = head?.next
        var index = 0
        var next: ListNode? = null
        while (index < k - 1) {
            next = current?.next
            current?.next = pre
            pre = current
            current = next
            index++
        }

        head?.next = current
        return pre
    }


    /**
     * - [LeetCode第19题](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/)
     * @since 2025-2-12 15:09:46
     * */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var length = 0
        var index = 0
        var current: ListNode? = head
        while (current != null) {
            current = current.next
            length++
        }
        if (length == 1 && n == 1) return null
        if (n == length) return head?.next
        current = head
        var pre: ListNode? = null
        var next = head?.next
        while (index < length - n) {
            pre = current
            current = current?.next
            next = current?.next
            index++
        }
        pre?.next = next
        return head
    }


    /**
     * - [LeetCode第82题](https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/)
     * @since 2025-2-12 15:27:29
     * */
    fun deleteDuplicates(head: ListNode?): ListNode? {
        if (head?.next == null) return head
        val map = HashMap<Int, Int>()
        val list = ArrayList<ListNode?>()
        var current = head
        while (current != null) {
            val value = map[current.`val`] ?: 0
            map[current.`val`] = value + 1
            list.add(current)
            current = current.next

        }

        list.removeAll {
            val key = it!!.`val`
            val value = map[key] ?: 0
//            println("key = $key,value = $value")
            return@removeAll value > 1
        }

        for (node in list) {
            println("val = ${node!!.`val`}")
        }

        if (list.isEmpty()) return null

        current = list.first()
        val finalHead: ListNode? = current

        for (index in 1..<list.size) {
            current!!.next = list[index]
            current = list[index]
        }
        current?.next = null

        printListNode(finalHead)

        return finalHead
    }


    private fun printListNode(head: ListNode?) {
        var current = head
        while (current != null) {
            println("printListNode,value = ${current.`val`}")
            current = current.next
        }
    }


    /**
     * - [LeetCode第61题](https://leetcode.cn/problems/rotate-list/)
     * - 解题思路：对k取膜length得到r，先反转[0,r)，再反转(r+1,length),最后反转[0,length)
     * @since 2025-2-12 21:25:50
     * */
    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        var length = 0
        var current = head

        while (current != null) {
            length++
            current = current.next
        }

        // 特判，以下情况不需要旋转
        if (length == 0 || k == 0 || length == 1 || k % length == 0) return head

        val rotate = length - k % length
        var rotateHead = reverseListNode(head, 0, rotate)
        rotateHead = reverseListNode(rotateHead, rotate, length)
        rotateHead = reverseListNode(rotateHead, 0, length)

        return rotateHead
    }


    /**
     * @return 返回旋转后的头结点
     * */
    private fun reverseListNode(head: ListNode?, start: Int, end: Int): ListNode? {

        var index = 0
        var currentNode: ListNode? = head
        var preStartNode: ListNode? = null
        var finalHead: ListNode? = null

        // 先找到start位置以及前一个节点
        while (index < start) {
//            println("currentNode = ${currentNode?.`val`}")
            preStartNode = currentNode
            currentNode = currentNode?.next
            index++
        }

        // 开始旋转 start到end之间的节点
        val rotateHead = currentNode
        var nextNode: ListNode? = null
        var preNode = preStartNode
        index = 0
        while (index < end - start) {
            //println("index = $index,current = ${currentNode?.`val`},next = ${currentNode?.next?.`val`},preNode = ${preNode?.`val`}")
            nextNode = currentNode?.next
            currentNode?.next = preNode
            preNode = currentNode
            currentNode = nextNode
            index++
        }

        // 特判：如果是从头开始反转
        finalHead = if (preStartNode == null) {
            preNode
        } else {
            head
        }
        preStartNode?.next = preNode
        rotateHead?.next = currentNode

//        println("start = $start,end = $end")
//        printListNode(finalHead)
        return finalHead
    }


    /**
     * - [LeetCode第86题](https://leetcode.cn/problems/partition-list/)
     *
     * @since 2025-2-13 09:40:26
     * */
    fun partition(head: ListNode?, x: Int): ListNode? {
        var left: ListNode? = ListNode(0)
        var right: ListNode? = ListNode(0)
        var leftLoop: ListNode? = left
        var rightLoop: ListNode? = right
        var current: ListNode? = head
        var pre: ListNode? = null
        while (current != null) {
            if (current.`val` < x) {
                leftLoop?.next = current
                leftLoop = current
            } else {
                rightLoop?.next = current
                rightLoop = current
            }
            pre = current
            current = current.next
            pre.next = null
        }

        leftLoop?.next = right?.next
        return left?.next
    }


    /**
     * - [LeetCode第67题](https://leetcode.cn/problems/add-binary/)
     * @since 2025-2-13 10:56:21
     * */
    fun addBinary(a: String, b: String): String {
        val res = StringBuilder()
        var i = a.length - 1
        var j = b.length - 1
        var carry = 0
        while (i >= 0 || j >= 0 || carry != 0) {
            val numbA = if (i >= 0) (a[i] - '0') else 0
            val numbB = if (j >= 0) (b[j] - '0') else 0
            val sum = numbA + numbB + carry
            res.append(sum % 2)
            carry = sum / 2
            i--
            j--
        }

        return res.reverse().toString()
    }


    /**
     * - [LeetCode190题](https://leetcode.cn/problems/reverse-bits/)
     * @since 2025-2-13 11:35:56
     * */
    fun reverseBits(n: Int): Int {
        return Integer.reverse(n)
    }

    /**
     * - [LeetCode第191题](https://leetcode.cn/problems/number-of-1-bits/)
     * @since 2025-2-13 12:12:38
     * */
    fun hammingWeight(n: Int): Int {
        var numb = n
        var count = 0
        while (numb != 0) {
            count += numb and 1
            numb = numb ushr 1
        }

        return count
    }

    /**
     * - [LeetCode第135题](https://leetcode.cn/problems/candy/)
     * @since 2025-2-13 15:11:04
     * */
    fun candy(ratings: IntArray): Int {
        val left = IntArray(ratings.size) { 1 }
        val right = IntArray(ratings.size) { 1 }


        for (i in 1..<ratings.size) {
            if (ratings[i] > ratings[i - 1]) left[i] = left[i - 1] + 1
        }
        var sum = left.last()

        for (i in ratings.size - 2 downTo 0) {
            if (ratings[i] > ratings[i + 1]) right[i] = right[i + 1] + 1
            sum += left[i].coerceAtLeast(right[i])
        }
        return sum

    }
}



