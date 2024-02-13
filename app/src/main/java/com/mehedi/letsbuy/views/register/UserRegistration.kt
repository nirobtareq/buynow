package com.mehedi.letsbuy.views.register

data class UserRegistration(
    val name: String,
    val email: String,
    val password: String,
    val userType: String,
    var userID: String
)
