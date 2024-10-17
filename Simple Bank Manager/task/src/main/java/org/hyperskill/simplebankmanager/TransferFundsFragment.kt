package org.hyperskill.simplebankmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import org.hyperskill.simplebankmanager.ExtensionUtil.format

class TransferFundsFragment : Fragment(R.layout.transfer_funds_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val transferFundsButton = view.findViewById<Button>(R.id.transferFundsButton)
        transferFundsButton.setOnClickListener {
            transferMoney(view)
        }
    }

    private fun transferMoney(view: View) {
        val accountEditText = view.findViewById<EditText>(R.id.transferFundsAccountEditText)
        val accountName = accountEditText.text.toString()
        val isAccountValid = verifyAccountInput(accountName, accountEditText)

        val amountEditText = view.findViewById<EditText>(R.id.transferFundsAmountEditText)
        val amountText = amountEditText.text.toString()
        val isAmountValid = verifyAmountInput(amountText, amountEditText)

        if (isAccountValid && isAmountValid){
            val amount = amountText.toDouble()
            if (amountText.toDouble() > BankAccount.currentBalance) {
                Toast.makeText(view.context,
                    "Not enough funds to transfer \$${amount.format(2)}",
                    Toast.LENGTH_SHORT)
                    .show()
            } else {
                BankAccount.currentBalance -= amount
                Toast.makeText(view.context,
                    "Transferred \$${amount.format(2)} to account $accountName",
                    Toast.LENGTH_SHORT)
                    .show()
                view.findNavController().popBackStack()
            }
        }
    }

    private fun verifyAmountInput(amountText: String, amountEditText: EditText): Boolean {
        val amount : Double = if (amountText.isBlank()) -1.0 else amountText.toDouble()
        val isAmountValid = amount > 0
        if (!isAmountValid) {
            amountEditText.error = "Invalid amount"
        }
        return isAmountValid
    }

    private fun verifyAccountInput(accountName: String, accountEditText: EditText): Boolean {
        val accountNamePattern = "[s|c]a\\d{4}".toRegex()
        val isAccountValid = accountNamePattern.matches(accountName)
        if (!isAccountValid) {
            accountEditText.error = getString(R.string.account_number_error)
        }
        return isAccountValid
    }
}