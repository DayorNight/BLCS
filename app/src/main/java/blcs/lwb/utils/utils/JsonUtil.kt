package blcs.lwb.utils.utils

import android.content.Context
import org.json.JSONArray

object JsonUtil {
    /**
     * 获取json文件
     */
    fun getLocalJsonData(context: Context, fileName: String): JSONArray? {
        try {
            // Open the JSON file from the assets folder
            val inputStream = context.assets.open(fileName)
            // Read the file content into a string
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            // Parse the JSON string into a JSONObject
            return JSONArray(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}