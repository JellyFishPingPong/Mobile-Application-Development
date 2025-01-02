package com.example.assignment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.navigation.NavigationView

class SettingFragment : Fragment() {
    private lateinit var navigation: NavigationView
    private lateinit var delete: TextView
    private lateinit var memT: DataBaseHelper.Companion.MemberTable
    private lateinit var sharedPref: SharedPreferences
    private var memId = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_setting, container, false)

        this.sharedPref = requireActivity().getSharedPreferences("Farmanac", 0)
        memId = sharedPref.getInt("mem_id", -1)

        memT = DataBaseHelper.Companion.MemberTable(requireContext())
        delete = view.findViewById(R.id.delete_account)
        navigation = view.findViewById(R.id.settingNav)
        navigation.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.changePw -> switchFragment(ChangePwFragment())
                R.id.help -> switchFragment(HelpFragment())
                R.id.about -> switchFragment(AboutFragment())
            }
            true
        }

        delete.setOnClickListener {
            showDeleteConfirmationDialog()
        }

        return view
    }

    private fun showDeleteConfirmationDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.account_deletion_confirmation, null)

        val dialog = android.app.AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()


        dialogView.findViewById<Button>(R.id.delete_button).setOnClickListener {
            if(dialogView.findViewById<EditText>(R.id.delete_input).text.toString() == memT.getMember(memId).pw){
                deleteAccount()
                dialog.dismiss()
            } else {
                Toast.makeText(requireContext(), "Password is incorrect", Toast.LENGTH_SHORT).show()
            }
        }

        dialogView.findViewById<Button>(R.id.cancel_button).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun deleteAccount() {
        val editor = this.sharedPref.edit()
        editor.clear()
        editor.apply()
        memT.deleteMember(memId)
        Toast.makeText(requireContext(), "Account successfully deleted...", Toast.LENGTH_SHORT).show()
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
    }

    private fun switchFragment(fragment: Fragment) {
//        Log.d("fragment", "$fragment")

        val transaction = activity?.supportFragmentManager!!.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}


