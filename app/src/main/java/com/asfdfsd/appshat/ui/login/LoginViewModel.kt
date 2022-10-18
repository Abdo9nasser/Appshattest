package com.asfdfsd.appshat.ui.login

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asfdfsd.appshat.ui.getuserfrofirestor
import com.asfdfsd.appshat.ui.models.DataUsers
import com.asfdfsd.appshat.ui.register.NavigatorRegiter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class LoginViewModel:ViewModel() {
    var navigatorLohin:NavigatorLohin?=null
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val emailerorr = ObservableField<String?>()
    val passworderorr = ObservableField<String?>()
    val auth = Firebase.auth
    val lodinglivedata = MutableLiveData<Boolean>()
    val messagelivedata = MutableLiveData<String>()
    fun navigatetoregister()
    {
        navigatorLohin?.navigatetoregister()
    }
    fun Login()
    {
        if (validate())
        {
            lodinglivedata.value=true
            auth.signInWithEmailAndPassword(email.get()!!, password.get()!!).addOnCompleteListener{
                lodinglivedata.value=false
                if (it.isSuccessful)
                {
                    navigatorLohin?.navigatetoHome()
                    getuserfrofirestor(it.result.user!!.uid,{
                        var currentuser=it.toObject<DataUsers>()

                    },{
                        Log.e("ffff",it.toString())
                    })
                }
                else
                {
                   messagelivedata.value=it.exception?.message
                }
            }


        }
        else
        {}
    }
    private fun validate(): Boolean {
        var valid = true
        if (email.get().isNullOrBlank()) {
            valid = false
            emailerorr.set("Enter E-mail")
        } else {
            emailerorr.set(null)
        }
        if (password.get().isNullOrBlank()) {
            valid = false
            passworderorr.set("Enter password")
        } else {
            passworderorr.set(null)
        }
        return valid
    }

}