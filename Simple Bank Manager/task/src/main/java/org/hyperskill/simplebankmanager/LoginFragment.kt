package org.hyperskill.simplebankmanager

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class LoginFragment: Fragment(R.layout.login_fragment) {
    private lateinit var usernameExpected : String
    private lateinit var passwordExpected : String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getExpectedCredentials(view)
        setOnClickListener(view)
    }

    private fun setOnClickListener(view: View) {
        val logInBtn = view.findViewById<TextView>(R.id.loginButton)

        logInBtn.setOnClickListener {
            val editTxtUserName = view.findViewById<EditText>(R.id.loginUsername)
            val userName: String = editTxtUserName.text.toString()

            val editTxtPassword = view.findViewById<EditText>(R.id.loginPassword)
            val password: String = editTxtPassword.text.toString()

            val correctCredentials = userName == usernameExpected && password == passwordExpected

            if (correctCredentials) {
                Toast.makeText(view.context, "logged in", Toast.LENGTH_SHORT).show()
                val bundle = bundleOf(getString(R.string.username) to userName)
                view.findNavController()
                    .navigate(R.id.action_loginFragment_to_userMenuFragment, bundle)
            } else {
                Toast.makeText(view.context, "invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getExpectedCredentials(view: View) {
        val intent = (view.context as AppCompatActivity).intent
        usernameExpected = intent.extras?.getString(getString(R.string.username)) ?: "Lara"
        passwordExpected = intent.extras?.getString(getString(R.string.password)) ?: "1234"
    }

}