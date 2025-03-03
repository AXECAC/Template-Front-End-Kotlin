package com.example.template.api

import com.example.template.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
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
    /*
    @POST("/api/Start/SomePost") // registration is not yet implemented: for testing purposes only.
    @Headers("Content-Type: application/json")
    suspend fun register(@Body temp: Users) : Response<String>
    */

    @POST("/api/User/Save")
    @Headers("Content-Type: application/json")
    suspend fun createSave(@Body temp: Users, oldEmail: String, IsNew: Boolean) : Response<Unit>


    @POST("/api/Auth/Registration")
    @Headers("Content-Type: application/json")
    suspend fun register(@Body temp: Users) : Response<TokenResponseClass> // Back-End returns codes: 201 + token, 409, 500
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
    @POST("/api/Start/SomeLogin")
    @Headers("Content-Type: application/json")
    suspend fun login(@Body temp: LogInForm) : Response<Unit> // Back-End returns codes: 200, 404
}