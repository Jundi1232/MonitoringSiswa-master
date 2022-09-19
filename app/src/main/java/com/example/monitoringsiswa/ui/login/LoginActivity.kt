package com.example.monitoringsiswa.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.monitoringsiswa.Constanta.EXTRA_DATA
import com.example.monitoringsiswa.MainActivity
import com.example.monitoringsiswa.data.remote.entity.utils.SessionManager
import com.example.monitoringsiswa.databinding.ActivityLoginBinding
import com.example.monitoringsiswa.ui.AdminActivity
import com.jn.latihan1.ui.login.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private lateinit var sessionManager: SessionManager
    private val viewModel: LoginViewModel by viewModels {
        ViewModelFactoryLogin.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sessionManager = SessionManager(this)
        binding.apply {

            btnLogin.setOnClickListener {
                val username=username.text.toString()
                val password=password.text.toString()
                if (username.isEmpty() && password.isEmpty()){
                    Toast.makeText(this@LoginActivity,"Harap masukan username dan passwod",Toast.LENGTH_SHORT).show()
                }else if (username=="admin" && password=="admin123"){
                    val intent= Intent(this@LoginActivity,AdminActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    viewModel.getUser(username,password).observe(this@LoginActivity){
                        val intent= Intent(this@LoginActivity,MainActivity::class.java)
                        intent.putExtra(EXTRA_DATA,it)
//                        sessionManager.saveAccessId(it.username)
                        startActivity(intent)
                        finish()
                    }
                }
            }

        }
    }
}