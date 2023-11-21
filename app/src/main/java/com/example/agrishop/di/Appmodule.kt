package com.example.agrishop.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Appmodule {
    @Provides
    @Singleton
    fun getFirebaseAut()=FirebaseAuth.getInstance()


}