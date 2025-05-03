package com.example.template.model


// My version
data class User (
    val Id: Int,
    val Email: String,
    val Password: String,
    val FirstName: String, // 'Name' starts with a capital letter just because, lol
    val SecondName: String
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