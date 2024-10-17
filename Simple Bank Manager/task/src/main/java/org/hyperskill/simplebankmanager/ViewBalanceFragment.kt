package org.hyperskill.simplebankmanager

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.hyperskill.simplebankmanager.ExtensionUtil.format

class ViewBalanceFragment: Fragment(R.layout.view_balance_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentBalanceTv = view.findViewById<TextView>(R.id.viewBalanceAmountTextView)
        currentBalanceTv.text =
            getString(R.string.current_balance_amount, BankAccount.currentBalance.format(2))
    }
}