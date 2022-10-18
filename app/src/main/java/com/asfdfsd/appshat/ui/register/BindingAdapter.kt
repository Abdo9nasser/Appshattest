package com.asfdfsd.appshat.ui.register

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:erorr")
fun erorr(inputtext: TextInputLayout, text:String?)
{
  inputtext.error=text
}