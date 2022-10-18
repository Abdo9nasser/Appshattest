package com.asfdfsd.appshat.ui.login

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.asfdfsd.appshat.R
import com.asfdfsd.appshat.databinding.ActivityLoginBinding
import com.asfdfsd.appshat.ui.BaseActivity
import com.asfdfsd.appshat.ui.home.HomeActivity
import com.asfdfsd.appshat.ui.register.MainActivity

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>(), NavigatorLohin {
    lateinit var progrisDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigatorLohin = this
        databinding.vm = viewModel
        supscribinlivedata()
    }

    override fun getlayout(): Int {
        return R.layout.activity_login
    }

    override fun intiviewmodel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun navigatetoregister() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun navigatetoHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    fun supscribinlivedata() {
        viewModel.lodinglivedata.observe(this) {
            if (it) {
                progrisDialog = ProgressDialog.show(this, "loding", "")

            } else {
                progrisDialog.dismiss()
            }
        }
        viewModel.messagelivedata.observe(this) {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setMessage(it).setTitle("Welcome").setPositiveButton("Ok"
            ) { p0, p1 -> p0!!.dismiss() }
            alertDialog.show()
        }
    }

}