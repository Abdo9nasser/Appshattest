package com.asfdfsd.appshat.ui

import com.asfdfsd.appshat.ui.models.DataUsers
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun savetofirestor(
    user: DataUsers,
    SuccessListener: OnSuccessListener<in Void>,
    FailureListener: OnFailureListener,
) {
    val firestor = Firebase.firestore
    firestor.collection(DataUsers.namecollection).document(user.id).set(user)
        .addOnSuccessListener(SuccessListener).addOnFailureListener(FailureListener)
}
fun getuserfrofirestor(id:String, SuccessListener:OnSuccessListener<DocumentSnapshot>, FailureListener:OnFailureListener)
{
    val db =Firebase.firestore
    db.collection(DataUsers.namecollection).document(id).get().addOnSuccessListener(SuccessListener).addOnFailureListener(FailureListener)
}