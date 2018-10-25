package com.example.beardwulf.reva.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.activities.vragenOplossen.VragenOplossen
import com.example.beardwulf.reva.adapters.VoorkeurCategorieLijstAdapter
import com.example.beardwulf.reva.domain.VoorkeurCategorie
import kotlinx.android.synthetic.main.voorkeurcategorieen.*

class VoorkeurCategorieen : AppCompatActivity() {

    var selectedCategorieen: MutableList<VoorkeurCategorie> = ArrayList()
    val categorieen = arrayOf<String>("Hulpmiddelen ADL","Aangepaste kledij","Rolstoelen","Rolstoelen sport","Scooters","Loophulpmiddelen en rampen","Fietsen","Hulpmiddelen voor kinderen","Omgevingsbedineing, Domotica, Besturing","Aangepaste auto's","Tilhulpmiddelen","Huisliften","Vakantie, Reizen sport","Overheidsdiensten","Belangenverenigingen,Zelfhulpgroepen")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.voorkeurcategorieen);

        //De listView met de categorieen krijgt een custom adapter om specifiekere rows te maken
        var adapter = VoorkeurCategorieLijstAdapter(this, maakCategorieItems())
        listView?.adapter = adapter
        listView?.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE)

        //Bij klik, voeg item toe of verwijder item uit geselecteerdeCategorieen
        listView?.setOnItemClickListener { parent, view, position, id ->

            val item = listView?.getItemAtPosition(position) as VoorkeurCategorie;
            if (selectedCategorieen.contains(item)){
                selectedCategorieen.remove(item);
            }else {
                selectedCategorieen.add(item)
            }

            //Verander gui op basis van selecties
            if(selectedCategorieen.size >= 10) {
                cmdNext.isEnabled = true
                cmdNext.alpha = 1F
            } else{
                cmdNext.isEnabled = false
                cmdNext.alpha = 0.4F
            }
            txtAantalGeselecteerd.text = selectedCategorieen.size.toString() + "/10 " + getString(R.string.geselecteerd)

        }

        txtAantalGeselecteerd.text = selectedCategorieen.size.toString() + "/10 " + getString(R.string.geselecteerd)

        cmdNext.setOnClickListener{
            startActivity(Intent(this, VragenOplossen::class.java))
        }

    }

    //Opvullen van de listview met alle categorieën
    private fun maakCategorieItems(): ArrayList<VoorkeurCategorie> {
        var items = ArrayList<VoorkeurCategorie>()

        for (categorie in categorieen) {
            var item: VoorkeurCategorie = VoorkeurCategorie(categorie)
            items.add(item)
        }

        return items
    }
}
