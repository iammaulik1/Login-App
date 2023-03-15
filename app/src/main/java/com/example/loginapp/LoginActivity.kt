package com.example.loginapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    private lateinit var db :DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val buttonLogin = findViewById<Button>(R.id.loginButton)
        val registrationButton = findViewById<Button>(R.id.registerButton)
        val db = DatabaseHelper(this)

        registrationButton.setOnClickListener {
            intent = Intent(this , RegistrationActivity::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext,"Register Yourself", Toast.LENGTH_SHORT).show()
        }




        buttonLogin.setOnClickListener {

            val Username = username.text.toString()
            val Password = password.text.toString()

            if (TextUtils.isEmpty(Username) || TextUtils.isEmpty(Password)){
                Toast.makeText(applicationContext,"You Must Enter Username And Password" , Toast.LENGTH_SHORT).show()
            }else{
                val checkUser = db.checkuserpass(Username,Password)
                if (checkUser==true){
                    Toast.makeText(applicationContext,"Login Successful" , Toast.LENGTH_SHORT).show()

                    intent = Intent(this , HomeActivity::class.java)
                    startActivity(intent)

                }else{
                    Toast.makeText(applicationContext,"Wrong Username Or Password",Toast.LENGTH_SHORT).show()
                }
            }
          }
        }

    }
