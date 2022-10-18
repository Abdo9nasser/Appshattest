package com.asfdfsd.appshat.ui.models

data class DataUsers(
    var id: String = "",
    var firstname: String = "",
    var lastname: String = "",
    var email: String = "",
) {
    companion object{
        val namecollection="Uesers"
    }
}