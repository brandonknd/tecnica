package com.alf.tecnica.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.alf.tecnica.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginMain()
    }

    private fun loginMain(){
        val btnRegister = findViewById<Button>(R.id.buttonRegistrer)
        val btnLogin = findViewById<Button>(R.id.buttonLogin)
        val user = findViewById<EditText>(R.id.name).text
        val password = findViewById<EditText>(R.id.password).text



        btnLogin.setOnClickListener {

            if (validateLogin(user.toString(), password.toString())){
                startActivity(Intent(this,HomeActivity::class.java).apply { putExtra("userLogin", user.toString()) })
            }else{
                showAlert("ERROR","algo salio mal asegurate de registrar el usuario")
            }

        }

        btnRegister.setOnClickListener {
            startActivity(Intent(this,registroActivity::class.java))
        }
    }

    private fun validateLogin(user:String,password:String): Boolean {
        val loginUser = getSharedPreferences(getString(R.string.users_file), Context.MODE_PRIVATE)
        val userSaved = loginUser.getString("user",null)
        val passwordSaved = loginUser.getString("password",null)
        var isPass = false
        var isPassLogin = false

        var login = false
        if (user.isNotEmpty() && password.isNotEmpty()){
            isPass = true
        }
        if(userSaved == user && password == passwordSaved){
            isPassLogin = true
        }
        if(isPass && isPassLogin){
            login = true
        }
        return login
    }

    private fun showAlert(title: String, msg: String){
        val alert = AlertDialog.Builder(this)
        alert.setTitle(title)
        alert.setMessage(msg)
        alert.setPositiveButton("Aceptar", null)
        alert.create().show()
    }
}