package org.hyperskill.simplebankmanager

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class UserMenuFragment: Fragment(R.layout.user_menu_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWelcomeMessage(view)
        setViewBalanceNavigation(view)
    }

    private fun setViewBalanceNavigation(view: View) {
        val viewBalanceBtn = view.findViewById<Button>(R.id.userMenuViewBalanceButton)
        val currentBalance : Double = 100.0
        val bundle = bundleOf(getString(R.string.current_balance_key) to currentBalance)
        viewBalanceBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_userMenuFragment_to_viewBalanceFragment, bundle)
        }
    }

    private fun setWelcomeMessage(view: View) {
        val userName = arguments?.getString(getString(R.string.username))
        val welcomeText = view.findViewById<TextView>(R.id.userMenuWelcomeTextView)
        welcomeText.text = getString(R.string.welcome, userName)
    }
}