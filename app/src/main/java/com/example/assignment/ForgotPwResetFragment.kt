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
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class ForgotPwResetFragment : Fragment() {

    private lateinit var newPw: EditText
    private lateinit var retypeNewPw: EditText

    private lateinit var newPwBox: TextInputLayout
    private lateinit var retypeNewPwBox: TextInputLayout

    private lateinit var newPass: String
    private lateinit var button: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_forgot_pw_reset, container, false)

        val memT = DataBaseHelper.Companion.MemberTable(requireContext())

        val preferencesFilename = "Farmanac"
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(preferencesFilename, 0)!!
        val id = sharedPreferences.getInt("mem_id", -1)

        val memInfo =  memT.getMember(id)

        //initialize
        init(view)

        //validate
        pwFocusListener(newPw, newPwBox)
        pwFocusListener(retypeNewPw, retypeNewPwBox)


        button.setOnClickListener {
            newPass = newPw.text.toString()

            if(newPass != retypeNewPw.text.toString()){
//                Log.d("ChangePwFragment", "new pw doesnt match retype")
                retypeNewPwBox.helperText = "Password does not match !"
            } else {
                retypeNewPwBox.helperText = " "
            }
            if(newPass == retypeNewPw.text.toString()){
//                Log.d("ChangePwFragment", "all conditions are met")
                val user = UserModel(memInfo.username, memInfo.email, newPass, memInfo.contactNum, memInfo.occupation, memInfo.country)
                Log.d("ChangePwFragment", "$user")
                val status = memT.changePw(id, user)

                //check insert success or not
                if (status > -1) {
                    Toast.makeText(requireContext(), "Password Changed...", Toast.LENGTH_SHORT).show()

                    //clear helpertext & fields
                    newPwBox.helperText= ""
                    retypeNewPwBox.helperText= ""
                    newPw.setText("")
                    retypeNewPw.setText("")

                    val editor = sharedPreferences.edit()
                    editor.clear()
                    editor.apply()

                    val transaction = activity?.supportFragmentManager!!.beginTransaction()
                    transaction.replace(R.id.container, Login())
                    transaction.addToBackStack(null)
                    transaction.commit()

                } else {
                    Toast.makeText(requireContext(), "Password Not Changed...", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val backImage: ImageView = view.findViewById(R.id.backImage)

        backImage.setOnClickListener{
            activity?.onBackPressedDispatcher!!.onBackPressed()
        }


        return view
    }

    private fun init(view: View) {
        newPw = view.findViewById(R.id.newPwEt)
        retypeNewPw = view.findViewById(R.id.retypeNewPwEt)
        newPwBox = view.findViewById(R.id.newPwBox)
        retypeNewPwBox = view.findViewById(R.id.retypeNewPwBox)
        button = view.findViewById(R.id.resetPw)
    }


    //password validation
    private fun pwFocusListener(pw: EditText, pwLayout: TextInputLayout) {
        pw.setOnFocusChangeListener{_, focused ->
            if(!focused){
                pwLayout.helperText = validPw(pw)
            }
        }
    }

    private fun validPw(pw: EditText): String? {
        val pwText = pw.text.toString()
        if(pwText.length < 8){
            return "Minimum 8 character password"
        }
        if(!pwText.matches(".*[A-Z].*".toRegex())){
            return "Must contain 1 upper-class character"
        }
        if(!pwText.matches(".*[a-z].*".toRegex())){
            return "Must contain 1 lower-class character"
        }
        if(!pwText.matches(".*[@#\$%^&+=.].*".toRegex())){
            return "Must contain 1 special character (@#$%^&+=.)"
        }
        return null
    }


}