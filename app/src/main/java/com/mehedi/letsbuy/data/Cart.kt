package com.mehedi.letsbuy.data

import java.util.UUID

data class Cart(
    var cartID: String = UUID.randomUUID().toString(),
    var userID: String = "",
    var name: String = "",
    var price: Double = 0.0,
    var imageLink: String = "",
    var description: String = "",
    var amount: Int = 0,
    var sellerID: String = "",
    var productID: String = ""
)