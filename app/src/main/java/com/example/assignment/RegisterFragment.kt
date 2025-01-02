package com.example.assignment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils.isEmpty
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputLayout

class RegisterFragment : Fragment() {
    //declare edittext
    private lateinit var username: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var pw: TextInputEditText
    private lateinit var contactNum: TextInputEditText
    private lateinit var occupation: TextInputEditText
    private lateinit var register: Button
    private lateinit var toLoginLink: TextView

    //declare text input layout
    private lateinit var usernameLayout: TextInputLayout
    private lateinit var emailLayout: TextInputLayout
    private lateinit var pwLayout: TextInputLayout
    private lateinit var contactNumlayout: TextInputLayout
    private lateinit var occupationLayout: TextInputLayout

    //declare spinner & other related view
    private lateinit var countryReqMsg: TextView

    private lateinit var memT: DataBaseHelper.Companion.MemberTable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_register, container, false)
        val countries = resources.getStringArray(R.array.Country)

        //Spinner
        val spinner = view.findViewById<Spinner>(R.id.countrySpinner)
        if(spinner != null) {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, countries)
            spinner.adapter = adapter

//            spinner.setOnFocusChangeListener{_, focused ->
//                if(!focused){
//                    //assign selected item to countrySelected
//                    var countrySelected = spinner?.selectedItem.toString()
//                    Toast.makeText(requireContext(), "Selected country: " + countrySelected, Toast.LENGTH_SHORT).show()
//                }
//
//            }

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                   //if position is not first item and it is not user's first click
                    if(position != 0)
                    {
                        //set required text to null
                        countryReqMsg.text = ""
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }

        initView(view)

        memT = DataBaseHelper.Companion.MemberTable(requireContext())

        //data validation
        usernameFocusListener()
        emailFocusListener()
        pwFocusListener()
        contactNumFocusListener()
        occupationFocusListener()

        //register
        register.setOnClickListener {
            val verified = lastValidation(spinner)
            if(verified){
                addUser(spinner)
                val transaction = activity?.supportFragmentManager!!.beginTransaction()
                transaction.replace(R.id.container, Login())
                transaction.addToBackStack(null)
                transaction.commit()

            }

        }

        toLoginLink.setOnClickListener {
            val transaction = activity?.supportFragmentManager!!.beginTransaction()
            transaction.replace(R.id.container, Login())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view

    }

    //validation when click register button
    private fun lastValidation(spinner: Spinner): Boolean {
        //do data validation for each field
        usernameLayout.helperText = validUsername()
        emailLayout.helperText = validEmail()
        pwLayout.helperText = validPw()
        contactNumlayout.helperText = validContactNum()

        //check if they are null
        val validUsername = usernameLayout.helperText == null
        val validEmail = emailLayout.helperText == null
        val validPassword = pwLayout.helperText == null
        val validPhone = contactNumlayout.helperText == null
        val validOccupation = occupationLayout.helperText == null
        val validCountry = isEmpty(countryReqMsg.text)

        Log.d("validate", "$validUsername, $validEmail, $validPassword, $validPhone, $validOccupation, $validCountry")

        if (validUsername && validEmail && validPassword && validPhone && validOccupation && validCountry) {
            resetForm(spinner)
            return true
        }
        else {
            invalidForm()
        }
        return false
    }

    private fun invalidForm() {
        var message = ""
        if(usernameLayout.helperText != null) {
            message += "\nUsername: " + usernameLayout.helperText
            username.text =  null
        }
        if(emailLayout.helperText != null) {
            //show that field & its corresponding error
            message += "\nEmail: " + emailLayout.helperText
            //reset that field
            email.text =  null
        }
        if(pwLayout.helperText != null) {
            message += "\nPassword: " + pwLayout.helperText
            pw.text =  null
        }
        if(contactNumlayout.helperText != null) {
            message += "\nContact Number: " + contactNumlayout.helperText
            contactNum.text =  null
        }
        if(occupationLayout.helperText != null) {
            message += "\nContact Number: " + occupationLayout.helperText
            occupation.text =  null
        }
        message += "\nCountry: " + countryReqMsg.text

        AlertDialog.Builder(requireContext())
            .setTitle("Invalid Form")
            .setMessage(message)
            .setPositiveButton("text"){ _,_ ->
                //do nothing

            }.show()
    }

    private fun resetForm(spinner: Spinner) {
        val password = pw.text
        val encryptedPw = password?.let {
            password[0].toString().padEnd(it.length, '*')
        }

        var message = "Username: " + username.text
        message += "\nEmail: " + email.text
        message += "\nPassword: $encryptedPw"
        message += "\nContact Number: " + contactNum.text
        message += "\nOccupation: " + occupation.text
        message += "\nCountry: " + spinner.selectedItem.toString()

        //reset the helper text after successful submission
        usernameLayout.helperText = getString(R.string.required)
        emailLayout.helperText = getString(R.string.required)
        pwLayout.helperText = getString(R.string.required)
        contactNumlayout.helperText = getString(R.string.required)
        occupationLayout.helperText = getString(R.string.required)

        /*
        AlertDialog.Builder(requireContext())
            .setTitle("Form Submitted")
            .setMessage(message)
            .setPositiveButton("text"){ _,_ ->

                //set all back to null after successful submission
                clearEditText()


                //reset spinner (not working here .. T_T)
                spinner.setSelection(0)
                countryReqMsg.setText("Required*")

            }.show()*/
    }
    //occupation validation
    private fun occupationFocusListener() {

        occupation.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.isEmpty() == true) {
                    occupationLayout.helperText = getString(R.string.required)
                } else {
                    occupationLayout.helperText = validOccupation()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text has changed
            }
        })
    }

    private fun validOccupation(): String? {
        val occupationText = occupation.text.toString()
        if(occupationText.matches(".*\\d.*".toRegex())){
            return "Must all be letters"
        }
        return null
    }


    //contact number validation
    private fun contactNumFocusListener() {
        contactNum.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.isEmpty() == true) {
                    contactNumlayout.helperText = getString(R.string.required)
                } else {
                    contactNumlayout.helperText = validContactNum()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text has changed
            }
        })
    }

    private fun validContactNum(): String? {
        val contactNumtext = contactNum.text.toString()
        if (!contactNumtext.matches(".*\\d.*".toRegex())){
            return "Must all be digits"
        }
        if(!(contactNumtext.length == 11 || contactNumtext.length == 10)){
            return "Length range is 10 to 11 digits"
        }
        return null
    }

    //password validation
    private fun pwFocusListener() {
        pw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.isEmpty() == true) {
                    pwLayout.helperText = getString(R.string.required)
                } else {
                    pwLayout.helperText = validPw()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text has changed
            }
        })
    }

    private fun validPw(): String? {
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

    //email validation
    private fun emailFocusListener() {
        email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.isEmpty() == true) {
                    emailLayout.helperText = getString(R.string.required)
                } else {
                    emailLayout.helperText = validEmail()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text has changed
            }
        })
    }

    private fun validEmail(): String? {
        val emailText = email.text.toString()
        val emailExist = memT.emailExist(emailText)
        if(! Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            return "Invalid email address"
        }
        if(emailExist != -1) {
            return "Email already exists. Please try again !"
        }
        return null
    }


    //username validation
    private fun usernameFocusListener() {
        username.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.isEmpty() == true) {
                    usernameLayout.helperText = getString(R.string.required)
                } else {
                usernameLayout.helperText = validUsername()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text has changed
            }
        })
    }

    private fun validUsername(): String? {
        val usernameText1 = username.text.toString()
        val usernameText2= usernameText1.lowercase()
        val usernameExist = memT.usernameExist(usernameText1)

        if (usernameText1 != usernameText2){
            return "Must all be lowercase"
        }

        if(usernameExist != -1) {
            return "Username already taken. Please try again !"
        }
        return null
    }


    private fun addUser(spinner: Spinner) {
        val username = username.text.toString()
        val email = email.text.toString()
        val pw = pw.text.toString()
        val contactNumUser = contactNum.text.toString()
        val occupation = occupation.text.toString()
        val country = spinner.selectedItem.toString()

        if (username.isEmpty() || email.isEmpty() || pw.isEmpty() || contactNumUser.isEmpty() || occupation.isEmpty() || country.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter required field", Toast.LENGTH_SHORT)
                .show()
        } else {
            val user = UserModel(username, email, pw, contactNumUser, occupation, country)
            val status = memT.insertMember(user)

            //check insert success or not
            if (status > -1) {
                Toast.makeText(requireContext(), "User added...", Toast.LENGTH_SHORT).show()
                clearEditText()
            } else {
                Toast.makeText(requireContext(), "Record not saved...", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun clearEditText() {
        username.setText("")
        email.setText("")
        pw.setText("")
        contactNum.setText("")
        occupation.setText("")
        username.requestFocus()
    }

    private fun initView(view: View) {
        //initialize edittext
        username = view.findViewById(R.id.username)
        email = view.findViewById(R.id.email)
        pw = view.findViewById(R.id.pw)
        contactNum = view.findViewById(R.id.contactNum)
        occupation = view.findViewById(R.id.occupation)
        register = view.findViewById(R.id.btnRegister)
        toLoginLink = view.findViewById(R.id.toLoginLink)

        //initialize text input layout
        usernameLayout = view.findViewById(R.id.usernameBox)
        emailLayout = view.findViewById(R.id.emailBox)
        pwLayout = view.findViewById(R.id.pwBox)
        contactNumlayout = view.findViewById(R.id.contactNumBox)
        occupationLayout = view.findViewById(R.id.occupationBox)

        //initialize spinner
//        countrySpinner = view.findViewById(R.id.countrySpinner)
        countryReqMsg = view.findViewById(R.id.countryReqMsg)
    }


}