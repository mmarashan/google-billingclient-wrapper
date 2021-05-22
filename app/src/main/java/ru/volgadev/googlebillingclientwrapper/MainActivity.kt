package ru.volgadev.googlebillingclientwrapper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.volgadev.googlebillingclientwrapper.api.PaymentManagerFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val paymentManager = PaymentManagerFactory.createPaymentManager(this)
    }
}