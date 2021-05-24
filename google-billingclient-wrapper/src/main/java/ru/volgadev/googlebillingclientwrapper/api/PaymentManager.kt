package ru.volgadev.googlebillingclientwrapper.api

import com.android.billingclient.api.*
import kotlinx.coroutines.flow.SharedFlow

/**
 * Wrapper by Billing Client
 */
interface PaymentManager {

    /**
     * IN-APP products are available in app
     */
    val ownedProducts: SharedFlow<List<MarketItem>>

    /**
     * Subscriptions are available in app
     */
    val ownedSubscriptions: SharedFlow<List<MarketItem>>

    /**
     * Link to active [BillingClient].
     * Use this directly if the capabilities of this api are not enough for you
     */
    val billingClient: BillingClient

    /**
     * Sets project product ids. You should call it for launch request for products
     */
    fun setProjectSkuIds(ids: List<String>, skuType: ItemSkuType)

    /**
     * Request payment for product with [skuId] identifier
     */
    fun requestPayment(skuId: String)

    /**
     * Request payment for product with custom builds [billingFlowParams] params
     */
    fun requestPayment(billingFlowParams: BillingFlowParams)

    /**
     * Request consume purchase for product with [skuId] identifier
     */
    fun consumePurchase(skuId: String)

    /**
     * Request consume purchase for product with custom builds [consumeParams] params
     */
    fun consumePurchase(consumeParams: ConsumeParams)

    /**
     * Dispose resources including internal [billingClient]
     */
    fun dispose()
}

/**
 * Class binds product ([skuDetails]) with its payment info ([purchase])
 * @property skuDetails details about product
 * @property purchase details about product payment
 */
data class MarketItem(
    val skuDetails: SkuDetails,
    var purchase: Purchase? = null
) {
    /**
     * Returns if product is purchased
     */
    fun isPurchased(): Boolean = purchase?.purchaseState == Purchase.PurchaseState.PURCHASED

    /**
     * Type of product
     */
    val skuType: ItemSkuType =
        ItemSkuType.values().firstOrNull { it.skyType == skuDetails.type } ?: ItemSkuType.UNKNOWN
}

/**
 * Type of product
 * @property skyType type in string inside google lib
 */
enum class ItemSkuType(val skyType: String) {
    IN_APP("inapp"),
    SUBSCRIPTION("subs"),

    /* this result should not be, but introduced to avoid implicit errors */
    UNKNOWN("")
}