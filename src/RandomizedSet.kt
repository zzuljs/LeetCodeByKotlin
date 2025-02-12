import com.sun.org.apache.xpath.internal.operations.Bool


class RandomizedSet() {

    private val indexMap:HashMap<Int,Int> = hashMapOf()

    private val data:ArrayList<Int> = arrayListOf()

    private val random = java.util.Random()

    private var sum = 0

//    fun insert(element: Int): Boolean {
//        if (!indexMap.containsKey(element)){
//            indexMap[element] = data.size
//            data.add(element)
//            return true
//        }
//        return false
//    }

    fun insert(element: Int):Boolean{
        if (!indexMap.containsKey(element)){
            indexMap[sum++] = element
            return true
        }


        return false
    }

//    fun remove(element: Int): Boolean {
//        if (indexMap.containsKey(element)){
//            val index = indexMap[element]
//            val last = data.size-1
//            if (index!=null){
//                data[index] = data[last]
//                indexMap[data[last]] = index
//                indexMap.remove(element)
//                data.removeAt(last)
//            }
//            return true
//        }
//        return false
//    }

    fun remove(element:Int):Boolean{
        if (indexMap.containsKey(element)){
            indexMap.remove(element)
            sum--
        }
        return false
    }

}