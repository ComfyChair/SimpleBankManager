package org.hyperskill.simplebankmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import org.hyperskill.simplebankmanager.ExtensionUtil.format

class PayBillsFragment : Fragment(R.layout.pay_bills_fragment) {
    val billInfoMap: Map<String, Triple<String, String, Double>> by lazy {
        val intent = (view?.context as AppCompatActivity).intent
        val serialized = intent.extras?.getSerializable(getString(R.string.bill_info_key))
        serialized as? Map<String, Triple<String, String, Double>> ?: defaultBillInfoMap
    }

    companion object {
        val defaultBillInfoMap = mapOf(
            "ELEC" to Triple("Electricity", "ELEC", 45.0),
            "GAS" to Triple("Gas", "GAS", 20.0),
            "WTR" to Triple("Water", "WTR", 25.5)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText = view.findViewById<EditText>(R.id.payBillsCodeInputEditText)
        val showBillInfoBtn = view.findViewById<Button>(R.id.payBillsShowBillInfoButton)

        showBillInfoBtn.setOnClickListener {
            val billCode = editText.text.toString()
            if (billCode !in billInfoMap) {
                errorAlertDialog(view)
            } else {
                showBillInfo(view, billInfoMap[billCode]!!)
            }
        }
    }

    private fun showBillInfo(view: View, billInfo: Triple<String, String, Double>) {
        AlertDialog.Builder(view.context)
            .setTitle(getString(R.string.bill_info))
            .setMessage(
                getString(
                    R.string.bill_info_msg,
                    billInfo.first,
                    billInfo.second,
                    billInfo.third.format(2)
                ))
            .setPositiveButton(getString(R.string.confirm)) { _, _ ->
                //TODO: Pay Bill
            }
            .setNegativeButton(getString(R.string.cancel)){ _, _ ->
                //nothing
            }
            .show()
    }

    private fun errorAlertDialog(view: View) {
        AlertDialog.Builder(view.context)
            .setTitle(getString(R.string.error))
            .setMessage(getString(R.string.wrong_code))
            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                // nothing
            }
            .show()
    }

}