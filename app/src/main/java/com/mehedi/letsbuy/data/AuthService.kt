package com.mehedi.letsbuy.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.mehedi.letsbuy.views.register.User

class AuthService : AuthSource {
    override fun userRegistration(user: User): Task<AuthResult> {
        val mAuth = FirebaseAuth.getInstance()

        return mAuth.createUserWithEmailAndPassword(user.email, user.password)
    }

    override fun userLogin(user: User) {
        TODO("Not yet implemented")
    }

    override fun userForgetPassword(email: String) {
        TODO("Not yet implemented")
    }
}