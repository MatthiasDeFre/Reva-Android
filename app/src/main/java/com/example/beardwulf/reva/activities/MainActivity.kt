package com.example.beardwulf.reva.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.beardwulf.reva.Endpoint
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.RetrofitClientInstance
import com.example.beardwulf.reva.activities.registreren.Registreren
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    //lateinit var codes: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //codes = listOf(1,2)

        btnLogin.setOnClickListener {
            login()
        }

        btnVulCodeIn.setOnClickListener {
            txtInput.setText("qsdfd")
        }
    }

    fun login() {
        val code = "qsdfd"

        val service = RetrofitClientInstance().getRetrofitInstance()!!.create(Endpoint::class.java!!)
        val call = service.getGroup(code)
        call.enqueue(object : Callback<Objects> {
            override fun onResponse(call: Call<Objects>, response: Response<Objects>) {
                Log.d("lolz", response.code().toString())
            }

            override fun onFailure(call: Call<Objects>, t: Throwable) {
                Log.d("lolzzzz", t.message)
            }
        })

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
