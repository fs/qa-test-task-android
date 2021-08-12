package com.flatstack.qatesttask.feature.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flatstack.qatesttask.R
import com.flatstack.qatesttask.databinding.ItemNewsEntryBinding
import com.flatstack.qatesttask.feature.news.model.PostDto
import com.squareup.picasso.Picasso

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PostDto>() {

    override fun areItemsTheSame(oldItem: PostDto, newItem: PostDto): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: PostDto, newItem: PostDto): Boolean = oldItem.id == newItem.id
}

class NewsAdapter(
    private val onClickListener: (PostDto) -> Unit,
    private val onBottomReachedListener: () -> Unit,
) : ListAdapter<PostDto, PostHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder = PostHolder.newInstance(
        parent, onClickListener
    )

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        if (currentList.lastIndex == position) {
            onBottomReachedListener()
        }
        holder.bind(getItem(position))
    }
}

class PostHolder(
    private val container: ItemNewsEntryBinding,
    private val onClickListener: (PostDto) -> Unit
) : RecyclerView.ViewHolder(container.root) {

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

    companion object {
        fun newInstance(parent: ViewGroup, onClickListener: ((PostDto) -> Unit)): PostHolder = PostHolder(
            ItemNewsEntryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickListener
        )
    }
}
