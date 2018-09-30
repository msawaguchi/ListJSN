package testepontotel.sawastudio.me.listjsnteste.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import testepontotel.sawastudio.me.listjsnteste.R
import android.support.v7.widget.RecyclerView
import testepontotel.sawastudio.me.listjsnteste.adapter.ListaAdapter
import org.json.JSONException
import android.support.v7.widget.LinearLayoutManager
import testepontotel.sawastudio.me.listjsnteste.utils.Pessoa
import org.json.JSONObject

import android.widget.Toast
import android.os.AsyncTask
import android.support.v7.app.ActionBar
import testepontotel.sawastudio.me.listjsnteste.utils.NetworkUtils


class MainActivity : AppCompatActivity() {

    private var mRecyclerMovie: RecyclerView? = null
    private var mvListAdapter: ListaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar()?.setCustomView(R.layout.actionbar_custom);

        AsyncTaskMovies().execute()
    }

    //AsyncTask
    inner class AsyncTaskMovies : AsyncTask<String, String, String>() {

        override fun doInBackground(vararg strings: String): String? {
            var jsonRec = ""

            if (NetworkUtils.networkStatus(this@MainActivity)) {
                jsonRec = NetworkUtils.resgataJson()
                return jsonRec

            } else {
                Toast.makeText(this@MainActivity, "Conecte-se com a internet!", Toast.LENGTH_LONG).show()
            }
            return null
        }

        override fun onPostExecute(result: String) {

            val data = ArrayList<Pessoa>()

            try {
                val mainObject = JSONObject(result)
                val resArray = mainObject.getJSONArray("data") //Pega resultados do objeto
                for (i in 0 until resArray.length()) {

                    val jsonObject = resArray.getJSONObject(i)
                    val ps = Pessoa()

                    ps.id = jsonObject.getString("id")
                    ps.name = jsonObject.getString("name")
                    ps.pwd = jsonObject.getInt("pwd")

                    data.add(ps)
                }


                mRecyclerMovie = findViewById<RecyclerView>(R.id.list_view)
                mvListAdapter = ListaAdapter(data, this@MainActivity)
                mRecyclerMovie?.adapter = mvListAdapter
                mRecyclerMovie?.layoutManager = LinearLayoutManager(this@MainActivity)


            } catch (e: JSONException) {
                e.printStackTrace()

            }

        }
    }
}
