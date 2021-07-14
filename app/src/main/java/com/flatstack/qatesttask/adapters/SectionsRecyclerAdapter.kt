package com.flatstack.qatesttask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flatstack.qatesttask.R
import com.flatstack.qatesttask.data.guardiannews.model.GuardianResponse
import com.google.android.material.card.MaterialCardView

data class  Category(var title: String, var isChecked: Boolean)
private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Category>(){
    override fun areItemsTheSame(oldItem: Category, newItem: Category) =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Category, newItem: Category) =
        oldItem == newItem
}
class SectionsRecyclerAdapter(private val listener:OnItemClickListener): ListAdapter<Category, RecyclerView.ViewHolder>(
    DIFF_CALLBACK) {

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
        val category = getItem(position)
        when (holder) {
            is SectionsViewHolder -> holder.bind(category)
        }
        holder.itemView.setOnClickListener {
            (it as MaterialCardView).apply {
                isChecked = category.isChecked
                category.isChecked = !category.isChecked

            }

        }
    }


    inner class SectionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val category = itemView.findViewById<TextView>(R.id.category_tv)

        fun bind(categoryText: Category) {
            category.text = categoryText.title
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }

        }


    }


    interface OnItemClickListener {
        fun onItemClick(position: Int)

    }
}
