package com.submission.storyapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.submission.storyapp.databinding.LayoutStoryItemBinding
import com.submission.storyapp.domain.models.dto.StoryResponseItemDto

class StoryListAdapter :
    PagingDataAdapter<StoryResponseItemDto, StoryListAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private val binding: LayoutStoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, story: StoryResponseItemDto) {
            binding.apply {
                tvStoryUsername.text = story.name
                tvStoryDescription.text = story.description
                ivStoryImage.setImageFromUrl(context, story.photoUrl)
                tvStoryDate.setLocalDateFormat(story.createdAt)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutStoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val story = getItem(position)
        if (story != null) {
            holder.bind(holder.itemView.context, story)
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<StoryResponseItemDto>() {
            override fun areItemsTheSame(oldItem: StoryResponseItemDto, newItem: StoryResponseItemDto): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: StoryResponseItemDto, newItem: StoryResponseItemDto): Boolean {
                return oldItem == newItem
            }
        }
    }

}
