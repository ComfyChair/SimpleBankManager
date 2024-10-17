package org.hyperskill.simplebankmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.hyperskill.simplebankmanager.ExtensionUtil.format

class CalculateExchangeFragment : Fragment(R.layout.calculate_exchange_fragment), AdapterView.OnItemSelectedListener {
    private lateinit var fromCurrency: String
    private lateinit var toCurrency: String
    private val fromSpinner: Spinner by lazy {
        view!!.findViewById(R.id.calculateExchangeFromSpinner)
    }
    private val toSpinner: Spinner by lazy {
        view!!.findViewById(R.id.calculateExchangeToSpinner)
    }

    val exchangeMap: Map<String, Map<String, Double>> by lazy {
        val intent = (view?.context as AppCompatActivity).intent
        val serializableMap = intent.extras?.getSerializable("exchangeMap")
        serializableMap as? Map<String, Map<String, Double>> ?: defaultMap
    }

    companion object {
        val defaultMap = mapOf(
            "EUR" to mapOf(
                "GBP" to 0.5,
                "USD" to 2.0
            ),
            "GBP" to mapOf(
                "EUR" to 2.0,
                "USD" to 4.0
            ),
            "USD" to mapOf(
                "EUR" to 0.5,
                "GBP" to 0.25
            )
        )
        val currencySymbol = mapOf(
            "EUR" to "€",
            "GBP" to "£",
            "USD" to "$"
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSpinners()

        val amountEditTxt = view.findViewById<EditText>(R.id.calculateExchangeAmountEditText)
        val displayText = view.findViewById<TextView>(R.id.calculateExchangeDisplayTextView)
        val calculateBtn = view.findViewById<Button>(R.id.calculateExchangeButton)

        calculateBtn.setOnClickListener {
            val amountTxt = amountEditTxt.text.toString()
            if (amountTxt.isBlank()) {
                Toast.makeText(view.context,
                    getString(R.string.enter_amount),
                    Toast.LENGTH_SHORT)
                    .show()
            } else {
                displayResult(amountTxt.toDouble(), displayText)
            }
        }
    }

    private fun displayResult(amount: Double, displayText: TextView) {
        val exchangeRate = exchangeMap[fromCurrency]!![toCurrency]!!
        val exchangeAmount = amount * exchangeRate
        displayText.text = getString(
            R.string.exchange_display_text,
            currencySymbol[fromCurrency],
            amount.format(2),
            currencySymbol[toCurrency],
            exchangeAmount.format(2)
        )
    }

    private fun initSpinners() {
        fromSpinner.setSelection(0)
        fromCurrency = resources.getStringArray(R.array.currencies)[0]
        toSpinner.setSelection(1)
        toCurrency = resources.getStringArray(R.array.currencies)[1]

        fromSpinner.onItemSelectedListener = this
        toSpinner.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        val currencies = resources.getStringArray(R.array.currencies)
        val selected = currencies[pos]

        when (parent) {
            fromSpinner -> {
                fromCurrency = selected
                if (selected == toCurrency) {
                    sameCurrencyToast(parent)
                    toSpinner.setSelection((pos + 1) % currencies.size)
                }
            }
            toSpinner -> {
                if (selected == fromCurrency) {
                    sameCurrencyToast(parent)
                    toSpinner.setSelection((pos + 1) % currencies.size)
                } else {
                    toCurrency = selected
                }
            }
        }
    }

    private fun sameCurrencyToast(parent: AdapterView<*>) {
        Toast.makeText(
            parent.context,
            "Cannot convert to same currency",
            Toast.LENGTH_SHORT
        )
            .show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // have to implement - should we actually do anything here?
    }
}