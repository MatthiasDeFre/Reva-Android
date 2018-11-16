package com.example.beardwulf.reva.activities.registreren

import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.activities.vragenOplossen.VragenOplossen
import com.example.beardwulf.reva.domain.Category
import com.example.beardwulf.reva.fragments.registreren.RegisterCategories
import com.example.beardwulf.reva.fragments.registreren.RegisterPhoto
import com.example.beardwulf.reva.fragments.registreren.RegistreerGroep
import com.example.beardwulf.reva.interfaces.RegisterCallbacks
import android.content.Intent
import android.util.Log
import com.example.beardwulf.reva.Endpoint
import com.example.beardwulf.reva.RetrofitClientInstance
import com.example.beardwulf.reva.domain.Group
import com.example.beardwulf.reva.domain.testApplicationClass
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.MultipartBody
import java.io.File
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream

class Registreren : AppCompatActivity(), RegisterCallbacks {
    private lateinit var photo: Bitmap
    private lateinit var photoUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registreren)

        var conf = Bitmap.Config.ARGB_8888
        photo = Bitmap.createBitmap(306, 306, conf)
        photoUri = Uri.EMPTY

        setFragment(RegisterPhoto.newInstance())
    }

    fun setFragment(fragment: Fragment) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.registreerlayout, fragment)
        fragmentTransaction.commit()
    }

    override fun setCategories(categories: List<Category>) {
        //SET CATEGORIES
        val group = (applicationContext as testApplicationClass).group
        val f = File(cacheDir, "groupImage.jpeg")
        f.createNewFile()
        val outputStream : FileOutputStream = FileOutputStream(f)
        val byteArrayOutputStream = ByteArrayOutputStream()
        photo.compress(Bitmap.CompressFormat.JPEG, 30 /*ignored for PNG*/, byteArrayOutputStream);
        val bitmapdata = byteArrayOutputStream.toByteArray()

        outputStream.write(bitmapdata)
        outputStream.flush()
        outputStream.close()
       // val f = File(this.getCacheDir(), "groupFoto")
        val filePart = MultipartBody.Part.createFormData("groupImage", f.name, RequestBody.create(MediaType.parse("image/*"), f))
        val service = RetrofitClientInstance().getRetrofitInstance()!!.create(Endpoint::class.java!!)
        val call = service.registerGroup("5be6fc1db462e609c073e68d", filePart,group.description, group.name,ArrayList<Category>())
        println(filePart)
       call.enqueue(object : Callback<Group> {
            override fun onResponse(call: Call<Group>, response: Response<Group>) {
               // val group = Group(response.body()!!._id, response.body()!!.name, response.body()!!.visits, response.body()!!.category, response.body()!!.coordinates, response.body()!!.question)

                startActivity(Intent(applicationContext,VragenOplossen::class.java))
            }

            override fun onFailure(call: Call<Group>, t: Throwable) {
                Log.d("Error", t.message)
            }
        })


    }
    override fun setNameAndDescription(name: String, description: String) {
       //SET IFNO
        (applicationContext as testApplicationClass).group.name = name
        (applicationContext as testApplicationClass).group.description = description
        setFragment(RegisterCategories.newInstance())
    }
    override fun setPhoto(photo: Bitmap) {
        //SET PHOTO OF GROUP
        this.photo = photo
        setFragment(RegistreerGroep.newInstance())
    }


    companion object {

        /**
         * Variabele dat wordt gebruikt om de imageview voor de groepsfoto op te vullen
         *
         */

    }


}
