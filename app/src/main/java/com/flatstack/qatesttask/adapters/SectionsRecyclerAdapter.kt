package com.flatstack.qatesttask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flatstack.qatesttask.data.guardiannews.model.Category
import com.flatstack.qatesttask.databinding.SectionsRecyclerElemBinding
import com.google.android.material.card.MaterialCardView

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category) =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Category, newItem: Category) =
        oldItem == newItem
}

class SectionsRecyclerAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Category, SectionsRecyclerAdapter.SectionsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionsViewHolder {
        return SectionsViewHolder(
            SectionsRecyclerElemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SectionsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SectionsViewHolder(private val container: SectionsRecyclerElemBinding) :
        View.OnClickListener,
        RecyclerView.ViewHolder(container.root) {

        fun bind(category: Category) {
            container.categoryTv.text = category.title

            (itemView as? MaterialCardView)?.run { isChecked = category.isChecked }
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
