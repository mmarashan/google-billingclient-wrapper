## Google Billing Client Wrapper Lib
____
[![](https://jitpack.io/v/mmarashan/google-billingclient-wrapper.svg)](https://jitpack.io/#mmarashan/google-billingclient-wrapper)
Wrapper for a Google Billing Client library that simplifies in-app payments for purchases and subscriptions
At start face with [Google Play Billing Library guides](https://developer.android.com/google/play/billing/integrate)

### Advantages
1. Coroutines SharedFlow-based api for collect updates about in-app products and payment results  
2. Launch payment flow from your data-layer. You no longer need to bring payments dependencies to your Activity
3. You also have link to active BillingClient from original library for extending and customisation

### Add the dependency
Step 1. Add the JitPack repository to your build file 
Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```
	dependencies {
	        implementation 'com.github.mmarashan:google-billingclient-wrapper:0.0.3'
	}
```

### Code example

```kotlin

    /* create instanse via factory method */
    val paymentManager = PaymentManagerFactory.createPaymentManager(context = context)
    
    /* you should sku ids from you play console */
    val categoriesSkuIds = listOf("set-your-purchase-id-from-play-console")
    paymentManager.setProjectSkuIds(categoriesSkuIds, ItemSkuType.IN_APP)
        
    /* gets info about your market item */
    val marketItem: MarketItem = paymentManager.ownedProducts.first() 
    /* details about item */
    val skuDetails: SkuDetails = marketItem.skuDetails
    /* details about item's purchase (if paid) */
    val purchase: Purchase = marketItem.purchase
    
    launch {
        paymentManager.ownedProducts.collect { marketItem ->
            /* collect updates after payment */
        }
    }
    /* launch payment flow */
    paymentManager.requestPayment(skuDetails.getSku())
    
    /* dispose resources if no need paymentManager */
    paymentManager.dispose()
```