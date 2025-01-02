package com.example.assignment

import android.content.Context
import android.icu.util.TimeZone
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class ForumAdapter() : RecyclerView.Adapter<ForumViewHolder>() {
    private lateinit var context: Context
    private lateinit var forums: ArrayList<Forum>
    private lateinit var member: UserModel
    private lateinit var forumActions: ForumActions
    private var memID: Int = -1

    private lateinit var forumLikesT: DataBaseHelper.Companion.ForumLikeTable
    private lateinit var memT: DataBaseHelper.Companion.MemberTable
    private lateinit var reportT: DataBaseHelper.Companion.ForumReportTable
    private lateinit var commentT: DataBaseHelper.Companion.ForumCommentTable

    constructor(
        context: Context,
        forums: ArrayList<Forum>,
        forumActions: ForumActions,
        memID: Int,
    ) : this() {
        this.context = context
        this.forumLikesT = DataBaseHelper.Companion.ForumLikeTable(context)
        this.memT = DataBaseHelper.Companion.MemberTable(context)
        this.reportT = DataBaseHelper.Companion.ForumReportTable(context)
        this.commentT = DataBaseHelper.Companion.ForumCommentTable(context)
        this.forumActions = forumActions
        this.memID = memID

        this.forums = removeReported(forums)
    }

    private fun removeReported(forums: ArrayList<Forum>): ArrayList<Forum> {
        for (i in forums.size - 1 downTo 0) {
            val forum = forums[i]
            // Perform your condition check
            if (reportT.isReported(forum.id, memID)) {
                // Remove the item from the list
                forums.removeAt(i)
            }
        }

        return forums
    }

    fun updateForumList(forums: ArrayList<Forum>) {
        this.forums = removeReported(forums)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumViewHolder {
        return ForumViewHolder(
            LayoutInflater.from(context).inflate(R.layout.forum_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ForumViewHolder, position: Int) {
        val currForum: Forum = forums[position]
        member = memT.getMember(currForum.mem_id)
        Log.d("FA", "${member.profilePic}")
        holder.profilePic.setImageResource(member.profilePic)
        holder.username.text = member.username
        // Time Zone Test
        // Get System TimeZone
        val mytZone = ZoneId.of(TimeZone.getDefault().id)

        // Define the date format of the input string
        val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val displayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

        // Define the input string
        val inputString = currForum.uploadDate

        val date = LocalDateTime.parse(inputString, inputFormat)
        val fortZone = ZoneId.of(currForum.timeZone).rules.getOffset(date)

        val formattedDate = date
            .atOffset(fortZone)
            .atZoneSameInstant(mytZone)
            .format(displayFormat)

        holder.uploadDate.text = formattedDate
        holder.forumTitle.text = currForum.title
        holder.forumContent.text = currForum.description
        holder.likes.text = forumLikesT.getLikeAmount(currForum.id).toString()
        holder.likeIcon.isChecked = forumLikesT.liked(currForum.id, memID)
        holder.likeIcon.setOnClickListener {
            forumLikesT.toggleLike(currForum.id, memID)
            notifyDataSetChanged()
        }
        holder.entireForum.setOnClickListener {
            forumActions.onForumClicked(currForum.id)
        }

        holder.forumComment.text = commentT.getAllComment(currForum.id).size.toString()

    }

    override fun getItemCount(): Int {
        return forums.size
    }

}