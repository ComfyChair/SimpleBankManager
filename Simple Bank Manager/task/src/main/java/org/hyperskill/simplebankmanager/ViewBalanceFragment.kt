package org.hyperskill.simplebankmanager

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.Locale

class ViewBalanceFragment: Fragment(R.layout.view_balance_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentBalance = arguments?.getDouble(getString(R.string.current_balance_key))
        val currentBalanceTv = view.findViewById<TextView>(R.id.viewBalanceAmountTextView)
        currentBalanceTv.text = String.format("$%.2f", currentBalance)
    }
}