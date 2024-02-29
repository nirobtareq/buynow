package com.mehedi.letsbuy.views.dashboard.seller.upload

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mehedi.letsbuy.core.DataState
import com.mehedi.letsbuy.data.Product
import com.mehedi.letsbuy.data.SellerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ProductUploadViewModel @Inject constructor(private val repo: SellerRepository) :
    ViewModel() {

    private val _productUploadResponse = MutableLiveData<DataState<String>>()

    val productUploadResponse: LiveData<DataState<String>> = _productUploadResponse


    fun productUpload(product: Product) {
        _productUploadResponse.postValue(DataState.Loading())

        val imageUri: Uri = product.imageLink.toUri()

        repo.uploadProductImage(imageUri).addOnSuccessListener { snapshot ->

            snapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener { url ->

                product.imageLink = url.toString()

                repo.uploadProduct(product).addOnSuccessListener {
                    _productUploadResponse.postValue(DataState.Success("Uploaded and updated Successfully!"))
                }.addOnFailureListener {
                    _productUploadResponse.postValue(DataState.Error(it.message))
                }
            }

        }.addOnFailureListener {
            _productUploadResponse.postValue(DataState.Error(it.message))
        }


//        repo.uploadProduct()
//
//
//
//        repo.userLogin(user)
//            .addOnSuccessListener {
//                _loginResponse.postValue(DataState.Success(user))
//
//                Log.d("TAG", "userRegistration: Success")
//
//
//            }.addOnFailureListener { error ->
//
//                _loginResponse.postValue(DataState.Error(error.message))
//                Log.d("TAG", "userRegistration: ${error.message}")
//
//            }
//

    }

}