package com.mehedi.letsbuy.views.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mehedi.letsbuy.core.DataState
import com.mehedi.letsbuy.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val authService: AuthRepository) :
    ViewModel() {

    private val _registrationResponse = MutableLiveData<DataState<UserRegistration>>()

    val registrationResponse: LiveData<DataState<UserRegistration>> = _registrationResponse


    fun userRegistration(user: UserRegistration) {
        _registrationResponse.postValue(DataState.Loading())

        authService.userRegistration(user)
            .addOnSuccessListener { authResult ->

                authResult.user?.let { createdUser ->

                    user.userID = createdUser.uid

                    authService.createUser(user).addOnSuccessListener {

                        _registrationResponse.postValue(DataState.Success(user))

                    }.addOnFailureListener { error ->

                        _registrationResponse.postValue(DataState.Error(error.message))
                    }
                }


            }.addOnFailureListener { error ->

                _registrationResponse.postValue(DataState.Error(error.message))
                Log.d("TAG", "userRegistration: ${error.message}")

            }


    }

}