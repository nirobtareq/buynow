package com.mehedi.letsbuy.views.dashboard.seller.profile

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mehedi.letsbuy.core.DataState
import com.mehedi.letsbuy.data.repositories.SellerProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SellerProfileViewModel @Inject constructor(private val repo: SellerProfileRepository) :
    ViewModel() {

    private val _profileUpdateResponse = MutableLiveData<DataState<String>>()

    val profileUpdateResponse: LiveData<DataState<String>> = _profileUpdateResponse


    fun updateProfile(user: SellerProfile, hasLocalImageUrl: Boolean) {
        _profileUpdateResponse.postValue(DataState.Loading())

        if (hasLocalImageUrl) {
            val imageUri: Uri? = user.userImage?.toUri()
            imageUri?.let {
                repo.uploadImage(it).addOnSuccessListener { snapshot ->

                    snapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener { url ->

                        user.userImage = url.toString()

                        repo.updateUser(user).addOnSuccessListener {
                            _profileUpdateResponse.postValue(DataState.Success("Uploaded and updated user profile Successfully!"))
                        }.addOnFailureListener {
                            _profileUpdateResponse.postValue(DataState.Error(it.message))
                        }
                    }

                }.addOnFailureListener {
                    _profileUpdateResponse.postValue(DataState.Error(it.message))
                }
            }
        } else {

            repo.updateUser(user).addOnSuccessListener {
                _profileUpdateResponse.postValue(DataState.Success("Uploaded and updated user profile Successfully!"))
            }.addOnFailureListener {
                _profileUpdateResponse.postValue(DataState.Error(it.message))
            }


        }


    }


    private val _logedInUserResponse = MutableLiveData<DataState<SellerProfile>>()

    val logedInUserResponse: LiveData<DataState<SellerProfile>>
        get() = _logedInUserResponse

    fun getUserByUserID(userID: String) {

        _logedInUserResponse.postValue(DataState.Loading())

        repo.getUserByUserID(userID).addOnSuccessListener { value ->


            _logedInUserResponse.postValue(
                DataState.Success(
                    value.documents[0].toObject(
                        SellerProfile::class.java
                    )
                )
            )
        }


    }
}