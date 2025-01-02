package com.example.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class AboutPromoteSusFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_about_details, container, false)

        view.findViewById<TextView>(R.id.aboutPageTitle).text = resources.getString(R.string.promoting_sustainable_farming)
        view.findViewById<TextView>(R.id.aboutPageParagraph).text = resources.getString(R.string.promote_sustainable_farming_paragraph)

        val backImage: ImageView = view.findViewById(R.id.backImage)
        backImage.setOnClickListener{
            activity?.onBackPressedDispatcher!!.onBackPressed()
        }

        return view
    }

}