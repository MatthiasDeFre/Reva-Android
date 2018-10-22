package com.example.beardwulf.reva.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import android.widget.Toast
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.adapters.VoorkeurCategorieLijstAdapter
import com.example.beardwulf.reva.dtos.VoorkeurCategorie
import kotlinx.android.synthetic.main.voorkeurcategorieen.*

class VoorkeurCategorieen : AppCompatActivity() {

    var selectedCategorieen: MutableList<VoorkeurCategorie> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.voorkeurcategorieen);

        var adapter = VoorkeurCategorieLijstAdapter(this, maakCategorieItems())
        listView?.adapter = adapter
        listView?.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE)
        listView?.setOnItemClickListener { parent, view, position, id ->

            val item = listView?.getItemAtPosition(position) as VoorkeurCategorie;

            if (selectedCategorieen.contains(item)){
                selectedCategorieen.remove(item);
            }else {
                selectedCategorieen.add(item)
            }
            selectedCategorieen.forEach { System.out.print(it) }
            Toast.makeText(this, selectedCategorieen.size.toString(), Toast.LENGTH_SHORT).show()

        }

    }

    private fun maakCategorieItems(): ArrayList<VoorkeurCategorie> {
        var items = ArrayList<VoorkeurCategorie>()

        for (i in 0..9) {
            var user: VoorkeurCategorie = VoorkeurCategorie("Medica")
            items.add(user)
        }

        return items
    }
}
