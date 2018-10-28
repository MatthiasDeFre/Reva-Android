package com.example.beardwulf.reva.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.activities.registreren.Registreren
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var codes: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        codes = listOf(1,2)

        btnLogin.setOnClickListener {
            login()
        }
    }

    fun login() {

/*        val service = RetrofitClientInstance().getRetrofitInstance()!!.create(Endpoint::class.java!!)
        val call = service.getAllCodes()
        call.enqueue(object : Callback<List<Int>> {
            override fun onResponse(call: Call<List<Int>>, response: Response<List<Int>>) {
                codes = response.body()!!
            }

            override fun onFailure(call: Call<List<Int>>, t: Throwable) {

            }
        })*/

        val tekst: String
        if (txtInput.text.toString().equals("1234")
                /*codes.contains(txtInput.text.toString().toInt())*/) {
            tekst = getString(R.string.welkom)
            Toast.makeText(this@MainActivity, tekst, Toast.LENGTH_SHORT).show()
            val intent: Intent
            intent = Intent(this, Registreren::class.java)
            startActivity(intent)
        } else {
            tekst = "Code niet correct"
            Toast.makeText(this@MainActivity, tekst, Toast.LENGTH_SHORT).show()
        }
    }
}
