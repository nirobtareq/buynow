package com.mehedi.letsbuy.views.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthResult
import com.mehedi.letsbuy.core.DataState
import com.mehedi.letsbuy.data.AuthRepository
import com.mehedi.letsbuy.views.dashboard.seller.profile.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authService: AuthRepository) :
    ViewModel() {

    private val _loginResponse = MutableLiveData<DataState<Profile>>()

    val loginResponse: LiveData<DataState<Profile>> = _loginResponse


    fun userLogin(user: UserLogin) {
        _loginResponse.postValue(DataState.Loading())


        authService.userLogin(user)
            .addOnSuccessListener {
                // _loginResponse.postValue(DataState.Success(user))

                Log.d("TAG", "userRegistration: Success")

                checkUserById(it.user?.uid)


            }.addOnFailureListener { error ->

                _loginResponse.postValue(DataState.Error(error.message))
                Log.d("TAG", "userRegistration: ${error.message}")

            }


    }

    fun checkUserById(uid: String?) {
        uid?.let { userId ->
            authService.getUserByUserID(userId).addOnSuccessListener { value ->
                _loginResponse.postValue(
                    DataState.Success(value.documents[0].toObject(Profile::class.java))
                )
            }.addOnFailureListener { error ->
                _loginResponse.postValue(DataState.Error(error.message))
            }
        }


    }

}