package com.example.assignment

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChildCommentAdapter() : RecyclerView.Adapter<ChildCommentViewHolder>() {
    lateinit var context: Context
    private lateinit var commentList: List<Comment>
    private lateinit var forumActions:ForumActions

    lateinit var memT: DataBaseHelper.Companion.MemberTable
    private lateinit var commentLikeT: DataBaseHelper.Companion.CommentLikeTable
    private var memId = -1

    constructor(context: Context, commentList: List<Comment>, forumActions: ForumActions, memId: Int): this() {
        this.context = context
        this.commentList = commentList
        this.forumActions = forumActions
        this.memId = memId
        this.memT = DataBaseHelper.Companion.MemberTable(context)
        this.commentLikeT = DataBaseHelper.Companion.CommentLikeTable(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildCommentViewHolder {
        return ChildCommentViewHolder(
            LayoutInflater.from(context).inflate(R.layout.forum_child_comment_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: ChildCommentViewHolder, position: Int) {
        val currComment = commentList[position]
        val member = memT.getMember(currComment.memId)

        holder.profilePic.setImageResource(member.profilePic)
        holder.username.text = member.username
        holder.comment.text = currComment.comment

        val likeAmount = commentLikeT.getLikeAmount(currComment.commentId)
        if(likeAmount > 0) {
            holder.likes.text = "$likeAmount likes"
        } else {
            holder.likes.text = null
        }

        if(commentLikeT.liked(currComment.commentId,memId)) {
            holder.likeBtn.setTextColor(Color.BLUE)
        }
        else  {
            holder.likeBtn.setTextColor(Color.BLACK)
        }

        holder.likeBtn.setOnClickListener {
            commentLikeT.toggleLike(currComment.commentId, memId)
            notifyDataSetChanged()
        }


        holder.reply.setOnClickListener{
            forumActions.onReplyClicked(currComment.parentId)
        }
    }


}