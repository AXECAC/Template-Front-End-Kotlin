package com.example.template.repository

import com.example.template.api.RetrofitInstance
import com.example.template.model.*
import com.example.template.functions.data_manipulation.userhash
//import com.example.trashhack.model.loggedin.LoggedInUser_instance
import retrofit2.Response

class Repository {
    /*
    suspend fun getPosts(): Posts {
        return RetrofitInstance.api.getposts()
    }
     */
    suspend fun getDevs(): Response<MutableList<Users>> {
        return RetrofitInstance.api.getdevs(userhash.value ?: "")
    }

    suspend fun getRole(): Response<String> {
        return RetrofitInstance.api.getrole(userhash.value ?: "")
    }

    /*
    suspend fun pushPostUsers(
        id: Int,
        email: String,
        passwordhash: String,
        fullname: String,
        role: String,
        organizationid: Int,
        done: Int,
        score: Int,
        current: String
    ): Response<Users> {
        var temp: Users = Users(id, email, passwordhash, fullname, role, organizationid, done, score, current)
        return RetrofitInstance.api.pushpost(temp)
    }
     */
    suspend fun devregister(
        email: String,
        password: String,
        fullname: String,
        role: String,
        organizationid: Int
    ) : Response<String> {
        val temp: DevRegForm = DevRegForm(
            email,
            password,
            fullname,
            role,
            organizationid,
            userhash.value ?: ""
        )
        return RetrofitInstance.api.devregister(temp)
    }
    suspend fun register(
        email: String,
        password: String,
        fullname: String,
        role: String,
        organizationid: Int
    ) : Response<String> {
        val temp: RegistrationForm = RegistrationForm(
            email,
            password,
            fullname,
            role,
            organizationid
        )
        return RetrofitInstance.api.register(temp)
    }
    suspend fun delete(
        email: String,
        password_hash: String,
        fullname: String,
        role: String,
        organizationid: Int
    ) : Response<String> {
        val temp: DevRegForm = DevRegForm(
            email,
            password_hash,
            fullname,
            role,
            organizationid,
            userhash.value ?: ""
        )
        return RetrofitInstance.api.delete(temp)
    }

    suspend fun login(
        email: String,
        password: String
    ) : Response<String>
    {
        //LoggedInUser_instance = LoggedInUser("", email, password)
        val temp: LogInForm = LogInForm(
            email,
            password
        )
        return RetrofitInstance.api.login(temp)
    }
}