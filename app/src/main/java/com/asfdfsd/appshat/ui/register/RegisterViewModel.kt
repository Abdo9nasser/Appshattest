package com.asfdfsd.appshat.ui.register

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asfdfsd.appshat.ui.models.DataUsers
import com.asfdfsd.appshat.ui.savetofirestor
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel : ViewModel() {
    val first_name = ObservableField<String>()
    val last_name = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val first_nameerorr = ObservableField<String?>()
    val last_nameerorr = ObservableField<String?>()
    val emailerorr = ObservableField<String?>()
    val passworderorr = ObservableField<String?>()
    val auth = Firebase.auth
    val lodinglivedata = MutableLiveData<Boolean>()
    val messagelivedata = MutableLiveData<String>()
    var navigatorRegiter: NavigatorRegiter? = null
    fun createAccount() {
        if (validate()) {
            lodinglivedata.value = true
            auth.createUserWithEmailAndPassword(email.get()!!, password.get()!!)
                .addOnCompleteListener { task ->
                    lodinglivedata.value = false
                    if (!task.isSuccessful) {
                        messagelivedata.value = task.exception?.message.toString()
                    } else {
                        messagelivedata.value = "Account Created"
                        navigatorRegiter?.navigatetohom()
                        val datauser = DataUsers(task.result.user!!.uid,
                            firstname = first_name.get()!!,
                            lastname = last_name.get()!!,
                            email = email.get()!!)
                        savetofirestor(datauser,{
                                                Log.e("sss","sss")

                        },{ Log.e("sss",it.message.toString())})

                    }

                }
        }
    }

    fun validate(): Boolean {
        var valid: Boolean = true
        if (first_name.get().isNullOrBlank()) {
            valid = false
            first_nameerorr.set("please Enter valid Name")
        } else {
            first_nameerorr.set(null)
        }
        if (last_name.get().isNullOrBlank()) {

            valid = false
            last_nameerorr.set("please Enter valid Name")
        } else {
            last_nameerorr.set(null)
        }
        if (email.get().isNullOrBlank()) {
            valid = false

            emailerorr.set("please Enter valid E-mali")
        } else {
            emailerorr.set(null)
        }
        if (password.get().isNullOrBlank()) {
            valid = false
            passworderorr.set("please Enter valid Password")
        } else {
            passworderorr.set(null)
        }
        return valid
    }

}