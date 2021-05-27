package ru.volgadev.googlebillingclientwrapper.impl

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingFlowParams
import ru.volgadev.googlebillingclientwrapper.R

/**
 * Transparent Activity calling [BillingClient.launchBillingFlow]
 */
internal class TransparentBillingClientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transparent_layout)
        val billingFlowParams: BillingFlowParams = BillingProcessorServiceLocator.getParams()
        val billingClient: BillingClient = BillingProcessorServiceLocator.get()
        billingClient.launchBillingFlow(this, billingFlowParams)
        finish()
    }

    companion object {

        fun launch(context: Context) {
            val intent = Intent(context, TransparentBillingClientActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        }
    }
}