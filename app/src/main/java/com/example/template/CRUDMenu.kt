package com.example.template

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class CRUDMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crudmenu)
    }
    fun toCreateUserPage(view: View?) {
        val intent = Intent(this, CreateUser::class.java)
        startActivity(intent)
    }
    fun toGetUserPage(view: View?) {
        val intent = Intent(this, GetUser::class.java)
        startActivity(intent)
    }
    fun toGetUsersPage(view: View?) {
        val intent = Intent(this, GetUsers::class.java)
        startActivity(intent)
    }
    fun toChangeUserPage(view: View?) {
        val intent = Intent(this, ChangeUser::class.java)
        startActivity(intent)
    }
    fun toDeleteUserPage(view: View?) {
        val intent = Intent(this, DeleteUser::class.java)
        startActivity(intent)
    }
}