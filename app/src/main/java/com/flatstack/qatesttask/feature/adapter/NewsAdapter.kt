package com.flatstack.qatesttask.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flatstack.qatesttask.R
import com.flatstack.qatesttask.databinding.ElementNewsEntryBinding
import com.flatstack.qatesttask.feature.model.PostDto
import com.squareup.picasso.Picasso

class NewsAdapter(
    private val onClickListener: (PostDto) -> Unit = {},
    private val onBottomReachedListener: () -> Unit = {},
) : ListAdapter<PostDto, PostHolder>(PostDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        return PostHolder.newInstance(
            parent,
            onClickListener
        )
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        if (currentList.lastIndex == position) {
            onBottomReachedListener()
        }
        holder.bind(getItem(position))
    }

    object PostDiffCallback : DiffUtil.ItemCallback<PostDto>() {
        override fun areItemsTheSame(oldItem: PostDto, newItem: PostDto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PostDto, newItem: PostDto): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

class PostHolder(
    private val container: ElementNewsEntryBinding,
    val onClickListener: ((PostDto) -> Unit)
) :

    RecyclerView.ViewHolder(container.root) {
    companion object {
        fun newInstance(parent: ViewGroup, onClickListener: ((PostDto) -> Unit)): PostHolder {
            val bind = ElementNewsEntryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return PostHolder(bind, onClickListener)
        }
    }
    fun bind(postDto: PostDto) {
        container.tvNewsElementTitle.text = postDto.title
        container.root.setOnClickListener {
            onClickListener(postDto)
        }
        Picasso.with(container.root.context)
            .load(postDto.thumbnailUrl)
            .placeholder(R.drawable.ic_the_guardian)
            .error(R.drawable.ic_the_guardian)
            .into(container.ivNewsElementThumbnail)
    }
}
