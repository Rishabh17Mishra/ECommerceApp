package com.example.ecommerceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_signup.*

class Signup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        sign_up_layout_btnSignUp.setOnClickListener {
            if (sign_up_layout_edtPassword.text.toString() == sign_up_layout_edtConfirmPassword.text.toString()) {
                //Registration Process
                val signUpURL = "http://192.168.0.104/ECommerceApp/join_new_user.php?email=" + sign_up_layout_edtEmail.text.toString() +
                        "&username" + sign_up_layout_edtUsername.text.toString() + "&password" + sign_up_layout_edtPassword.text.toString()

                val requestQ = Volley.newRequestQueue(this@Signup)
                val stringRequest = StringRequest(Request.Method.GET, signUpURL, Response.Listener {
                    response -> if (response == "This email address already exists")
                {
                    val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Message")
                    dialogBuilder.setMessage(response)
                    dialogBuilder.create().show()
                } else {
                    Person.email = sign_up_layout_edtEmail.text.toString()
                    Toast.makeText(this@Signup, response, Toast.LENGTH_SHORT).show()
                    val homeIntent = Intent(this@Signup, HomeScreen::class.java)
                    startActivity(homeIntent)
                }
                }, Response.ErrorListener { error ->
                    val dialogBuilder= AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Message")
                    dialogBuilder.setMessage(error.message)
                    dialogBuilder.create().show()
                })
                requestQ.add(stringRequest)
            } else{
                val dialogBuilder= AlertDialog.Builder(this)
                dialogBuilder.setTitle("Message")
                dialogBuilder.setMessage("Password Mismatch")
                dialogBuilder.create().show()
            }
        }
        sign_up_layout_btnLogin.setOnClickListener {
            finish() }
    }
}