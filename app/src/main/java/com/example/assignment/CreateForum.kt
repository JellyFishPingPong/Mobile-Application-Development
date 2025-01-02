package com.example.assignment

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputLayout

class CreateForum : AppCompatActivity() {
    private lateinit var forumT: DataBaseHelper.Companion.ForumTable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_forum)

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            finish()
            onBackPressedDispatcher.onBackPressed()
        }

        val btn = findViewById<Button>(R.id.create_forum_top_button)

        btn.setOnClickListener {
            createForum()
        }

        findViewById<ConstraintLayout>(R.id.whole_create).setOnClickListener {
            hideInput()
        }
    }

    private fun createForum() {
        forumT = DataBaseHelper.Companion.ForumTable(this)

        val preferencesFileName = "Farmanac"
        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(preferencesFileName, 0)!!
        val id = sharedPreferences.getInt("mem_id", -1)
        val title = findViewById<TextInputLayout>(R.id.input_title)
        val desc = findViewById<TextInputLayout>(R.id.input_desc)

        val forum = Forum(id, title.editText!!.text.toString(), desc.editText!!.text.toString())

        forumT.insertForum(forum)

        finish()
        onBackPressedDispatcher.onBackPressed()

        Log.d("CreateForum", "$id")
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

    override fun onStop() {
        super.onStop()
        DataBaseHelper(this).close()
    }
}