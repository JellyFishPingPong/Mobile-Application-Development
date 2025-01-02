package com.example.assignment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.MenuItemCompat.setActionView
import com.google.android.material.navigation.NavigationView

class AboutFragment : Fragment() {
    private lateinit var navigation: NavigationView
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_about, container, false)

        val backImage: ImageView = view.findViewById(R.id.backImage)
        backImage.setOnClickListener{
            activity?.onBackPressedDispatcher!!.onBackPressed()
        }

        navigation = view.findViewById(R.id.abtFarmanacNav)
        navigation.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.mission -> switchFragment(AboutMissionFragment())
                R.id.promoteSustainableFarming -> switchFragment(AboutPromoteSusFragment())
                R.id.educationalResource -> switchFragment(AboutEducationResFragment())
                R.id.communityCollaboration -> switchFragment(AboutComColFragment())
                R.id.zeroHunger -> switchFragment(AboutZeroHunFragment())
                R.id.joinFarmanac -> switchFragment(AboutJoinFragment())
            }
            true
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