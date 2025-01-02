package com.example.assignment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ForumFragment : Fragment(), ForumActions {
    private lateinit var forumT: DataBaseHelper.Companion.ForumTable
    private lateinit var forumLikeT: DataBaseHelper.Companion.ForumLikeTable
    private lateinit var memT: DataBaseHelper.Companion.MemberTable
    private lateinit var adapter: ForumAdapter
    private lateinit var forList: ArrayList<Forum>
    private var memID: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_forum, container, false)
        val preferencesFileName = "Farmanac"
        val sharedPreferences: SharedPreferences =
            activity?.getSharedPreferences(preferencesFileName, 0)!!
        memID = sharedPreferences.getInt("mem_id", -1)


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forumT = DataBaseHelper.Companion.ForumTable(requireContext())
        forumLikeT = DataBaseHelper.Companion.ForumLikeTable(requireContext())
        memT = DataBaseHelper.Companion.MemberTable(requireContext())

        val recyclerview: RecyclerView = view.findViewById(R.id.forum_recycler_view)
        val btn: Button = view.findViewById(R.id.button)

        btn.setOnClickListener{
            val intent = Intent(activity, CreateForum::class.java)
            startActivity(intent)
        }

        forList = forumT.getAllForum()

        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(view.context)
        adapter = ForumAdapter(view.context, forList, this, memID)
        recyclerview.adapter = adapter
    }


    override fun onResume() {
        super.onResume()
        forList = forumT.getAllForum()
        adapter.updateForumList(forList)
    }

    override fun onForumClicked(forID: Int) {
        val sharedPref = requireContext().getSharedPreferences("Farmanac", 0)
        val editor = sharedPref.edit()
        editor.putInt("for_id", forID)
        editor.apply()
        val intent = Intent(requireContext(), ForumDetails::class.java)
        startActivity(intent)
    }
}