package com.example.beardwulf.reva.fragments.registreren

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.beardwulf.reva.Endpoint
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.RetrofitClientInstance
import com.example.beardwulf.reva.activities.registreren.Registreren
import com.example.beardwulf.reva.activities.vragenOplossen.VragenOplossen
import com.example.beardwulf.reva.adapters.CategoryListAdapter
import com.example.beardwulf.reva.adapters.testAdapter
import com.example.beardwulf.reva.domain.Category
import com.example.beardwulf.reva.domain.Exhibitor
import com.example.beardwulf.reva.domain.testApplicationClass
import com.example.beardwulf.reva.fragments.vragenOplossen.Kaart
import com.example.beardwulf.reva.interfaces.RegisterCallbacks
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_register_categories.*
import org.jetbrains.anko.backgroundColor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterCategories : Fragment(){

    lateinit var parent: RegisterCategoriesCallBacks
    var selectedCategories: MutableList<String> = ArrayList()
    val categories = arrayOf<String>("Hulpmiddelen ADL","Aangepaste kledij","Rolstoelen","Rolstoelen sport","Scooters","Loophulpmiddelen en rampen","Fietsen","Hulpmiddelen voor kinderen","Omgevingsbedineing, Domotica, Besturing","Aangepaste auto's","Tilhulpmiddelen","Huisliften","Vakantie, Reizen sport","Overheidsdiensten","Belangenverenigingen,Zelfhulpgroepen")
    private var amountOfCategories : Int = 10
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_register_categories, container, false)
        parent = (activity as RegisterCategoriesCallBacks)
        return view
    }
    override fun onResume() {
        super.onResume()

        cmdNext.setOnClickListener {
            var categoryList :MutableList<Category> = mutableListOf<Category>();
            selectedCategories.forEach {c -> categoryList.add(Category(c))}
            (activity!!.applicationContext as testApplicationClass).group.categories = categoryList;
           parent.registerAndGoToMap()
        }
        val service = RetrofitClientInstance().getRetrofitInstance()!!.create(Endpoint::class.java!!)
        val call = service.getCategories()
        call.enqueue(object : Callback<ArrayList<String>> {
            override fun onResponse(call: Call<ArrayList<String>>, response: Response<ArrayList<String>>) {
                var categories = response.body()!!
                amountOfCategories = categories.count() -categories.count() / 3
                if(amountOfCategories > 10)
                    amountOfCategories = 10
                textView.text = String.format(resources.getString(R.string.stap3), amountOfCategories)
                viewManager = LinearLayoutManager(this@RegisterCategories.context)
                listView?.adapter = testAdapter(selectedCategories, onClicklistener(),response.body()!!)

                listView?.layoutManager = viewManager
                txtAantalGeselecteerd.text = selectedCategories.size.toString() + "/" + amountOfCategories + " " + getString(R.string.geselecteerd)
            }

            override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
                Log.d("Error", t.message)
            }
        })
        //De listView met de categorieen krijgt een custom adapter om specifiekere rows te maken
        //var adapter = CategoryListAdapter(parent, makeCategoryItems())
        //listView?.adapter = adapter

     //   listView?.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE)

        //Bij klik, voeg item toe of verwijder item uit geselecteerdeCategorieen
       /* listView?.setOnItemClickListener { parent, view, position, id ->

            val item = listView?.getItemAtPosition(position) as Category;
            if (selectedCategories.contains(item)){
                selectedCategories.remove(item);
            }else {
                selectedCategories.add(item)
            }

            //Verander gui op basis van selecties
            if(selectedCategories.size >= amountOfCategories) {
                cmdNext.isEnabled = true
                cmdNext.alpha = 1F
            } else{
                cmdNext.isEnabled = false
                cmdNext.alpha = 0.4F
            }
            txtAantalGeselecteerd.text = selectedCategories.size.toString() + "/" + amountOfCategories + " " + getString(R.string.geselecteerd)

        }*/





    }



    //Opvullen van de listview met alle categorieën
    private fun makeCategoryItems(): ArrayList<Category> {
        var items = ArrayList<Category>()

        for (categorie in categories) {
            var item: Category = Category(categorie)
            items.add(item)
        }

        return items
    }
    private fun onClicklistener() : View.OnClickListener {
    val onClickListener : View.OnClickListener;
        return View.OnClickListener { v -> // Every view has a tag that can be used to store data related to that view // Here each item in the RecyclerView keeps a reference to the comic it represents. // This allows us to reuse a single listener for all items in the list val item = v.tag as Comic if (twoPane) { val fragment = RagecomicDetailFragment. newInstance(item) parentActivity.supportFragmentManager .beginTransaction() .replace(R.id.

            val cat = v.tag as String
            if(selectedCategories.count() < amountOfCategories && !selectedCategories.contains(cat)) {
                selectedCategories.add(cat)
                txtAantalGeselecteerd.text = selectedCategories.size.toString() + "/" + amountOfCategories + " " + getString(R.string.geselecteerd)
                v.setBackgroundColor(resources.getColor(R.color.Groen))
            } else {
                selectedCategories.remove(cat)
                txtAantalGeselecteerd.text = selectedCategories.size.toString() + "/" + amountOfCategories + " " + getString(R.string.geselecteerd)
                v.setBackgroundColor(resources.getColor(R.color.Transparant))
            }
            if(selectedCategories.count() == amountOfCategories) {
                cmdNext.isEnabled = true
                cmdNext.alpha = 1F
            } else {
                cmdNext.isEnabled = false
                cmdNext.alpha = 0.4F
            }

       }
    }
    interface RegisterCategoriesCallBacks {
        fun registerAndGoToMap()
    }
    companion object {
        fun newInstance(): RegisterCategories {
            return RegisterCategories()
        }
    }
}