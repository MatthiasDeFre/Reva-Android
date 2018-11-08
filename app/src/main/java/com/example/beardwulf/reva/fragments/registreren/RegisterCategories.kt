package com.example.beardwulf.reva.fragments.registreren

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.activities.registreren.Registreren
import com.example.beardwulf.reva.activities.vragenOplossen.VragenOplossen
import com.example.beardwulf.reva.adapters.CategoryListAdapter
import com.example.beardwulf.reva.domain.Category
import kotlinx.android.synthetic.main.fragment_register_categories.*


class RegisterCategories : Fragment(){

    lateinit var parent: Registreren
    var selectedCategories: MutableList<Category> = ArrayList()
    val categories = arrayOf<String>("Hulpmiddelen ADL","Aangepaste kledij","Rolstoelen","Rolstoelen sport","Scooters","Loophulpmiddelen en rampen","Fietsen","Hulpmiddelen voor kinderen","Omgevingsbedineing, Domotica, Besturing","Aangepaste auto's","Tilhulpmiddelen","Huisliften","Vakantie, Reizen sport","Overheidsdiensten","Belangenverenigingen,Zelfhulpgroepen")
    val amountOfCategories=10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_register_categories, container, false)


        parent = (activity as Registreren)

        return view
    }
    override fun onResume() {
        super.onResume()

        cmdNext.setOnClickListener {
            var intent = Intent(parent, VragenOplossen::class.java)
            startActivity(intent)
        }

        //De listView met de categorieen krijgt een custom adapter om specifiekere rows te maken
        var adapter = CategoryListAdapter(parent, makeCategoryItems())
        listView?.adapter = adapter
        listView?.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE)

        //Bij klik, voeg item toe of verwijder item uit geselecteerdeCategorieen
        listView?.setOnItemClickListener { parent, view, position, id ->

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

        }

        txtAantalGeselecteerd.text = selectedCategories.size.toString() + "/" + amountOfCategories + " " + getString(R.string.geselecteerd)



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

    companion object {
        fun newInstance(): RegisterCategories {
            return RegisterCategories()
        }
    }
}