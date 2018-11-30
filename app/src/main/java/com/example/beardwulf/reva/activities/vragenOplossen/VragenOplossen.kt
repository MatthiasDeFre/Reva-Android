package com.example.beardwulf.reva.activities.vragenOplossen

import android.arch.lifecycle.ViewModelProviders
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.util.Log
import com.example.beardwulf.reva.Endpoint
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.RetrofitClientInstance
import com.example.beardwulf.reva.domain.*
import com.example.beardwulf.reva.fragments.EindeSpel
import com.example.beardwulf.reva.fragments.vragenOplossen.Kaart
import com.example.beardwulf.reva.fragments.vragenOplossen.VraagIngevuld
import com.example.beardwulf.reva.fragments.vragenOplossen.VraagInvullen
import com.example.beardwulf.reva.fragments.vragenOplossen.VraagInvullenFoto
import com.example.beardwulf.reva.interfaces.QuestionCallbacks
import kotlinx.android.synthetic.main.activity_vragen_oplossen.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import com.google.gson.Gson



/**
 * Activity die het tonen van alle vragen en inlezen van alle antwoorden verzorgt
 */
class VragenOplossen : AppCompatActivity(), QuestionCallbacks, Kaart.MapCallbacks, VraagInvullen.QuestionAnswerCallbacks, VraagInvullenFoto.QuestionAnswerPhotoCallbacks {
    var questionNr = 0
    override var maxQuestion = 5
    var questions = arrayOf(
            "Hoeveel spelers zijn er op het veld tijdens een wedstrijd rolstoelbasketbal? (Beide ploegen samen opgeteld)",
            "Hoeveel kost de nieuwste kruk van VIGO?"
    )

    lateinit var kaart: Kaart
    lateinit var vraagInvullen: VraagInvullen
    lateinit var vraagIngevuld: VraagIngevuld
    lateinit var eindeSpel: EindeSpel

    lateinit var exhibitors: ArrayList<Exhibitor>
    lateinit var currentExhibitor: ExhibitorViewModel
    override var firstQuestion : Boolean = true

    /**
     * Opent de fragment voor het tonen en beantwoorden van een vraag
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vragen_oplossen)
       currentExhibitor = ViewModelProviders.of(this).get( ExhibitorViewModel::class.java);
        setNextExhibitor()
        overridePendingTransition(0, 0);
       // makeExhibitors()

/*        val service = RetrofitClientInstance().getRetrofitInstance()!!.create(Endpoint::class.java!!)
        val call = service.getExhibitor(MainActivity.group._id!!)
        call.enqueue(object : Callback<Exhibitor> {
            override fun onResponse(call: Call<Exhibitor>, response: Response<Exhibitor>) {
                exhibitor = Exhibitor(response.body()!!._id, response.body()!!.name, response.body()!!.visits, response.body()!!.category, response.body()!!.coordinates, response.body()!!.question)

                setContentView(R.layout.activity_vragen_oplossen)
                setFragment(Kaart.newInstance(), R.id.fragment)
            }

            override fun onFailure(call: Call<Exhibitor>, t: Throwable) {
                Log.d("Error", t.message)
            }
        })*/

    }

    override fun onResume() {
        super.onResume()
    }
    fun makeExhibitors() {
        exhibitors= ArrayList(2)
        /*var exhibitor2 = Exhibitor("Test", "Vigo",1, "Rolstoelen", Pair(10, 3))
        var exhibitor1 = Exhibitor("Test", "Thuisbezorgwinkel Orona",2, "Rolstoelen Sport", Pair(5,3))
        var exhibitor0 = Exhibitor("3","Test", 0, "Rolstoelen", Pair(5, 5))
        exhibitors.add(exhibitor2)
        exhibitors.add(exhibitor1)
        exhibitors.add(exhibitor0)*/

    }

    fun setFragment(fragment: Fragment, int: Int) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(int, fragment)
        fragmentTransaction.commit()
    }

    fun removeFragment(fragment: Fragment) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.remove(fragment)
        fragmentTransaction.commit()
    }

    fun currentExhibitor(): Exhibitor {
        return exhibitors[questionNr]
    }

    fun unfocusMap() {
       fragment.alpha = 0.1F
    }

    fun focusMap(){
        fragment.alpha = 1.0F
    }
    override fun setNextExhibitor() {
        Log.d("NEXTE", "ANOTHER ONE")
    if(currentExhibitor.isNew) {
        Log.d("EXNEW", "NEW");
        val service = RetrofitClientInstance().getRetrofitInstance()!!.create(Endpoint::class.java!!)
        val call = service.getExhibitor((applicationContext as testApplicationClass).group._id!!)
        call.enqueue(object : Callback<Exhibitor> {
            override fun onResponse(call: Call<Exhibitor>, response: Response<Exhibitor>) {
                Log.d("EXNEW", "REACHED")
                currentExhibitor.currentExhibitor = response.body()!!
                currentExhibitor.isNew = false
                Log.d("currentExhibitor", Gson().toJson(response))
                //currentExhibitor = Exhibitor(response.body()!!._id, response.body()!!.name, response.body()!!.visits, response.body()!!.category, response.body()!!.coordinates, response.body()!!.question)

                setFragment(Kaart.newInstance(), R.id.fragment)
            }

            override fun onFailure(call: Call<Exhibitor>, t: Throwable) {
                Log.d("Error", t.message)
            }
        })
    } else {
        Log.d("EXNEW", "OLD");
        setFragment(Kaart.newInstance(), R.id.fragment)
    }
    }

    override fun setAnswer(answer: String) {
        val service = RetrofitClientInstance().getRetrofitInstance()!!.create(Endpoint::class.java!!)
        val call = service.postAnwser((applicationContext as testApplicationClass).group._id!!, answer)
        call.enqueue(object : Callback<Group> {
            override fun onResponse(call: Call<Group>, response: Response<Group>) {
                setFragment(Kaart.newInstance(), R.id.fragment)
                unfocusMap()
                val vraagIngevuld = VraagIngevuld.newInstance()
                firstQuestion = false
                setFragment(vraagIngevuld, R.id.fragment2)
                this@VragenOplossen.vraagIngevuld = vraagIngevuld
            }

            override fun onFailure(call: Call<Group>, t: Throwable) {
                Log.d("Error", t.message)
            }
        })
    }

    override fun setAnswer(photo: Bitmap) {
        val service = RetrofitClientInstance().getRetrofitInstance()!!.create(Endpoint::class.java!!)
        val group = (applicationContext as testApplicationClass).group
        val f = File(cacheDir, "answerPhoto.png")
        val outputStream : FileOutputStream = FileOutputStream(f)
        val byteArrayOutputStream = ByteArrayOutputStream()
        photo.compress(Bitmap.CompressFormat.PNG, 30 /*ignored for PNG*/, byteArrayOutputStream);
        val bitmapdata = byteArrayOutputStream.toByteArray()

        outputStream.write(bitmapdata)
        outputStream.flush()
        outputStream.close()
        // val f = File(this.getCacheDir(), "groupFoto")
        val filePart = MultipartBody.Part.createFormData("photo", f.name, RequestBody.create(MediaType.parse("image/*"), f))
        val call = service.postAnwser((applicationContext as testApplicationClass).group._id!!, filePart)
        call.enqueue(object : Callback<Group> {
            override fun onResponse(call: Call<Group>, response: Response<Group>) {
                setFragment(Kaart.newInstance(), R.id.fragment)
                unfocusMap()
                val vraagIngevuld = VraagIngevuld.newInstance()
                firstQuestion = false
                setFragment(vraagIngevuld, R.id.fragment2)
                this@VragenOplossen.vraagIngevuld = vraagIngevuld
            }

            override fun onFailure(call: Call<Group>, t: Throwable) {
                Log.d("Error", t.message)
            }
        })
    }

    override fun goToNextQuestion() {
        if (!firstQuestion)
            removeFragment(vraagIngevuld)
        if(currentExhibitor.currentExhibitor.question.type == QuestionType.TEXT)
            setFragment(VraagInvullen.newInstance(), R.id.fragment)
        else
            setFragment(VraagInvullenFoto.newInstance(), R.id.fragment)
    }

    override fun determineNextMove() {
        if (currentExhibitor.currentExhibitor.question.counter + 1 < maxQuestion) {
            removeFragment(vraagIngevuld)
            currentExhibitor.isNew = true
            setNextExhibitor()
            //parent.removeFragment(this)
            focusMap()
        } else {
            setFragment(EindeSpel.newInstance(), R.id.fragment2)
        }
    }
    override fun decrementCounter() {
        questionNr--
    }

    companion object {
        fun newInstance(): VragenOplossen {
            return newInstance()
        }
    }
}
