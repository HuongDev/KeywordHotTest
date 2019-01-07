package vn.tiki.android.androidhometest

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.tiki.android.androidhometest.api.APIClient
import vn.tiki.android.androidhometest.api.ApiInterface
import vn.tiki.android.androidhometest.data.api.ApiServices
import vn.tiki.android.androidhometest.data.api.response.Deal
import vn.tiki.android.androidhometest.di.initDependencies
import vn.tiki.android.androidhometest.di.inject
import vn.tiki.android.androidhometest.di.releaseDependencies

class MainActivity : AppCompatActivity() {

  val apiServices by inject<ApiServices>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initDependencies(this)

    setContentView(R.layout.activity_main)

    object : AsyncTask<Unit, Unit, List<Deal>>() {
      override fun doInBackground(vararg params: Unit?): List<Deal> {
        return apiServices.getDeals()
      }

      override fun onPostExecute(result: List<Deal>?) {
        super.onPostExecute(result)
        result.orEmpty()
            .forEach { deal ->
              println(deal)
            }
      }
    }.execute()

    // set up the RecyclerView
    rcvKeyword.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    // Call api and set data
    callApi();
  }

  private fun callApi() {
    var apiServices = APIClient.client.create(ApiInterface::class.java)

    val call = apiServices.getKeywords()

    call.enqueue(object : Callback<List<String>> {
      override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {

        var listOfKeyword: List<String> = response.body()!!
        val adapter = MainAdapter(applicationContext, listOfKeyword)
        rcvKeyword.adapter = adapter
      }

      override fun onFailure(call: Call<List<String>>?, t: Throwable?) {
        Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_SHORT).show()
      }
    })
  }

  override fun onDestroy() {
    super.onDestroy()
    releaseDependencies()
  }
}
