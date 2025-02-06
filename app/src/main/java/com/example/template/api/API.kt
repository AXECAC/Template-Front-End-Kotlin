package com.example.template.api

import com.example.template.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface API {
    /*
    @GET("posts/1")
    suspend fun getposts() : Posts
    */
    /*
    @POST("devs")
    @Headers("Content-Type: application/json")
    suspend fun getdevs(@Body hash: String) : Response<MutableList<Users>>
    LEGACY
     */
    @POST("registration")
    @Headers("Content-Type: application/json")
    suspend fun register(@Body temp: Users) : Response<String> // back-end returns role + hash
    /*
    @POST("role") // TODO: change to get request
    @Headers("Content-Type: application/json")
    suspend fun getrole(@Body hash: String) : Response<String>

     */
    /*
    @POST("user/{user}")
    @Headers("Content-Type: application/json")
    suspend fun gethash(@Path("user") user: String?): Response<Users>

    @POST("hash")
    @Headers("Content-Type: application/json")
    suspend fun gethash(@Body temp: RegistrationForm) : Response<String>

    @POST("hash")
    @Headers("Content-Type: application/json")
    suspend fun gethash(@Body hash: String) : Response<String>
     */
    /*
    @POST("devregistration")
    @Headers("Content-Type: application/json")
    suspend fun devregister(@Body temp: DevRegForm) : Response<String>

    @POST("deletion")
    @Headers("Content-Type: application/json")
    suspend fun delete(@Body temp: DevRegForm) : Response<String> // Password field here is used for a password hash
    LEGACY
     */
    @POST("login")
    @Headers("Content-Type: application/json")
    suspend fun login(@Body temp: LogInForm) : Response<String> // back-end returns role + hash
}