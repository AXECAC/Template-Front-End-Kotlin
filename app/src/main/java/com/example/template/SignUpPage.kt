package com.example.template

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.template.repository.Repository
import com.example.template.viewModel.MainViewModel
import com.example.template.viewModelFactory.MainViewModelFactory
import com.example.template.functions.*
import com.example.template.functions.data_manipulation.globalEmail
import com.example.template.functions.navigation.*

class SignUpPage : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    // 'in' prefix for 'input'
    lateinit var inemail: EditText
    lateinit var inpassword: EditText
    lateinit var infirstname: EditText
    lateinit var insecondname: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)

        inemail = findViewById(R.id.email_input)
        inpassword = findViewById(R.id.password_input)
        infirstname = findViewById(R.id.surname_input)
        insecondname = findViewById(R.id.name_input)
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }
    fun signup(view: View?) {
        if (removespaces(inemail.text.toString()) == "" ||
            removespaces(inpassword.text.toString()) == "" ||
            removespaces(infirstname.text.toString()) == "" ||
            removespaces(insecondname.text.toString()) == "") {
            Toast.makeText(this, "You have an empty field", Toast.LENGTH_SHORT).show()
            return
        }
        if (checkForInternet(this).not()) {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show()
            return
        }
        // server check
        viewModel.register(
            removespaces(inemail.text.toString()),
            removespaces(inpassword.text.toString()),
            removespaces(infirstname.text.toString()),
            removespaces(insecondname.text.toString())
        )
        viewModel.myStringResponse.observe(this, Observer {
                response ->
            if (response.body() == null) {
                Toast.makeText(this, "No Response", Toast.LENGTH_SHORT).show()
            } else if (response.code() != 200) {
                Toast.makeText(this, "ERROR: ".plus(response.code().toString()), Toast.LENGTH_SHORT).show()
            }  else {
                Toast.makeText(this, "Success".plus(response.body()), Toast.LENGTH_SHORT).show()
                println("Success".plus(response.body()))
                globalEmail.value = response.body()
                //writehash(this, sessionHash.value!!)
            }
        })
        // should I put it before the response observer?
        globalEmail.observe(this, Observer {
            //userrole.value = inrole.text.toString()
            navigationhub(this, "CRUD MENU")
            this.finish()
        })
    }
    fun tologinpage(view: View?) {
        tologinpage(this)
        this.finish()
    }
}