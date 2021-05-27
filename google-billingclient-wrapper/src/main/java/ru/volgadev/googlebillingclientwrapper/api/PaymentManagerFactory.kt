package ru.volgadev.googlebillingclientwrapper.api

import android.content.Context
import androidx.annotation.AnyThread
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.volgadev.googlebillingclientwrapper.impl.PaymentManagerImpl

/*
 * Factory for [PaymentManager]
 */
object PaymentManagerFactory {

    @AnyThread
    fun createPaymentManager(
        context: Context,
        ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    ): PaymentManager {
        return PaymentManagerImpl(context, ioDispatcher = ioDispatcher)
    }
}