package com.example.stravaver1.networking.user

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.stravaver1.networking.models.CommentItem
import com.example.stravaver1.networking.user.delegates.CommentItemDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class UserAdapter () :  AsyncListDifferDelegationAdapter<Any>(
    MainDiffCallback()
) {

    init {
        delegatesManager
            .addDelegate(CommentItemDelegate())
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(){
        notifyDataSetChanged()
    }

    class MainDiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(first: Any, second: Any): Boolean {
            return first.javaClass == second.javaClass && when (first) {
                is CommentItem -> first.id == (second as CommentItem).id
                else -> true
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(first: Any, second: Any): Boolean = first == second
    }

}