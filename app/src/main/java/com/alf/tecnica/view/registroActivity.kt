package com.alf.tecnica.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.alf.tecnica.R

class registroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        registerInit()
    }

    private fun registerInit(){
        val user = findViewById<EditText>(R.id.nameRegister).text
        val password = findViewById<EditText>(R.id.passwordRegister).text
        val passwordComfirn = findViewById<EditText>(R.id.passwordRegister2).text
        val btnRegister= findViewById<Button>(R.id.btnRegisters)

        val saveUser = getSharedPreferences(getString(R.string.users_file), Context.MODE_PRIVATE).edit()

        btnRegister.setOnClickListener {
            var isPassUser = false
            var isPassPassword = false
            if (user.length >= 8){
                isPassUser = true
            }
            if (validatePassword(password.toString(), passwordComfirn.toString())){
                isPassPassword = true
            }

            if (isPassPassword && isPassUser){
                saveUser.putString("user", user.toString())
                saveUser.putString("password", password.toString())
                saveUser.apply()
                user.clear()
                password.clear()
                passwordComfirn.clear()
                showAlert("usuario creado", "se ha creado con exito")
            }else{
                showAlert("Error al crear usuario", "te falta alguno de estos criterios \n1. el usuario debe tener almenos 8 caractares")
                showAlert("Error al crear usuario", "te falta alguno de estos criterios \n2. el password debe tener una letra mayuscula y no menos a 6 caracteres aparte deben ser iguales")
            }
        }



    }

    private fun showAlert(title: String, msg: String){
        val alert = AlertDialog.Builder(this)
        alert.setTitle(title)
        alert.setMessage(msg)
        alert.setPositiveButton("Aceptar", null)
        alert.create().show()
    }

    private fun validatePassword(password:String,passwordComfirn:String): Boolean {
        var pass = false
        if((cumpleRequisitos(password) && cumpleRequisitos(passwordComfirn)) && (password == passwordComfirn)){
            pass = true
        }
        return pass
    }

    fun cumpleRequisitos(texto: String): Boolean {
        val regex = Regex("[A-Z].{5,}")
        return regex.find(texto) != null
    }
}