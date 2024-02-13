package com.mehedi.letsbuy.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.mehedi.letsbuy.views.login.UserLogin
import com.mehedi.letsbuy.views.register.UserRegistration

interface AuthSource {

    fun userRegistration(user: UserRegistration): Task<AuthResult>
    fun userLogin(user: UserLogin): Task<AuthResult>
    fun userForgetPassword(email: String)
    fun createUser(user: UserRegistration):Task<Void>


}