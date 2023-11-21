package com.example.agrishop.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrishop.Util.Rsource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) :ViewModel(){
    private val _login = MutableSharedFlow<Rsource<FirebaseUser>>()
    val login =_login.asSharedFlow()

    fun login(email:String,password:String){
        viewModelScope.launch {  _login.emit((Rsource.Loading()))}
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            viewModelScope.launch {
                it.user.let {
                    _login.emit(Rsource.Success(it))
                }
            }
        }.addOnFailureListener {
            viewModelScope.launch {

                    _login.emit(Rsource.Error(it.message.toString()))

            }
        }
    }
}