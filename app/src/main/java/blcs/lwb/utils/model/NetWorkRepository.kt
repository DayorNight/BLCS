package blcs.lwb.utils.model

class NetWorkRepository {
    suspend fun getContent(page:Int): MutableList<String> {
        return mutableListOf("1","2","3","4")
    }
}