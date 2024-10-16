package org.hyperskill.simplebankmanager

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    private lateinit var usernameExpected : String
    private lateinit var passwordExpected : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameExpected = intent.extras?.getString(getString(R.string.username)) ?: "Lara"
        passwordExpected = intent.extras?.getString(getString(R.string.password)) ?: "1234"

    }

    fun onLoginBtnPress(view: View) {
        val editTxtUserName = findViewById<EditText>(R.id.loginUsername)
        val userName: String = editTxtUserName.text.toString()

        val editTxtPassword = findViewById<EditText>(R.id.loginPassword)
        val password: String = editTxtPassword.text.toString()

        val correctCredentials = userName == usernameExpected && password == passwordExpected

        //TODO: pass username via Bundle to add to TextView ("Welcome, $username")
        if (correctCredentials) {
            Toast.makeText( this, "logged in", Toast.LENGTH_SHORT).show()
            val bundle = bundleOf(getString(R.string.username) to userName)
            view.findNavController()
                .navigate(R.id.action_loginFragment_to_userMenuFragment, bundle)
        } else {
            Toast.makeText( this, "invalid credentials", Toast.LENGTH_SHORT).show()
        }
    }
}