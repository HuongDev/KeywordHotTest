package vn.tiki.android.androidhometest.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by HuongPN on 01/07/2019.
 */
class APIClient {

    companion object {

        val baseURL: String = "https://gist.githubusercontent.com/"
        var retofit: Retrofit? = null

        val client: Retrofit
            get() {

                if (retofit == null) {
                    retofit = Retrofit.Builder()
                            .baseUrl(baseURL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                }
                return retofit!!
            }
    }
}