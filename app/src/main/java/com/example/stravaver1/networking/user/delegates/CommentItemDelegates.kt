package com.example.stravaver1.networking.user.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stravaver1.R
import com.example.stravaver1.databinding.ItemCommentBinding
import com.example.stravaver1.networking.models.CommentItem
import com.example.stravaver1.networking.user.athlete.AthleteFragment
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class CommentItemDelegate() : AbsListItemAdapterDelegate<Any, Any, CommentItemDelegate.ViewHolder>(){

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is CommentItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comment, parent, false)
        val binding: ItemCommentBinding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, itemView)
    }

    override fun onBindViewHolder(
        item: Any,
        viewHolder: ViewHolder,
        payloads: MutableList<Any>
    ) {
        viewHolder.bind(item as CommentItem)
    }

    inner class ViewHolder(
        private val binding: ItemCommentBinding,
        val containerView: View,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CommentItem) = with(binding) {
            binding.name.text = item.name
            binding.date.text = item.date
            binding.plan.text = item.type
            binding.distance.text = item.distance.toString()
            binding.movingTime.text = item.moving_time.toString()
            binding.kudosCount.text = item.kudos_count.toString()
            binding.commentCount.text = item.comment_count.toString()
            Glide.with(containerView)
                .load(AthleteFragment.photo.picture)
                .placeholder(R.drawable.no_face)
                .into(binding.image)

        }
    }
}