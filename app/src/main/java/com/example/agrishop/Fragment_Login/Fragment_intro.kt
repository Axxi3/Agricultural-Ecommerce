package com.example.agrishop.Fragment_Login

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.agrishop.R


import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class Fragment_intro : Fragment() {
    private lateinit var auth: FirebaseAuth


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View= inflater.inflate(R.layout.fragment_intro, container, false)
        var getStarted=view.findViewById<Button>(R.id.Regsiter)
        val login=view.findViewById<TextView>(R.id.gotologinPage)
        auth = Firebase.auth
        checklogin()
        getStarted.setOnClickListener {
            navigate(Fragment_register())
        }

        login.setOnClickListener {
            navigate(Fragment_login())
        }



        return view
    }

    private fun checklogin() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            Log.d("login console", "checklogin: " + auth.uid)
        }
    }


    private fun navigate(fragmentRegister: Fragment) {


        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()


        transaction.replace(R.id.fragmentContainerView, fragmentRegister)


        transaction.commit()
    }


}