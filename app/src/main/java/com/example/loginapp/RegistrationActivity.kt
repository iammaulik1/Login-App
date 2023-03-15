package com.example.loginapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegistrationActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    private lateinit var db:DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val loginButton = findViewById<Button>(R.id.loginButtonRegister)
        val registrationButton = findViewById<Button>(R.id.registerButtonRegister)
        val registerFirstName = findViewById<EditText>(R.id.registerFirstName)
        val registerLastName = findViewById<EditText>(R.id.registerLastName)
        val registerEmail = findViewById<EditText>(R.id.registerEmailId)
        val createPassword = findViewById<EditText>(R.id.registerEnterPassword)
        val confirmPassword = findViewById<EditText>(R.id.registerConfirmPassword)
        db = DatabaseHelper(this)


        registrationButton.setOnClickListener {

            val usernameText = registerEmail.text.toString()
            val passwordText = createPassword.text.toString()
            val savedata = db.insertdata(usernameText,passwordText)


            when{
                TextUtils.isEmpty(registerFirstName.text.toString().trim()) -> {
                    Toast.makeText(applicationContext,"Enter First Name ", Toast.LENGTH_SHORT).show()
                }

                TextUtils.isEmpty(registerLastName.text.toString().trim()) -> {
                    Toast.makeText(applicationContext,"Enter Last Name", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(registerEmail.text.toString().trim()) -> {
                    Toast.makeText(applicationContext,"Enter Email", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(createPassword.text.toString().trim()) -> {
                    Toast.makeText(applicationContext,"Create Password", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(confirmPassword.text.toString().trim()) -> {
                    Toast.makeText(applicationContext,"Confirm Password", Toast.LENGTH_SHORT).show()
                }



                registerFirstName.text.toString().isNotEmpty() && registerLastName.text.toString().isNotEmpty()
                        && registerEmail.text.toString().isNotEmpty() && createPassword.text.toString().isNotEmpty()
                        && confirmPassword.text.toString().isNotEmpty()
                -> {

                    if (createPassword.text.toString() == confirmPassword.text.toString()){

                        if (savedata==true){


                            Toast.makeText(applicationContext,"User Exist",Toast.LENGTH_SHORT).show()


                        }
                        else{
                            Toast.makeText(applicationContext,"Registration Successful",Toast.LENGTH_SHORT).show()

                            intent = Intent(this , LoginActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(applicationContext,"Your Registration  ", Toast.LENGTH_SHORT).show()


                        }
                    }else{

                        Toast.makeText(applicationContext,"Your Create Password and Confirm Password Doesn't Matched",Toast.LENGTH_SHORT).show()
                    }


                }
            }
        }
        loginButton.setOnClickListener {
            intent = Intent(this , LoginActivity::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext,"Now , Enter Username And Password", Toast.LENGTH_SHORT).show()
        }


    }
}