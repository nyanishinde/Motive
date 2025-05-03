package com.example.motivv

import android.R
import com.google.firebase.auth.FirebaseAuth

class AuthRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun register(email: String,password: String,callback:(Boolean, String?)-> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) callback(true, null)
                else callback(false, task.exception?.message)
            }
    }
    fun login(email: String,password: String,callback: (Boolean, String?) -> Unit){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task->
                if(task.isSuccessful)callback(true,null)
                else callback(false,task.exception?.message)
            }
    }
    fun logout(){
        auth.signOut()
    }
    fun isLoggedIn() : Boolean=auth.currentUser !=null
}