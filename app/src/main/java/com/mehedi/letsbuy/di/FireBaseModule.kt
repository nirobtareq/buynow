package com.mehedi.letsbuy.di


import com.google.android.datatransport.runtime.dagger.Provides
import com.google.firebase.auth.FirebaseAuth
import com.mehedi.letsbuy.data.AuthService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FireBaseModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth(): AuthService {
        return AuthService(FirebaseAuth.getInstance())
    }


}