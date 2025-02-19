const val a = 100_0
const val b = 0b1111

var uInt:UInt = 0u

var str = "Hello world!"
    get() {
        field += "get"
        return field
    }
    set(value) {
        field = value+"Jason"
    }

var str2 = """
    1111111
    111111
    11111
    1111
    111
    11
    1
""".trimEnd(    )


val x:Int by lazy {
    0
}


fun callFunc(str:String):String{
    return "$str,callback\n"
}


fun callback(call:(String)->String):Unit{

}


infix fun Int.add(other:Int):Int{
    return  this+other
}


class Student/*(private var name:String)*/{

    var name:String? = null
    private var age:Int = 0

    init {

    }

    fun showName(){
        println("这里是成员函数：$name")
    }

    override fun toString(): String {
        return super.toString()
    }
//operator fun component1() = name
}

fun Student.showName() = "这里是扩展函数:${this.name}"


data class Data(val name:String?, val age:Int = 0){
    fun useMap(){
        val map = mapOf("A" to 1,"B" to 2)
        for ((key,value) in map){
            println("key = $key,value = $value")
        }
    }
}

fun add(a:Int,b:Int = 0,c:Int = 0):Int = a+b+c

fun jason(){

//    val a= 1
//    val b = a.add(1)
//    val funcVar:(String)->String = ::callFunc
//    println(funcVar("jason"))
//    println(str)
//    str = "Hi"
//    println(str)
//    println(str2)
//    println("add = ${add(a=1,2,4)}")

    println(Student().showName())


}


class MyException(s: String) :Exception(s){
}

fun main(){
//    val a = readlnOrNull()
//    val message = try {
//        if (a == "1") throw MyException("a equals 1")
//        else "success"
//    }catch (e:Exception){
//        e.message
//    }
//    println(message)


//    val strArray = arrayOf("eat", "tea", "tan", "ate", "nat", "bat")
//    println(Solution().groupAnagrams(strArray).joinToString())

//    println(Solution().isHappy(2))

//    println(Solution().longestConsecutive(intArrayOf(100,4,200,1,3,2))

//    val intervals = arrayOf(intArrayOf(1,3), intArrayOf(6,9))
//    val newInterval = intArrayOf(2,5)
//    var res = Solution().insert(intervals,newInterval)
//    for (e in res){
//        println(e.joinToString())
//    }

//    val points: Array<IntArray> = arrayOf(
//        intArrayOf(3, 9),
//        intArrayOf(7, 12),
//        intArrayOf(3, 8),
//        intArrayOf(6, 8),
//        intArrayOf(9, 10),
//        intArrayOf(2, 9),
//        intArrayOf(0, 9),
//        intArrayOf(3, 9),
//        intArrayOf(0, 6),
//        intArrayOf(2, 8)
//    )
//c
//    println(Solution().findMinArrowShots(points))

//    println(Solution().trailingZeroes(8425))
//    val list = Solution().createLinkedList(arrayListOf(1,4,3,2,5,2))
//    Solution().rotateRight(list,2)
//    Solution().partition(list,3)

//    println(Solution().addBinary("10","1"))

//    println(Solution().candy(intArrayOf(1,3,2,2,1)))

//    println(Solution().rangeBitwiseAnd(5,7))

//    println(Solution().intToRoman(1994))

//    println(Solution().convert("PAYPALISHIRING",3))

//    println(Solution().findPeakElement(intArrayOf(1,2,1,3,5,6,4)))

//    println(Solution().wordBreak("leetcode", arrayListOf("leet","code")))

//    println(Solution().coinChange(intArrayOf(186,419,83,408),6249))

    println(Solution().isValid("()[]{}"))

}