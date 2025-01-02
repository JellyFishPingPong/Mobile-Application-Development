package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputLayout

class EditForum : AppCompatActivity() {
    private lateinit var forumT: DataBaseHelper.Companion.ForumTable
    private var forId = -1
    private lateinit var title: TextInputLayout
    private lateinit var desc: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_forum)

        val sharedPref = this.getSharedPreferences("Farmanac", 0)!!
        forId = sharedPref.getInt("for_id", -1)

        forumT = DataBaseHelper.Companion.ForumTable(this)

        val forum = forumT.getForum(forId)

        title = findViewById(R.id.input_title)
        desc = findViewById(R.id.input_desc)

        title.editText!!.setText(forum.title, TextView.BufferType.EDITABLE)
        desc.editText!!.setText(forum.description, TextView.BufferType.EDITABLE)

        findViewById<TextView>(R.id.create_forum_top_title).text = getString(R.string.edit_post_title)

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            finish()
            onBackPressedDispatcher.onBackPressed()
        }

        val btn = findViewById<Button>(R.id.create_forum_top_button)
        btn.text = getString(R.string.save_edit_btn)

        btn.setOnClickListener {
            editForum()
        }

        findViewById<ConstraintLayout>(R.id.whole_create).setOnClickListener {
            hideInput()
        }
    }

    private fun editForum() {

        forumT.editForum(forId, title.editText!!.text.toString(), desc.editText!!.text.toString())

        finish()
        onBackPressedDispatcher.onBackPressed()
    }

    private fun hideInput() {
        currentFocus?.clearFocus()
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(findViewById<ConstraintLayout>(R.id.whole_create).windowToken, 0)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        finish()
        onBackPressedDispatcher.onBackPressed()
    }

}