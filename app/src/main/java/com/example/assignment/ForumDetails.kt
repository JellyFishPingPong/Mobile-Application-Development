package com.example.assignment

import android.app.AlertDialog
import android.content.Intent
import android.icu.util.TimeZone
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetDialog

class ForumDetails : AppCompatActivity(), ForumActions {
    private lateinit var forumT: DataBaseHelper.Companion.ForumTable
    private lateinit var forumCommentT: DataBaseHelper.Companion.ForumCommentTable
    private lateinit var forumLikeT: DataBaseHelper.Companion.ForumLikeTable
    private lateinit var memT: DataBaseHelper.Companion.MemberTable
    private lateinit var commentLikeT: DataBaseHelper.Companion.CommentLikeTable
    private lateinit var reportT: DataBaseHelper.Companion.ForumReportTable

    private lateinit var forum: Forum
    private lateinit var member: UserModel

    private lateinit var backButton: ImageView
    private lateinit var profilePic: ImageView
    private lateinit var username: TextView
    private lateinit var title: TextView
    private lateinit var desc: TextView
    private lateinit var commentRecyclerView: RecyclerView
    private lateinit var commentInput: EditText
    private lateinit var uploadDate: TextView
    private lateinit var likeButton: LinearLayout
    private lateinit var like: CheckBox
    private lateinit var commentButton: LinearLayout
    private lateinit var commentDefault: TextView
    private lateinit var sendButton: ImageButton
    private lateinit var rootView: RelativeLayout
    private lateinit var adapter: ParentCommentAdapter
    private lateinit var actions: ConstraintLayout
    private lateinit var edit: ImageView
    private lateinit var delete: ImageView
    private lateinit var report: ImageView

    private var forId: Int = -1
    private var memId: Int = -1

    private var replyingTo = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forum_details)

        val sharedPref = this.getSharedPreferences("Farmanac", 0)
        forId = sharedPref.getInt("for_id", -1)
        memId = sharedPref.getInt("mem_id", -1)

        init()

        sendButton.isEnabled = false

        sendButton.setOnClickListener {
            forumCommentT.insertComment(
                Comment(
                    memId,
                    forId,
                    replyingTo,
                    commentInput.text.toString()
                )
            )
            val commentList = forumCommentT.getAllParent(forId)
            adapter.setCommentList(commentList)
            currentFocus?.clearFocus()
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(rootView.windowToken, 0)
            Log.d("ForumDetails", "${adapter.itemCount}")

            commentInput.text = null

            val parentCommentList = forumCommentT.getAllParent(forId)

            if(parentCommentList.size > 0) {
                commentDefault.text = null
            }
//            Toast.makeText(this, "pressed sent", Toast.LENGTH_SHORT).show()
        }

        commentButton.setOnClickListener {
            commentInput.requestFocus()
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(commentInput, InputMethodManager.SHOW_IMPLICIT)
            replyingTo = 0
        }

        commentInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sendButton.isEnabled = s?.isEmpty() != true
            }

            override fun afterTextChanged(p0: Editable?) {
                return
            }
        })

        rootView.setOnClickListener {
            // clear focus on the view that currently has it
            currentFocus?.clearFocus()
            // hide the keyboard if it is showing
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(rootView.windowToken, 0)
        }


        backButton.setOnClickListener {
            back()
        }

        setForumDetails()

        val mytZone = ZoneId.of(TimeZone.getDefault().id)
        val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val displayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val inputString = forum.uploadDate
        val date = LocalDateTime.parse(inputString, inputFormat)
        val fortZone = ZoneId.of(forum.timeZone).rules.getOffset(date)

        val formattedDate = date
            .atOffset(fortZone)
            .atZoneSameInstant(mytZone)
            .format(displayFormat)

        uploadDate.text = formattedDate

        val parentCommentList = forumCommentT.getAllParent(forId)

        if(parentCommentList.size > 0) {
            commentDefault.text = null
        }

        commentRecyclerView.setHasFixedSize(true)
        commentRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ParentCommentAdapter(this, parentCommentList, this, memId)
        commentRecyclerView.adapter = adapter

        likeButton.setOnClickListener {
            like()
        }
        like.setOnClickListener {
            like()
        }

        forumAction()

    }

    private fun setForumDetails() {
        forum = forumT.getForum(forId)
        member = memT.getMember(forum.mem_id)

        profilePic.setImageResource(member.profilePic)
        username.text = member.username
        title.text = forum.title
        desc.text = forum.description
    }

    private fun forumAction() {
        if (forum.mem_id == memId) {
            actions.visibility = View.VISIBLE
            delete.setOnClickListener {
//                forumT.deleteForum(forId)
                showDeleteConfirmationDialog()
//                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show()
//                back()
            }
            edit.setOnClickListener {
//                Toast.makeText(this, "edit", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, EditForum::class.java)
                startActivity(intent)
            }
        } else {
            report.visibility = View.VISIBLE
            report.setOnClickListener {
                showBottomDialog()
//                Toast.makeText(this, "report", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun like() {
        Log.d("FD", "$forId, $memId")
        like.isChecked = forumLikeT.liked(forId, memId)
        forumLikeT.toggleLike(forId, memId)
        like.isChecked = forumLikeT.liked(forId, memId)
    }

    override fun onReplyClicked(parID: Int) {
        commentInput.requestFocus()
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(commentInput, InputMethodManager.SHOW_IMPLICIT)
        replyingTo = parID
        Log.d("ForumDetails", "$replyingTo")
    }

    private fun back() {
        val sharedPref = this.getSharedPreferences("Farmanac", 0)
        val editor = sharedPref.edit()
        editor.remove("for_id")
        editor.apply()
        finish()
        onBackPressedDispatcher.onBackPressed()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        back()
    }

    override fun onResume() {
        Log.d("ForumDetails", "onResume Called")
        setForumDetails()
        super.onResume()
    }

    private fun init() {
        forumT = DataBaseHelper.Companion.ForumTable(this)
        forumCommentT = DataBaseHelper.Companion.ForumCommentTable(this)
        memT = DataBaseHelper.Companion.MemberTable(this)
        forumLikeT = DataBaseHelper.Companion.ForumLikeTable(this)
        commentLikeT = DataBaseHelper.Companion.CommentLikeTable(this)
        reportT = DataBaseHelper.Companion.ForumReportTable(this)

        backButton = findViewById(R.id.forum_details_back)
        profilePic = findViewById(R.id.forum_details_pfp)
        username = findViewById(R.id.forum_details_username)
        uploadDate = findViewById(R.id.forum_details_upload_date)
        title = findViewById(R.id.forum_details_title)
        desc = findViewById(R.id.forum_details_desc)
        commentRecyclerView = findViewById(R.id.forum_details_comment)
        rootView = findViewById(R.id.forum_details_root)
        sendButton = findViewById(R.id.forum_details_send)
        commentInput = findViewById(R.id.forum_details_comment_input)
        commentButton = findViewById(R.id.forum_details_comment_button)
        commentDefault = findViewById(R.id.forum_details_comment_default)
        actions = findViewById(R.id.forum_details_actions)
        edit = findViewById(R.id.forum_details_edit)
        delete = findViewById(R.id.forum_details_delete)
        report = findViewById(R.id.forum_details_report)
        like = findViewById(R.id.forum_details_like)
        likeButton = findViewById(R.id.forum_details_like_button)
    }

    private fun showDeleteConfirmationDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.forum_delete_confirmation, null)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        dialogView.findViewById<Button>(R.id.delete_button).setOnClickListener {
            forumT.deleteForum(forId)
            dialog.dismiss()
            back()
        }

        dialogView.findViewById<Button>(R.id.cancel_button).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showBottomDialog() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.forum_report_dialog, null)
        bottomSheetDialog.setContentView(view)

        val reportBtn = view.findViewById<Button>(R.id.dialog_report_button)
        val radioGroup = view.findViewById<RadioGroup>(R.id.report_radio_group)
        Log.d("RadioGroup", "$radioGroup")

        val margin = resources.getDimensionPixelSize(R.dimen.radio_margin)
        val layoutParams = RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,
            RadioGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(0, margin, 0, 0)
        val textSize = resources.getDimensionPixelSize(R.dimen.radio_text_size)
        val options = resources.getStringArray(R.array.report_reasons)
        for(i in options.indices) {
            val radioButton = RadioButton(this)
            radioButton.text = options[i]
            radioButton.id = i
            radioButton.layoutParams = layoutParams
            radioButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
            radioGroup.addView(radioButton)
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = view.findViewById<RadioButton>(checkedId)
            val reason = radioButton.text.toString()
            reportBtn.isEnabled = true
            reportBtn.setOnClickListener {
                Log.d("ForumDetails", reason)

                val report = ForumReport(forId, memId, reason)
                Toast.makeText(this, "Thanks for your feedback!", Toast.LENGTH_SHORT).show()
                reportT.reportForum(report)
                bottomSheetDialog.dismiss()
                back()
            }
        }

        reportBtn.isEnabled = false

        bottomSheetDialog.setCancelable(true)
        bottomSheetDialog.show()
    }

    override fun onStop() {
        super.onStop()
        DataBaseHelper(this).close()
    }
}