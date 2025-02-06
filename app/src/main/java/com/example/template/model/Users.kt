package com.example.template.model


// My version
data class Users (
    val id: Int,
    val email: String,
    val password: String,
    val firstname: String,
    val secondname: String
)

/*
// Egor version
data class Users (
    var Id: Int,
    val Email: String,
    val Password_hash: String,
    val Fullname: String, // Surname + Name + Patronymic
    val Role: String,
    val Organization_id: Int,
    val Done: Int, // number of participations
    val Score: Int,
    val Current: String // What a user participates in right now
)
 */