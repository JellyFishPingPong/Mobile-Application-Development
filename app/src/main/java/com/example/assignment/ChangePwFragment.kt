package com.example.assignment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

class ChangePwFragment : Fragment() {

    private lateinit var currentPw: EditText
    private lateinit var newPw: EditText
    private lateinit var retypeNewPw: EditText

    private lateinit var currentPwBox: TextInputLayout
    private lateinit var newPwBox: TextInputLayout
    private lateinit var retypeNewPwBox: TextInputLayout

    private lateinit var newPass: String


    private lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_change_pw, container, false)

        val memT = DataBaseHelper.Companion.MemberTable(requireContext())

        val preferencesFileName = "Farmanac"
        val sharedPreferences = requireActivity().getSharedPreferences(preferencesFileName, 0)!!
        val id = sharedPreferences.getInt("mem_id", -1)

        //get member info
        val memInfo = memT.getMember(id)
        val memPw = memInfo.pw

        //initialize
        init(view)

        //validate
        pwFocusListener(newPw, newPwBox)
        pwFocusListener(retypeNewPw, retypeNewPwBox)


        button.setOnClickListener {
            newPass = newPw.text.toString()

            if (currentPw.text.toString() != memPw) {
                currentPwBox.helperText = "Incorrect password !"
            } else {
                currentPwBox.helperText = " "
            }
            if (newPass != retypeNewPw.text.toString()) {
                retypeNewPwBox.helperText = "Password does not match !"
            } else {
                retypeNewPwBox.helperText = " "
            }
            if (currentPw.text.toString() == memPw && newPass == retypeNewPw.text.toString()) {
                val user = UserModel(
                    memInfo.username,
                    memInfo.email,
                    newPass,
                    memInfo.contactNum,
                    memInfo.occupation,
                    memInfo.country
                )
                Log.d("ChangePwFragment", "$user")
                val status = memT.changePw(id, user)

                //check insert success or not
                if (status > -1) {
                    Toast.makeText(requireContext(), "Password Changed...", Toast.LENGTH_SHORT)
                        .show()

                    //clear helperText & fields
                    currentPwBox.helperText = ""
                    newPwBox.helperText = ""
                    retypeNewPwBox.helperText = ""
                    currentPw.setText("")
                    newPw.setText("")
                    retypeNewPw.setText("")

                } else {
                    Toast.makeText(requireContext(), "Password Not Changed...", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        val backImage: ImageView = view.findViewById(R.id.backImage)

        backImage.setOnClickListener {
            activity?.onBackPressedDispatcher!!.onBackPressed()
        }

        return view
    }

    private fun init(view: View) {
        currentPw = view.findViewById(R.id.currentPwEt)
        newPw = view.findViewById(R.id.newPwEt)
        retypeNewPw = view.findViewById(R.id.retypeNewPwEt)
        currentPwBox = view.findViewById(R.id.currentPwBox)
        newPwBox = view.findViewById(R.id.newPwBox)
        retypeNewPwBox = view.findViewById(R.id.retypeNewPwBox)
        button = view.findViewById(R.id.changePwButton)
    }

    //password validation
    private fun pwFocusListener(pw: EditText, pwBox: TextInputLayout) {

        pw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.isEmpty() == true) {
                    pwBox.helperText = getString(R.string.required)
                } else {
                    pwBox.helperText = validPw(pw)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text has changed
            }
        })
    }

    private fun validPw(pw: EditText): String? {
        val pwText = pw.text.toString()
        if (pwText.length < 8) {
            return "Minimum 8 character password"
        }
        if (!pwText.matches(".*[A-Z].*".toRegex())) {
            return "Must contain 1 upper-class character"
        }
        if (!pwText.matches(".*[a-z].*".toRegex())) {
            return "Must contain 1 lower-class character"
        }
        if (!pwText.matches(".*[@#\$%^&+=.].*".toRegex())) {
            return "Must contain 1 special character (@#$%^&+=.)"
        }
        return null
    }

}
