package org.hyperskill.simplebankmanager

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class UserMenuFragment: Fragment(R.layout.user_menu_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userName = arguments?.getString(getString(R.string.username))
        val welcomeText = view.findViewById<TextView>(R.id.userMenuWelcomeTextView)
        welcomeText.text = getString(R.string.welcome, userName)
    }
}