package com.example.assignment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class GetStartedFragment : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_get_started, container, false)

        val backIcon = view.findViewById<ImageView>(R.id.backImage)

        backIcon.setOnClickListener{
            activity?.onBackPressedDispatcher!!.onBackPressed()
        }

        return view
    }
}