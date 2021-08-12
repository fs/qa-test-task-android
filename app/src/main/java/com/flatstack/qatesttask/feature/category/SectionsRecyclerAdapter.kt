package com.flatstack.qatesttask.feature.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flatstack.qatesttask.data.guardiannews.model.Category
import com.flatstack.qatesttask.databinding.ItemSectionsBinding

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category) = oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Category, newItem: Category) = oldItem == newItem
}

class SectionsRecyclerAdapter(
    private val onItemClick: (Int) -> Unit
) : ListAdapter<Category, SectionsRecyclerAdapter.SectionsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionsViewHolder = SectionsViewHolder(
        ItemSectionsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onItemClick
    )

    override fun onBindViewHolder(holder: SectionsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SectionsViewHolder(
        private val container: ItemSectionsBinding,
        private val onItemClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(container.root) {

        init {
            container.root.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        fun bind(category: Category) {
            container.tvCategory.text = category.title
            container.root.isChecked = category.isChecked
        }
    }
}
