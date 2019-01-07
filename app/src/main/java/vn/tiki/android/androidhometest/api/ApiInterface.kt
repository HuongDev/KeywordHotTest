package vn.tiki.android.androidhometest.api

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by HuongPN on 01/07/2019.
 */
interface ApiInterface {

    @GET("talenguyen/38b790795722e7d7b1b5db051c5786e5/raw/63380022f5f0c9a100f51a1e30887ca494c3326e/keywords.json")
    fun getKeywords(): Call<List<String>>
}