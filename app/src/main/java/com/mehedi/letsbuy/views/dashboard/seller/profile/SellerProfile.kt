package com.mehedi.letsbuy.views.dashboard.seller.profile

data class SellerProfile(
    var name: String = "",
    var email: String = "",
    val password: String = "",
    val userType: String = "",
    val userID: String = "",
    var userImage: String? = null,
    val shopName: String? = null
)

fun SellerProfile.toMap(): Map<String, Any?> {
    return mapOf(
        "name" to name,
        "email" to email,
        "password" to password,
        "userType" to userType,
        "userID" to userID,
        "userImage" to userImage,
        "shopName" to shopName
    )
}
