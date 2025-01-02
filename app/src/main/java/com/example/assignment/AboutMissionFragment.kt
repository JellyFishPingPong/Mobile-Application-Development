package com.example.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class AboutMissionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_about_details, container, false)

        view.findViewById<TextView>(R.id.aboutPageTitle).text = resources.getString(R.string.our_mission)
        view.findViewById<TextView>(R.id.aboutPageParagraph).text = resources.getString(R.string.missionParagraph)

        val backImage: ImageView = view.findViewById(R.id.backImage)
        backImage.setOnClickListener{
            activity?.onBackPressedDispatcher!!.onBackPressed()
        }

        return view
    }

}