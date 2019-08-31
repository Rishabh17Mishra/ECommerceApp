package com.example.ecommerceapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

@Suppress("DEPRECATION")
class AmountFragment : android.app.DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_amount, container, false)
        val edtEnterAmount = fragmentView.findViewById<EditText>(R.id.edtEnterAmount)
        val btnAddToCart = fragmentView.findViewById<ImageButton>(R.id.btnAddToCart)
        btnAddToCart.setOnClickListener {
            val ptoUrl = "http://192.168.0.104/ECommerceApp/insert_temporary_order.php?email=" +
                    "${Person.email}&product_id=${Person.addToCartProductID}&amount=${edtEnterAmount.text}"
            var requestQ = Volley.newRequestQueue(activity)
            var stringRequest = StringRequest(Request.Method.GET, ptoUrl, Response.Listener
            {
                response ->
                var intent = Intent(activity, CartProductsActivity::class.java)
                startActivity(intent)

            }, Response.ErrorListener { error ->  })
            requestQ.add(stringRequest)
        }
        return fragmentView
    }

}
