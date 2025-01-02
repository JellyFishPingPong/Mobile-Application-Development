package com.example.assignment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class ProfileMainFragment : Fragment(),ForumActions {

    private lateinit var username: TextView
    private lateinit var memT: DataBaseHelper.Companion.MemberTable
    private lateinit var forT: DataBaseHelper.Companion.ForumTable

    private lateinit var posts: TextView
    private lateinit var likes: TextView
    private lateinit var empty: TextView
    private lateinit var rv: RecyclerView
    private lateinit var pfp: ImageView

    private var memID: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profile_main, container, false)

        val preferencesFileName = "Farmanac"
        val sharedPreferences: SharedPreferences =
            activity?.getSharedPreferences(preferencesFileName, 0)!!
        memID = sharedPreferences.getInt("mem_id", -1)
        rv = view.findViewById(R.id.profile_main_rv)
        pfp = view.findViewById(R.id.profilePic)

        memT = DataBaseHelper.Companion.MemberTable(requireContext())
        forT = DataBaseHelper.Companion.ForumTable(requireContext())

        val memInfo = memT.getMember(memID)
        //assign username
        username = view.findViewById(R.id.usernameText)
        username.text = memInfo.username
        pfp.setImageResource(memInfo.profilePic)

        return view
    }

    private fun setContent(forums: ArrayList<Forum>) {
        if(forums.size == 0) {
            empty.visibility = View.VISIBLE
        } else {
            empty.visibility = View.GONE
        }

        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = ForumAdapter(requireContext(), forums, this, memID)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        memT = DataBaseHelper.Companion.MemberTable(requireContext())

        val editProfile = view.findViewById<Button>(R.id.btnEditProfile_profile_main)
        editProfile.setOnClickListener {
            val intent = Intent(activity, EditProfile::class.java)
            startActivity(intent)
        }

        posts = view.findViewById(R.id.profile_main_posts)
        likes = view.findViewById(R.id.profile_main_likes)
        empty = view.findViewById(R.id.profile_main_empty)

        setContent(forT.getAllPostedForum(memID))
        setHover(posts, likes)

        posts.setOnClickListener {
            setContent(forT.getAllPostedForum(memID))
            setHover(posts, likes)
        }

        likes.setOnClickListener {
            setContent(forT.getAllLikedForum(memID))
            setHover(likes, posts)
        }
    }

    private fun setHover(positive: TextView, negative: TextView) {
        positive.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_app_color))
        positive.setTextColor(ContextCompat.getColor(requireContext(), R.color.cream))

        negative.background = ContextCompat.getDrawable(requireContext(), R.drawable.layout_border)
        negative.setTextColor(ContextCompat.getColor(requireContext(), R.color.darker_grey))
    }

    override fun onForumClicked(forID: Int) {
        val sharedPref = requireContext().getSharedPreferences("Farmanac", 0)
        val editor = sharedPref.edit()
        editor.putInt("for_id", forID)
        editor.apply()
        val intent = Intent(requireContext(), ForumDetails::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        setContent(forT.getAllPostedForum(memID))
        setHover(posts, likes)

        val memInfo = memT.getMember(memID)
        pfp.setImageResource(memInfo.profilePic)
        username.text = memInfo.username
    }
}