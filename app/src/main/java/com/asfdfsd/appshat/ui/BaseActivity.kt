package com.asfdfsd.appshat.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<VM:ViewModel,DB:ViewDataBinding>:AppCompatActivity() {
    lateinit var viewModel: VM
    lateinit var databinding:DB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding=DataBindingUtil.setContentView(this,getlayout())
        viewModel=intiviewmodel()
    }
    abstract fun getlayout():Int
    abstract fun intiviewmodel():VM

}
