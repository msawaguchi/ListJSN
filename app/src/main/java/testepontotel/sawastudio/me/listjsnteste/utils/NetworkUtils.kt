package testepontotel.sawastudio.me.listjsnteste.utils

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException

object NetworkUtils {

    private val URL = "https://s3-sa-east-1.amazonaws.com/pontotel-docs/data.json"

    fun resgataJson(): String {
        var jsonResposta = ""

        val client = OkHttpClient()

        val mediaType = MediaType.parse("application/octet-stream")
        val body = RequestBody.create(mediaType, "{}")
        val request = Request.Builder()
                .url(URL)
                .get()
                .build()
        try {
            val response = client.newCall(request).execute()
            jsonResposta = response.body()!!.string().replace(" ".toRegex(), "").replace("\n".toRegex(), "")

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return jsonResposta
    }


    fun networkStatus(context: Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val net = manager.activeNetworkInfo

        return if (net != null && net.isConnected) {
            true
        } else false
    }
}
