package org.hyperskill.simplebankmanager

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class UserMenuFragment: Fragment(R.layout.user_menu_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWelcomeMessage(view)
        setViewBalanceNavigation(view)
        setTransferFundsNavigation(view)
    }

    private fun setTransferFundsNavigation(view: View) {
        val transferFundsBtn = view.findViewById<Button>(R.id.userMenuTransferFundsButton)
        transferFundsBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_userMenuFragment_to_transferFundsFragment)
        }
    }

    private fun setViewBalanceNavigation(view: View) {
        val viewBalanceBtn = view.findViewById<Button>(R.id.userMenuViewBalanceButton)
        viewBalanceBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_userMenuFragment_to_viewBalanceFragment)
        }
    }

    private fun setWelcomeMessage(view: View) {
        val userName = arguments?.getString(getString(R.string.username))
        val welcomeText = view.findViewById<TextView>(R.id.userMenuWelcomeTextView)
        welcomeText.text = getString(R.string.welcome_username, userName)
    }
}