package com.example.beardwulf.reva.activities
import com.example.beardwulf.reva.extensions.InputRegex
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.beardwulf.reva.Endpoint
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.RetrofitClientInstance
import com.example.beardwulf.reva.activities.registreren.Registreren
import com.example.beardwulf.reva.domain.Group
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.activityManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.ref.WeakReference
import java.util.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            login()
        }

        btnVulCodeIn.setOnClickListener {
            txtInput.setText("qsdfd")
        }
    }

    fun login() {
        //"qsdfd"
        //"5be19cecdf933a07fe06120c"

    if(InputRegex.controleerLettersCijfers(txtInput.text.toString())) {
            if (txtInput.text.toString().equals("1234")) {
                group = Group("5be19cecdf933a07fe06120c", null, null, null, null, null, null)
                Toast.makeText(this@MainActivity, "Welkom", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, Registreren::class.java))
            } else {
                val service = RetrofitClientInstance().getRetrofitInstance()!!.create(Endpoint::class.java!!)
                val call = service.getGroup(txtInput.text.toString())
                call.enqueue(object : Callback<Group> {
                    override fun onResponse(call: Call<Group>, response: Response<Group>) {
                        if (response.code() == 200) {
                            var body = response.body()
                            group = Group(body!!._id, body!!.teacherId, body!!.name, body!!.code, body!!.imageString, body!!.description, body!!.answers)
                            Toast.makeText(this@MainActivity, "Welkom", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext, Registreren::class.java))
                        } else {
                            Toast.makeText(this@MainActivity, "Code niet correct", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Group>, t: Throwable) {
                        Log.d("Error", t.message)
                    }
                })
            }
        }else{
        Toast.makeText(this@MainActivity, "Voer enkel cijfers en letters in aub", Toast.LENGTH_SHORT).show()
    }
    }

    companion object {
        lateinit var group: Group
    }
}
