package com.example.assignment

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

class ForgotPwFragment : Fragment() {

    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var emailLayout: TextInputLayout
    private lateinit var button: Button
    private lateinit var memT: DataBaseHelper.Companion.MemberTable
    private lateinit var memInfo: UserModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_forgot_pw, container, false)

        //initialize
        memT = DataBaseHelper.Companion.MemberTable(requireContext())
        username = view.findViewById(R.id.username)
        email = view.findViewById(R.id.email)
        emailLayout = view.findViewById(R.id.emailBox)

        button = view.findViewById(R.id.verifyEmail)
        button.setOnClickListener {

            val usernameInput = username.text.toString()
            val emailInput = email.text.toString()

            memInfo = memT.getMemberViaUsername(usernameInput)
            val memUsername = memInfo.username
            val memEmail = memInfo.email

            Log.d("ForgotPwFragment, User input", "$usernameInput, $emailInput")
            Log.d("ForgotPwFragment, User existing data", "$memUsername, $memEmail")
            //check if username and email for this specific user matches
            if(usernameInput != memUsername && emailInput != memEmail){
                emailLayout.helperText="Invalid username or email"
            } else {
                val preferencesFilename = "Farmanac"
                val sharedPreferences: SharedPreferences =
                    requireActivity().getSharedPreferences(preferencesFilename, 0)!!
                val editor = sharedPreferences.edit()
                editor.putInt("mem_id", memInfo.id)
                editor.apply()

                val transaction = activity?.supportFragmentManager!!.beginTransaction()
                transaction.replace(R.id.container, ForgotPwResetFragment())
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }

        return view
    }

}