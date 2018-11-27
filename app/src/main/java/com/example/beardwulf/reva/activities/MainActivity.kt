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
import com.example.beardwulf.reva.domain.testApplicationClass
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

//        btnVulCodeIn.setOnClickListener {
  //          txtInput.setText("qsdfd")
    //    }
    }

    fun login() {
        //"qsdfd"
        //"5be19cecdf933a07fe06120c"

    if(InputRegex.controleerLettersCijfers(txtInput.text.toString())) {
        val service = RetrofitClientInstance().getRetrofitInstance()!!.create(Endpoint::class.java!!)
        val call = service.getGroup(txtInput.text.toString())
        call.enqueue(object : Callback<Group> {
            override fun onResponse(call: Call<Group>, response: Response<Group>) {
                if (response.code() == 200) {
                    var body = response.body()
                    var group = Group(body!!._id, body!!.teacherId, body!!.name, body!!.code, body!!.imageString, body!!.description, body!!.answers)
                    var test = applicationContext as testApplicationClass
                    test.group = group;
                    //Toast.makeText(this@MainActivity, "Welkom", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, Registreren::class.java))
                } else {
                    txtInput.setError("Code niet correct");
                }
            }

            override fun onFailure(call: Call<Group>, t: Throwable) {
                Log.d("Error", t.message)
            }
        })
        /*if (txtInput.text.toString().equals("1234")) {
            group = Group("5be19cecdf933a07fe06120c", null, null, null, null, null, null)
            Toast.makeText(this@MainActivity, "Welkom", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext, Registreren::class.java))
        } else {

        }*/
        }else{
        txtInput.setError("Voer enkel cijfers en letters in aub");
    }
    }

}
