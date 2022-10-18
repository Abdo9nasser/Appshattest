package com.asfdfsd.appshat.ui.register

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.asfdfsd.appshat.R
import com.asfdfsd.appshat.databinding.ActivityMainBinding
import com.asfdfsd.appshat.ui.BaseActivity
import com.asfdfsd.appshat.ui.home.HomeActivity

class MainActivity : BaseActivity<RegisterViewModel,ActivityMainBinding>(),NavigatorRegiter {
    lateinit var progrisDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding.vm=viewModel
        supscribinlivedata()
        viewModel.navigatorRegiter=this

    }

    override fun getlayout(): Int {
        return R.layout.activity_main
    }

    fun  supscribinlivedata(){
        viewModel.lodinglivedata.observe(this){
            if (it){
                progrisDialog= ProgressDialog.show(this,"loding","")

            }
            else{
                progrisDialog.dismiss()
            }
        }
        viewModel.messagelivedata.observe(this){
            val alertDialog= AlertDialog.Builder(this)
            alertDialog.setMessage(it).setTitle("Welcome").setPositiveButton("Ok"
            ) { p0, p1 -> p0!!.dismiss() }
            alertDialog.show()
        }
    }

    override fun intiviewmodel(): RegisterViewModel {

return ViewModelProvider(this).get(RegisterViewModel::class.java)   }

    override fun navigatetohom() {
        val intent=Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
}