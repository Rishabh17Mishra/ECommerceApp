package com.example.ecommerceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val brandsUrl = "http://192.168.0.104/ECommerceApp/fetch_brands.php"
        val brandsList = ArrayList<String>()
        val requestQ = Volley.newRequestQueue(this@HomeScreen)
        val jsonAR = JsonArrayRequest(Request.Method.GET, brandsUrl, null, Response.Listener
        {
            response ->
            for (jsonObject in 0.until(response.length()) ) {
                brandsList.add(response.getJSONObject(jsonObject).getString("brand"))
            }
            val brandsListAdapter = ArrayAdapter(this@HomeScreen, R.layout.brand_item_text_view, brandsList)
            brandsListView.adapter = brandsListAdapter
        }, Response.ErrorListener { error ->
            val dialogBuilder= AlertDialog.Builder(this)
            dialogBuilder.setTitle("Message")
            dialogBuilder.setMessage(error.message)
            dialogBuilder.create().show()
        })
        requestQ.add(jsonAR)
        brandsListView.setOnItemClickListener { adapterView, view, i, l ->
            val tappedBrand = brandsList.get(i)
            val intent = Intent(this@HomeScreen, FetchEProductsActivity::class.java)
            intent.putExtra("BRAND", tappedBrand)
            startActivity(intent)
        }
    }
}
