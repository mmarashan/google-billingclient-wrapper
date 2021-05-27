package ru.volgadev.googlebillingclientwrapper.impl

import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingFlowParams

/**
 * Simple service locator that hold links to [BillingClient] and [BillingFlowParams]
 */
internal object BillingProcessorServiceLocator {

    private var billingClient: BillingClient? = null
    private var billingFlowParams: BillingFlowParams? = null

    fun register(billingProcessor: BillingClient, params: BillingFlowParams) {
        this.billingClient = billingProcessor
        this.billingFlowParams = params
    }

    fun get(): BillingClient {
        return billingClient ?: throw IllegalStateException("billingProcessor not registered!")
    }

    fun getParams(): BillingFlowParams {
        return billingFlowParams ?: throw IllegalStateException("billingProcessor not registered!")
    }

    fun clear() {
        billingClient = null
        billingFlowParams = null
    }
}