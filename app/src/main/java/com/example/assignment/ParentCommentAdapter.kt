package com.example.assignment

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ParentCommentAdapter(): RecyclerView.Adapter<ParentCommentViewHolder>() {

    private lateinit var context: Context
    private lateinit var commentList: List<Comment>
    private lateinit var forumActions: ForumActions
    private lateinit var member: UserModel
    private var memId = -1

    private lateinit var memT: DataBaseHelper.Companion.MemberTable
    private lateinit var commentT: DataBaseHelper.Companion.ForumCommentTable
    private lateinit var commentLikeT: DataBaseHelper.Companion.CommentLikeTable

    constructor(context: Context, commentList: List<Comment>, forumActions: ForumActions, memId: Int): this() {
        this.context = context
        this.commentList = commentList
        this.forumActions = forumActions
        this.memId = memId
        this.memT = DataBaseHelper.Companion.MemberTable(context)
        this.commentT = DataBaseHelper.Companion.ForumCommentTable(context)
        this.commentLikeT = DataBaseHelper.Companion.CommentLikeTable(context)
    }

    fun setCommentList(commentList: List<Comment>) {
        this.commentList = commentList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentCommentViewHolder {
        Log.d("ParentAdapter", "onCreateViewHolderCalled")
        return ParentCommentViewHolder(
            LayoutInflater.from(context).inflate(R.layout.forum_parent_comment_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: ParentCommentViewHolder, position: Int) {
        val currComment = commentList[position]
        member = memT.getMember(currComment.memId)

        holder.profilePic.setImageResource(member.profilePic)
        holder.username.text = member.username
        holder.comment.text = currComment.comment

        holder.likeBtn.setOnClickListener {
            commentLikeT.toggleLike(currComment.commentId, memId)
            notifyDataSetChanged()
        }

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

        Log.d("ParentAdapter", "${member.profilePic}, ${member.username}, " +
                currComment.comment
        )

        val allChildComments = commentT.getAllChild(currComment.commentId)
        val childAdapter = ChildCommentAdapter(context, allChildComments,forumActions, memId)

        holder.childComment.setHasFixedSize(true)
        holder.childComment.layoutManager = LinearLayoutManager(context)
        holder.childComment.adapter = childAdapter
        holder.reply.setOnClickListener{
            forumActions.onReplyClicked(currComment.commentId)
        }
        childAdapter.notifyDataSetChanged()
        Log.d("ParentAdapter", "OnBindViewHolder position = $position")
    }
}

