package com.example.beardwulf.reva.adapters
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.domain.Category


/**
 * @author Bett
 * Help class for creating a listview with images and text
 * found on https://www.youtube.com/watch?v=KFo1bO05Jho&t=1s
 * implemented by Karel Heyndrickx
 */
class CategoryListAdapter(private var activity: Activity, private var items: ArrayList<Category>): BaseAdapter() {


    private class ViewHolder(row: View?) {
        var txtCategoryName: TextView? = null

        init {
            this.txtCategoryName = row?.findViewById<TextView>(R.id.txtCategoryName)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.category_row, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var category = items[position]
        viewHolder.txtCategoryName?.text = category.name

        return view as View
    }

    override fun getItem(i: Int): Category {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }


}