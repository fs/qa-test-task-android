package com.flatstack.qatesttask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.flatstack.qatesttask.R
import com.flatstack.qatesttask.data.guardiannews.model.GuardianResponse

class SectionsRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var categories: List<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SectionsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.sections_recycler_elem,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is SectionsViewHolder -> holder.bind(categories[position])
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun submitData(data: List<String>){
        categories = data

    }

    inner class SectionsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    private val category = itemView.findViewById<TextView>(R.id.category_tv)

        fun bind(categoryText: String){
            category.text = categoryText
        }
    }
}