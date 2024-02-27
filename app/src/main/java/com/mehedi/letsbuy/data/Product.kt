package com.mehedi.letsbuy.data

data class Product(
    val name: String = "",
    val price: Double = 0.0,
    var imageLink: String = "",
    val description: String = "",
    val amount: Int = 0,
    var sellerID: String = "",
    var productID: String = ""

)