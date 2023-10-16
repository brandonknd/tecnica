package com.alf.tecnica.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.alf.tecnica.R
import com.alf.tecnica.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_home)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        setContentView(binding.root)

        val userLogin = intent.extras?.getString("userLogin","Usuario")

        showAlert("usuario logeado",userLogin.toString())
    }

    private fun showAlert(title: String, msg: String){
        val alert = AlertDialog.Builder(this)
        alert.setTitle(title)
        alert.setMessage(msg)
        alert.setPositiveButton("Aceptar", null)
        alert.create().show()
    }
}