package com.example.motivv

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        viewModel= ViewModelProvider(this)[AuthViewModel::class.java]
        val userEmail=findViewById<TextInputEditText>(R.id.edt_user_email)
        val userPassword=findViewById<TextInputEditText>(R.id.edt_password)
        val signInBtn=findViewById<MaterialButton>(R.id.btn_sign_in)

        signInBtn.setOnClickListener {
            val email=userEmail.text.toString()
            val password=userPassword.text.toString()

            if (email.isEmpty()||password.isEmpty()){
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.login(email,password){success,error ->
                if(success){
                    Toast.makeText(this, "Signin Successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this, error?:"Signin Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}