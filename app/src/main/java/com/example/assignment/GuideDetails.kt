package com.example.assignment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class GuideDetails : Fragment() {

    private lateinit var guiT:DataBaseHelper.Companion.GuideTable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.guide_detail, container, false)
        val backButton=view.findViewById<Button>(R.id.back_button)
        guiT = DataBaseHelper.Companion.GuideTable(requireContext())

        //get veg id
        val preferencesFileName = "Farmanac"
        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences(preferencesFileName, 0)!!

        val id = sharedPreferences.getInt("veg_id", -1)
        val valueGuide=guiT.getDetail(id)

        val vegSelected = valueGuide
        val title =view.findViewById<TextView>(R.id.title)
        title.text = vegSelected.vegetableName
        val display=view.findViewById<ImageView>(R.id.imageView)
        display.setImageResource(vegSelected.image)
        val method=view.findViewById<TextView>(R.id.method)
        method.text=vegSelected.method
        val care=view.findViewById<TextView>(R.id.content)
        care.text=vegSelected.veg_content

        backButton.setOnClickListener{
            val targetFragment = GuideFragment()
            val transaction = activity?.supportFragmentManager!!.beginTransaction()
            transaction.replace(R.id.container, targetFragment)
            transaction.addToBackStack(null)
            transaction.commit() //提交事务
        }

        // Inflate the layout for this fragment
        return view
    }


}
