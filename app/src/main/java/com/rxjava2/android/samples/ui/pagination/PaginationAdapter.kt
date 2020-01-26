package com.rxjava2.android.samples.ui.pagination

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rxjava2.android.samples.R
import java.util.*

/**
 * Created by amitshekhar on 15/03/17.
 */
class PaginationAdapter : RecyclerView.Adapter<ViewHolder>() {
    var items: MutableList<String> = ArrayList()
    fun addItems(items: Collection<String?>?) {
        if (items != null) {
//            this.items.addAll(items!!)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ItemViewHolder.create(parent)!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ItemViewHolder?)!!.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private class ItemViewHolder internal constructor(itemView: View) : ViewHolder(itemView) {
        fun bind(content: String?) {
            (itemView as TextView).text = content
        }

        companion object {
            fun create(parent: ViewGroup?): ItemViewHolder? {
                return ItemViewHolder(
                        LayoutInflater.from(parent!!.getContext()).inflate(R.layout.item_pagination, parent, false))
            }
        }
    }
}