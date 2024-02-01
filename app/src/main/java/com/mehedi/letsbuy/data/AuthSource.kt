package com.mehedi.letsbuy.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.mehedi.letsbuy.views.register.User

interface AuthSource {

    fun userRegistration(user: User): Task<AuthResult>
    fun userLogin(user: User)
    fun userForgetPassword(email: String)


}