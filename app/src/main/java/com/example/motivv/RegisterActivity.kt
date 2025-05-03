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

class RegisterActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        viewModel= ViewModelProvider(this)[AuthViewModel::class.java]

        val userName=findViewById<TextInputEditText>(R.id.edt_username)
        val userEmail=findViewById<TextInputEditText>(R.id.edt_email)
        val userPassword=findViewById<TextInputEditText>(R.id.edt_password)
        val userConfirmPassword=findViewById<TextInputEditText>(R.id.edt_confirm_password)
        val joinMotivvBtn=findViewById<MaterialButton>(R.id.btn_join_motivv)

        joinMotivvBtn.setOnClickListener {
            val name=userName.text.toString()
            val email=userEmail.text.toString()
            val password=userPassword.text.toString()
            val confirmPassword=userConfirmPassword.text.toString()
            if(email.isEmpty() || password.length < 8 ){
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(password!=confirmPassword){
                Toast.makeText(this, "Passwords are not same", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.register(email,password){success,error ->
                if(success){
                    Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this, error?:"Registration failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}