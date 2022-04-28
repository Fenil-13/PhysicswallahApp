package com.fenil.physicswallahapp.api.models.entities

data class User(
    val id: Int,
    val name: String,
    val profileImage: String,
    val qualification: List<String>,
    val subjects: List<String>
)