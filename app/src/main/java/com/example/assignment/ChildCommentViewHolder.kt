package com.example.assignment

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChildCommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
    val profilePic: ImageView = itemView.findViewById(R.id.child_comment_profile_pic)
    val username: TextView = itemView.findViewById(R.id.child_comment_username)
    val comment: TextView = itemView.findViewById(R.id.child_comment_comment)
    val likeBtn: TextView = itemView.findViewById(R.id.child_comment_like_button)
    val reply: TextView = itemView.findViewById(R.id.child_comment_reply)
    val likes: TextView = itemView.findViewById(R.id.child_comment_likes)
}