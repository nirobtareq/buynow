package com.mehedi.letsbuy.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.mehedi.letsbuy.core.Nodes
import com.mehedi.letsbuy.views.login.UserLogin
import com.mehedi.letsbuy.views.register.UserRegistration
import javax.inject.Inject

class AuthRepository  @Inject constructor(
    private val mAuth: FirebaseAuth,
    private val db: FirebaseFirestore
) : AuthSource {
    override fun userRegistration(user: UserRegistration): Task<AuthResult> {
        return mAuth.createUserWithEmailAndPassword(user.email, user.password)
    }

    override fun userLogin(user: UserLogin): Task<AuthResult> {
        return mAuth.signInWithEmailAndPassword(user.email, user.password)
    }

    override fun userForgetPassword(email: String) {
        val mAuth = FirebaseAuth.getInstance()
    }

    override fun createUser(user: UserRegistration): Task<Void> {
        return db.collection(Nodes.USER).document(user.userID).set(user)
    }

    fun getUserByUserID(userID: String): Task<QuerySnapshot> {
        return db.collection(Nodes.USER).whereEqualTo("userID", userID).get()
    }
}