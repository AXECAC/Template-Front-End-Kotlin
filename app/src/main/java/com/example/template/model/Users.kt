package com.example.template.model


// My version
data class Users (
    val id: Int,
    val email: String,
    val password_hash: String,
    val fullname: String, // Surname + Name + Patronymic
    val role: String,
    val organization_id: Int,
    val done: Int, // number of participations
    val score: Int,
    val current: String // What a user participates in right now
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