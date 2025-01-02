package com.example.assignment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.android.material.navigation.NavigationView

class HelpFragment : Fragment() {

    private lateinit var navigation: NavigationView
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_help, container, false)

        navigation = view.findViewById(R.id.helpNav)
        navigation.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.faq-> switchFragment(FaqFragment())
                R.id.getStarted -> switchFragment(GetStartedFragment())
                R.id.troubleshoot -> switchFragment(TroubleshootFragment())
                R.id.privacy-> switchFragment(PrivacyFragment())
            }
            true
        }

        val backIcon = view.findViewById<ImageView>(R.id.backImage)

        backIcon.setOnClickListener{
            activity?.onBackPressedDispatcher!!.onBackPressed()
        }
        return view
    }

    private fun switchFragment(fragment: Fragment){
        val transaction = activity?.supportFragmentManager!!.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}