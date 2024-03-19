package com.mehedi.letsbuy.data

data class Product(
    var name: String = "",
    var price: Double = 0.0,
    var imageLink: String = "",
    var description: String = "",
    var amount: Int = 0,
    var sellerID: String = "",
    var productID: String = ""

)

fun Product.toMap(): Map<String, Any?> {
    return mapOf(
        "name" to name,
        "price" to price,
        "imageLink" to imageLink,
        "description" to description,
        "amount" to amount,
        "sellerID" to sellerID,
        "productID" to productID
    )
}