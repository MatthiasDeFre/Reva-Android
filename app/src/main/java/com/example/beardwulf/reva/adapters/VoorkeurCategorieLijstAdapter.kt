package com.example.beardwulf.reva.adapters
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.domain.VoorkeurCategorie


/**
 * @author Bett
 * Help class for creating a listview with images and text
 * found on https://www.youtube.com/watch?v=KFo1bO05Jho&t=1s
 * implemented by Karel Heyndrickx
 */
class VoorkeurCategorieLijstAdapter(private var activity: Activity, private var items: ArrayList<VoorkeurCategorie>): BaseAdapter() {


    private class ViewHolder(row: View?) {
        var txtCategorienaam: TextView? = null

        init {
            this.txtCategorienaam = row?.findViewById<TextView>(R.id.txtCategorienaam)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.voorkeurcategorie_rij, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var voorkeurCateogorie = items[position]
        viewHolder.txtCategorienaam?.text = voorkeurCateogorie.naam

        return view as View
    }

    override fun getItem(i: Int): VoorkeurCategorie {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }


}