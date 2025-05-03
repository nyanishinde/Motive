package com.example.motivv

import android.R
import android.app.Application
import androidx.lifecycle.AndroidViewModel

class AuthViewModel(application: Application): AndroidViewModel(application) {
    private val repository= AuthRepository()
    fun register(email: String,password: String,callback:(Boolean, String?) ->Unit){
        repository.register(email,password,callback)
    }

    fun login(email: String,password: String,callback: (Boolean, String?) -> Unit){
        repository.register(email,password,callback)
    }

    fun isLoggedIn(): Boolean = repository.isLoggedIn()
}