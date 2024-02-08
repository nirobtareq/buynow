package com.mehedi.letsbuy.di



import com.google.firebase.auth.FirebaseAuth
import com.mehedi.letsbuy.data.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FireBaseModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun providesFirebase(mAuth: FirebaseAuth): AuthService {
        return AuthService(mAuth)
    }


}