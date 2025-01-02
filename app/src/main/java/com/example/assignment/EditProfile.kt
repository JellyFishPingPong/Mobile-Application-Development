package com.example.assignment

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputLayout
import de.hdodenhof.circleimageview.CircleImageView


class EditProfile : AppCompatActivity() {
    private lateinit var memT: DataBaseHelper.Companion.MemberTable

    //variable to store old value
    private lateinit var pfp: CircleImageView
    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var contactNum: EditText
    private lateinit var occupation: EditText

    // private var id: Int = 0
    private lateinit var pw: String
    private lateinit var countrySpin: Spinner

    //variable layouts for validation
    private lateinit var usernameBox: TextInputLayout
    private lateinit var emailBox: TextInputLayout
    private lateinit var contactNumBox: TextInputLayout
    private lateinit var occupationBox: TextInputLayout
    private lateinit var countryReqMsgProfile: TextView

    //variable to store new values
    private var newPfp: Int = -1
    private lateinit var newUsername: String
    private lateinit var newEmail: String
    private lateinit var newContactNum: String
    private lateinit var newOccupation: String
    private lateinit var newCountry: String
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
//        _binding = FragmentProfileEditBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)

        //initialize variable layout
        usernameBox = this.findViewById(R.id.usernameBox)
        emailBox = this.findViewById(R.id.emailBox)
        contactNumBox = this.findViewById(R.id.contactNumBox)
        occupationBox = this.findViewById(R.id.occupationBox)
        countryReqMsgProfile = this.findViewById(R.id.countryReqMsgProfile)

        val backIcon = this.findViewById<ImageView>(R.id.backIcon)

        backIcon.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        memT = DataBaseHelper.Companion.MemberTable(this)

        val preferencesFileName = "Farmanac"
        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(preferencesFileName, 0)!!
        val id = sharedPreferences.getInt("mem_id", -1)


        //hide helperText
        //Fill in each field with user data when activity is created
        //Get member info
        val memInfo = memT.getMember(id)

        //pfp
        pfp = findViewById(R.id.edit_profile_pic)
        pfp.setImageResource(memInfo.profilePic)

        newPfp = memInfo.profilePic

        pfp.setOnClickListener {
            showImageSelectionDialog()
        }

        //username
        var changedUsername: String
        username = this.findViewById(R.id.usernameEdit)
        //display old value when activity is loaded
        username.setText(memInfo.username)
        //set old value as new value
        newUsername = memInfo.username
        //if user changes the value, assign the new value after change
        username.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //do nothing
            }

            override fun afterTextChanged(p0: Editable?) {
                changedUsername = p0.toString()

                //if the text changed, then assign it as the new input value
                if (memInfo.username != changedUsername) {
                    newUsername = changedUsername
                }
            }

        })


        //email
        var changedEmail: String
        email = this.findViewById(R.id.emailEdit)
        email.setText(memInfo.email)
        newEmail = memInfo.email
        email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //do nothing
            }

            override fun afterTextChanged(p0: Editable?) {
                changedEmail = p0.toString()
                if (memInfo.email != changedEmail) {
                    newEmail = changedEmail
                }
            }

        })

        //contact number
        var changedContactNum: String
        contactNum = this.findViewById(R.id.contactNumEdit)
        contactNum.setText(memInfo.contactNum)
        newContactNum = memInfo.contactNum
        contactNum.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //do nothing
            }

            override fun afterTextChanged(p0: Editable?) {
                changedContactNum = p0.toString()
                if (memInfo.contactNum != changedContactNum) {
                    newContactNum = changedContactNum
                }
            }

        })

        //occupation
        var changedOccupation: String
        occupation = this.findViewById(R.id.occupationEdit)
        occupation.setText(memInfo.occupation)
        newOccupation = memInfo.occupation
        occupation.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //do nothing
            }

            override fun afterTextChanged(p0: Editable?) {
                changedOccupation = p0.toString()
                if (memInfo.occupation != changedOccupation) {
                    newOccupation = changedOccupation
                }
            }

        })


        //country
        val countryChosen = memInfo.country
        Log.d("Edit Profile, Country Chosen", countryChosen)

        //Capitalize string
        val countryChosenSplit = countryChosen[0].uppercase() + countryChosen.substring(1)

        //spinner
        // var changedSpinnerPosition: Int
        val countries = resources.getStringArray(R.array.Country)
        countrySpin = this.findViewById(R.id.countrySpinnerProfile)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            countries
        )
        //set adapter for spinner
        countrySpin.adapter = adapter

        //get position of item by the item name in adapter
        val spinnerPosition: Int = adapter.getPosition(countryChosenSplit)

        //set selection for spinner
        countrySpin.setSelection(spinnerPosition)

        //initialize new Country value first. If user later chooses to change, then we assign the new selected item to newCountry
        newCountry = countries[spinnerPosition]

        //get new selection if user clicks
        countrySpin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (spinnerPosition != p2) {
                    newCountry = countries[p2]
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //do nothing
            }
        }

        //assign old pw as new
        pw = memInfo.pw

        //data validation
        usernameFocusListener(memInfo.username)
        emailFocusListener(memInfo.email)
        contactNumFocusListener()
        occupationFocusListener()

        button = findViewById(R.id.btnSaveChanges)
        button.setOnClickListener {
            if (newUsername.isEmpty() || newEmail.isEmpty() || newContactNum.isEmpty() || newOccupation.isEmpty() || newCountry.isEmpty()) {
                Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val user =
                    UserModel(memInfo.id, newPfp, newUsername, newEmail, pw, newContactNum, newOccupation, newCountry)
                val status = memT.updateMember(user)

                //check insert success or not
                if (status > -1) {
                    Toast.makeText(this, "Profile Edited...", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Profile Not Edited...", Toast.LENGTH_SHORT).show()

                }
                finish()
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    //username validation
    private fun usernameFocusListener(memName: String) {
        username.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                usernameBox.helperText = validUsername(memName)
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun validUsername(memName: String): String? {
        val usernameText1 = username.text.toString()
        val usernameText2 = usernameText1.lowercase()
        val usernameExist = memT.usernameExist(usernameText1)

        if (usernameText1 != usernameText2) {
            return "Must all be lowercase"
        }
        if (usernameExist != -1 && usernameText1 != memName) {
            return "Username already taken. Please try again !"
        }
        return null
    }

    //email validation
    private fun emailFocusListener(email: String) {
        this.email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //do nothing
                emailBox.helperText = validEmail(email)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun validEmail(email: String): String? {
        val emailText = this.email.text.toString()
        val emailExist = memT.emailExist(emailText)
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Invalid email address"
        }
        if (emailExist != -1 && emailText != email) {
            return "Email already exists. Please try again !"
        }
        return null
    }

    //contact number validation
    private fun contactNumFocusListener() {
        contactNum.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //do nothing
                contactNumBox.helperText = validContactNum()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun validContactNum(): String? {
        val contactNumText = contactNum.text.toString()
        if (!contactNumText.matches(".*\\d.*".toRegex())) {
            return "Must all be digits"
        }
        if (!(contactNumText.length == 11 || contactNumText.length == 10)) {
            return "Length range is 10 to 11 digits"
        }
        return null
    }

    //occupation validation
    private fun occupationFocusListener() {
        occupation.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                occupationBox.helperText = validOccupation()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun validOccupation(): String? {
        val occupationText = occupation.text.toString()
        if (occupationText.matches(".*\\d.*".toRegex())) {
            return "Must all be letters"
        }
        return null
    }

    private fun showImageSelectionDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_select_image, null)

        val imageViews = listOf<ImageView>(
            dialogView.findViewById(R.id.image1),
            dialogView.findViewById(R.id.image2),
            dialogView.findViewById(R.id.image3),
            dialogView.findViewById(R.id.image4),
            dialogView.findViewById(R.id.image5),
            dialogView.findViewById(R.id.image6),
            dialogView.findViewById(R.id.image7)
        )

        val selectImageButton = dialogView.findViewById<Button>(R.id.btnSelectImage)

        var selectedImageId: Int? = null

        // Set click listeners for the image views
        imageViews.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                // Set the selected image ID
                selectedImageId = index + 1

                // Update the visuals to indicate the selected image
                imageViews.forEach { iv ->
                    iv.alpha = if (iv == imageView) 1.0f else 0.5f
                }
            }
        }

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(true)
            .create()

        dialog.show()


        // Set click listener for the select button
        selectImageButton.setOnClickListener {
            // Check if an image is selected
            if (selectedImageId != null) {
                newPfp = when(selectedImageId) {
                    1-> R.drawable.woman_1
                    2-> R.drawable.woman_2
                    3-> R.drawable.woman_3
                    4-> R.drawable.man_1
                    5-> R.drawable.man_3
                    6-> R.drawable.boy
                    else -> R.drawable.baseline_account_circle_24
                }
                pfp.setImageResource(newPfp)

                dialog.dismiss()
                // Image selected, handle the selectedImageId as desired
                // For example, you can pass it to a callback function or store it in a variable
//                Toast.makeText(this, "Selected Image ID: $selectedImageId", Toast.LENGTH_SHORT).show()
            } else {
                dialog.dismiss()
                // No image selected, show an error message or perform any required action
//                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show()
            }
        }
    }

}


