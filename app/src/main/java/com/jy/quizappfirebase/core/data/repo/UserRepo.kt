package com.jy.quizappfirebase.core.data.repo

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jy.quizappfirebase.core.data.model.User
import com.jy.quizappfirebase.core.services.AuthService
import kotlinx.coroutines.tasks.await

class UserRepo(
    private val authService: AuthService
) {
    private fun getUid(): String {
        return authService.getUid() ?: throw Exception("User doesn't exist")
    }

    private fun getCollection(): CollectionReference {
        return Firebase.firestore.collection("users")
    }

    suspend fun createUser(user: User) {
        getCollection().document(getUid()).set(user).await()
    }

    suspend fun getUser(): User? {
        val res = getCollection().document(getUid()).get().await()
        return res.data?.let { User.fromMap(it) }
    }

    suspend fun updateUser(user: User) {
        getCollection().document(getUid()).set(user).await()
    }
}