
/**
 * - [LeetCode第146题](https://leetcode.cn/problems/lru-cache/)
 * @since 2025-2-13 10:36:13
 * */
class LRUCache(private val capacity: Int) : LinkedHashMap<Int, Int>(capacity, 0.75f, true) {

    override fun get(key: Int): Int {
        return super.getOrDefault(key, -1)
    }

    override fun put(key: Int, value: Int): Int? {
        return super.put(key, value)
    }

    override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Int, Int>?): Boolean {
        return size > capacity
    }
}
