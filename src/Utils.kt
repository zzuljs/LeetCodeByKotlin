class Utils {
    val func = object:Function0<Int>{
        override fun invoke(): Int {
            return 0
        }
    }

    fun use(){
        println(func())
    }
}


