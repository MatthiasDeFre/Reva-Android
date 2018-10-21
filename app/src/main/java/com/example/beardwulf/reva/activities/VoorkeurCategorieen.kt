package com.example.beardwulf.reva.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.adapters.VoorkeurCategorieLijstAdapter
import com.example.beardwulf.reva.dtos.VoorkeurCategorie
import kotlinx.android.synthetic.main.voorkeurcategorieen.*

class VoorkeurCategorieen : AppCompatActivity() {

    val IMAGES = arrayOf(R.drawable.medisch, R.drawable.sport);
    var onderwerpen = arrayOf<String>("Sport", "Technologie", "Medica");

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.voorkeurcategorieen);

        var adapter = VoorkeurCategorieLijstAdapter(this, maakCategorieItems())
        listView?.adapter = adapter
    }

    private fun maakCategorieItems(): ArrayList<VoorkeurCategorie> {
        var items = ArrayList<VoorkeurCategorie>()

        for (i in 0..9) {
            //var user: VoorkeurCategorie = VoorkeurCategorie(categorie = onderwerpen[i%3])
            var user: VoorkeurCategorie = VoorkeurCategorie("Medisch")
            items.add(user)
        }

        return items
    }
}
