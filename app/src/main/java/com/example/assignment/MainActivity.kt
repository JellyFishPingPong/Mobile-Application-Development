package com.example.assignment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {
    private lateinit var memInfo: UserModel
    private lateinit var memT: DataBaseHelper.Companion.MemberTable
    private var memId = -1
    private lateinit var navView: NavigationView
    private lateinit var cover: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferencesFileName = "Farmanac"
        val sharedPref =
            this.getSharedPreferences(preferencesFileName, 0)!!
        memId = sharedPref.getInt("mem_id", -1)

//        val editor = sharedPref.edit()
//        editor.clear()
//        editor.apply()
//
//        val db = DataBaseHelper(this)
//        db.close()
//        this.deleteDatabase("farmanac.db")
//        finish()
//
//        val conT = DataBaseHelper.Companion.ConsultantTable(this)
//        val consultantData = Consultant(0, "")
//        for (x in consultantData.getConsultant) {
//            Log.d("consultant", "${conT.insertConsultant(x)}")
//        }
//
//        val guideT = DataBaseHelper.Companion.GuideTable(this)
//        val guideData = GuideData(0, "")
//        for (x in guideData.getVeg) {
//            Log.d("guide", "${guideT.insertGuideDetail(x)}")
//        }
//
//        val borrowT = DataBaseHelper.Companion.BorrowTable(this)
//        val borrowData = BorrowData("", "", "")
//        for (x in borrowData.getBorrow) {
//            Log.d("borrow", "${borrowT.insertBorrowDetail(x)}")
//        }

        memT = DataBaseHelper.Companion.MemberTable(this)
        memInfo = memT.getMember(memId)
        Log.d("Main Activity", "$memId")

        if (memId != -1) {
            Log.d("MainActivity", "begin transaction")
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, HomeFragment())
            transaction.commit()
        } else {
            val intent = Intent(this, LandingActivity::class.java)
            finish()
            startActivity(intent)
        }

        val menuIcon: ImageView = findViewById(R.id.menu_icon)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_bar)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_icon -> {
                    switchFragment(HomeFragment())
                    true
                }
                R.id.guide_icon -> {
                    switchFragment(GuideFragment())
                    true
                }
                R.id.forum_icon -> {
                    switchFragment(ForumFragment())
                    true
                }
                else -> {
                    switchFragment(ProfileMainFragment())
                    true
                }
            }
        }
        navView = findViewById(R.id.navView)

        menuIcon.setOnClickListener {
            navView.findViewById<CircleImageView>(R.id.userPic).setImageResource(memInfo.profilePic)
            navView.findViewById<TextView>(R.id.usernameView).text = memInfo.username

            //set username to display in bar nav
            navView.visibility = View.VISIBLE
            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.consultation -> {
                        switchFragment(ConsultationBookingFragment())
                        navView.visibility = View.GONE
                    }
                    R.id.equipment -> {
                        switchFragment(BorrowFragment())
                        navView.visibility = View.GONE
                    }
                    R.id.setting -> {
                        switchFragment(SettingFragment())
                        navView.visibility = View.GONE
                    }
                    R.id.my_appointment -> {
                        switchFragment(Appointments())
                        navView.visibility = View.GONE
                    }
                    R.id.logout -> logout(sharedPref)
                }
                true
            }

            findViewById<ConstraintLayout>(R.id.main_view).setOnClickListener {
                Log.d("MAIN", "clicked")
                if (navView.visibility == View.VISIBLE) {
                    navView.visibility = View.GONE
                }
            }

            currentFocus?.clearFocus()
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(navView.windowToken, 0)
        }

    }

    private fun switchFragment(fragment: Fragment) {
        navView.visibility = View.GONE

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun logout(sharedPreferences: SharedPreferences) {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        editor.clear()
        Log.d("Main Activity", "Logout")
        finish()
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        DataBaseHelper(this).close()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            onBackPressedDispatcher.onBackPressed() // If the back stack is not empty, allow the default behavior
        } else {
            finish() // If the back stack is empty, handle the back button press as desired (e.g., exit the app)
        }
    }

    override fun onResume() {
        super.onResume()
        memInfo=memT.getMember(memId)
    }
}
