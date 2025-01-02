package com.example.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class PrivacyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_privacy, container, false)

        view.findViewById<ImageView>(R.id.backImage).setOnClickListener {
            activity?.onBackPressedDispatcher!!.onBackPressed()
        }

        return view
    }

}