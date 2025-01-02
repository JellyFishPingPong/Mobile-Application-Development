package com.example.assignment

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class ForumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val profilePic: ImageView = itemView.findViewById(R.id.profile_pic)
    val username: TextView = itemView.findViewById(R.id.forum_username)
    val uploadDate: TextView = itemView.findViewById(R.id.forum_upload_date)
    val forumTitle: TextView = itemView.findViewById(R.id.forum_title)
    val forumContent: TextView = itemView.findViewById(R.id.forum_description)
    val likes: TextView = itemView.findViewById(R.id.forum_likes)
    val likeIcon: CheckBox = itemView.findViewById(R.id.like_icon)
    val forumComment: TextView = itemView.findViewById(R.id.forum_comment)
    val entireForum: MaterialCardView = itemView.findViewById(R.id.entire_forum)
}