package com.example.assignment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import com.example.assignment.databinding.FragmentLoginBinding

class Login : Fragment() {

    //create a private writable property called _binding that can hold a type of FragmentLoginBinding or null
    private var _binding: FragmentLoginBinding? = null //private writable property called _binding; ? means the reference can hold null value

    //property only valid between onCreateView and onDestroyView
    private val binding get() = _binding!! // !! means not nul assertion operator. This converts any value to a non-null type / throw an exception if value is null

    //create a writable property called editorListener that can hold a type of TextVide.OnEditorActionListener or null

    private lateinit var memT: DataBaseHelper.Companion.MemberTable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_guide, container, false)
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        memT = DataBaseHelper.Companion.MemberTable(requireContext())

        binding.buttonLogin.setOnClickListener{
            val id = memT.validLogin(binding.username.text.toString(), binding.pw.text.toString())
            Log.d("login", "$id")

            //valid Login
            if(id != -1){
                val preferencesFileName = "Farmanac"
                val sharedPreferences: SharedPreferences =
                    activity?.getSharedPreferences(preferencesFileName, 0)!!
                val editor:SharedPreferences.Editor =  sharedPreferences.edit()
                editor.putInt("mem_id", id)
                val idValue = editor.putInt("mem_id", id).toString()
                Log.d("CheckUserId", idValue)
                editor.apply()
                editor.commit()

                val intent = Intent(activity, MainActivity::class.java)
                requireActivity().finish()
                startActivity(intent)
                Toast.makeText(requireContext(), "Successful Login", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(requireContext(), "Unsuccessful Login.. Please try again !", Toast.LENGTH_SHORT).show()
            }
        }
        binding.toRegisterLink.setOnClickListener{
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, RegisterFragment())
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

        val forgotPw = view.findViewById<TextView>(R.id.forgot_pw)
        forgotPw.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, ForgotPwFragment())
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null //manually set View binding object to null. Prevent memory leaks, ensure each call of onCreateView return a fresh & newly updated View
    }

   /* fun OnEditorActionListener(view: TextView, actionId: Int, event: KeyEvent): Boolean {
        when(actionId){.
            EditorInfo.IME_ACTION_NEXT ->
                Toast.makeText(this, "Next", Toast.LENGTH_SHORT).show()

            EditorInfo.IME_ACTION_DONE ->
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
        }
        return false
        }
    }*/


}