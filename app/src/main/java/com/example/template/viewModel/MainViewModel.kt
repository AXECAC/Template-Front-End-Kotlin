package com.example.template.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.model.*
import com.example.template.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {
    //val myResponse_posts: MutableLiveData<Posts> = MutableLiveData()
    val myResponseUsers: MutableList<Users> = mutableListOf<Users>()
    //lateinit var myResponse_users: Array<Users> //= mutableListOf<Users>()
    val myCResponse: MutableLiveData<Response<CResponse>> = MutableLiveData()
    //val myErrorResponse: MutableLiveData<Response<ErrorResponse>> = MutableLiveData()
    val myStringResponse: MutableLiveData<String> = MutableLiveData()
    val myErrorCodeResponse: MutableLiveData<Int> = MutableLiveData()
    val myUnitResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val myTokenResponse: MutableLiveData<Response<TokenResponseClass?>> = MutableLiveData()
	val myUserResponse: MutableLiveData<Users> = MutableLiveData()
    /*
    fun getPosts() {
        viewModelScope.launch {
            val response = repository.getPosts()
            myResponse_posts.value = response
        }
    }
     */
    /*
    fun getDevs(context: Context) {
        try {
            viewModelScope.launch {
                val response = repository.getDevs()
                response.body()?.let { myResponse_users.addAll(it) }// = response
            }
        } catch (e: HttpException) {
            Toast.makeText(context, "Try again.", Toast.LENGTH_SHORT).show()
        }
    }
    LEGACY
     */
    fun register(
        email: String,
        password: String,
        firstname: String,
        secondname: String
    ) {
        viewModelScope.launch {
            val response = repository.register(email, password, firstname, secondname)
            if (response.body() != null)
                myTokenResponse.value = response
            else
                myErrorCodeResponse.value = response.code()
        }
    }
    /*
    fun devregister(
        email: String,
        password: String,
        fullname: String,
        role: String,
        organizationid: Int
    ) {
        viewModelScope.launch {
            val response = repository.devregister(email, password, fullname, role, organizationid)
            myStringResponse.value = response
        }
    }
    LEGACY
     */
    /*
    fun delete(requesteduser: Users) {
        viewModelScope.launch {
            val response = repository.delete(
                com.example.template.functions.data_manipulation.deletionrequesteduser.email,
                com.example.template.functions.data_manipulation.deletionrequesteduser.password_hash,
                com.example.template.functions.data_manipulation.deletionrequesteduser.fullname,
                com.example.template.functions.data_manipulation.deletionrequesteduser.role,
                com.example.template.functions.data_manipulation.deletionrequesteduser.organization_id
            )
            myStringResponse.value = response
        }
    }
    LEGACY
     */
    fun login(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            val response = repository.login(email, password)
            if (response.body() != null)
                myTokenResponse.value = response
            else
                myErrorCodeResponse.value = response.code()
        }
    }
    fun check() {
        viewModelScope.launch {
            val response = repository.check()
            if (response.code() == 200)
				myUnitResponse.value = response // not the body,
												// because Unit is a Unit no matter what,
												// I'm just not really sure it will
												// work properly if I don't save the whole
												// response class
            else
                myErrorCodeResponse.value = response.code()
        }
    }

	fun getUsers() {
		viewModelScope.launch {
			val response = repository.getUsers()
			if (response.code() == 200) {
				myResponseUsers.clear()
				myResponseUsers.addAll(0, response.body() ?: mutableListOf<Users>())
			} else if (response.code() == 204) {
				myResponseUsers.clear()
			} else
				myErrorCodeResponse.value = response.code()
		}
	}
	fun getUserById(id: Int) {
		viewModelScope.launch {
			val response = repository.getUserById(id)
			if (response.code() == 200) {
				myUserResponse.value = response.body()
			} else
				myErrorCodeResponse.value = response.code()
		}
	}
	fun getUserByEmail(email: String) {
		viewModelScope.launch {
			val response = repository.getUserByEmail(email)
			if (response.code() == 200) {
				myUserResponse.value = response.body()
			} else
				myErrorCodeResponse.value = response.code()
		}
	}
	fun create(
		email: String,
		password: String,
		firstname: String,
		secondname: String
	) {
		viewModelScope.launch {
			val response = repository.create(email, password, firstname, secondname)
			if (response.code() == 201) {
				//val message = response.body()?.get("message")
				//Log.i("JSON", message.toString())
				myStringResponse.value = "SUCCESS" //message.toString() // response.body()
			} else
				myErrorCodeResponse.value = response.code()
		}
	}
	fun edit(user: Users, email: String) {
		viewModelScope.launch {
			val response = repository.edit(user, email)
			if (response.code() == 201) {
				myStringResponse.value = "SUCCESS" // response.body()
			} else
				myErrorCodeResponse.value = response.code()
		}
	}
	fun delete(id: Int) {
		viewModelScope.launch {
			val response = repository.delete(id)
			if (response.code() == 204)
				myUnitResponse.value = response // not the body,
												// because Unit is a Unit no matter what,
												// I'm just not really sure it will
												// work properly if I don't save the whole
												// response class
			else
				myErrorCodeResponse.value = response.code()
		}
	}
    /*
    fun getRole() {
        viewModelScope.launch {
            val response = repository.getRole()
            myStringResponse.value = response
        }
    }
    LEGACY
     */
}