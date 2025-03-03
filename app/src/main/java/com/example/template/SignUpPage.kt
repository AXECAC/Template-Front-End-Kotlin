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
import com.example.template.preferencesManager.AuthManager
import com.example.template.functions.data_manipulation.globalToken
import com.example.template.functions.navigation.*

class SignUpPage : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    // 'in' prefix for 'input'
    lateinit var inemail: EditText
    lateinit var inpassword: EditText
    lateinit var infirstname: EditText
    lateinit var insecondname: EditText

    val authman = AuthManager()

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

        // server API request
        viewModel.register(
            removespaces(inemail.text.toString()),
            removespaces(inpassword.text.toString()),
            removespaces(infirstname.text.toString()),
            removespaces(insecondname.text.toString())
        )

        viewModel.myTokenResponse.observe(this, Observer {
                response ->
            if (response.code() != 201) {
                Toast.makeText(this, "ERROR: ".plus(response.code().toString()), Toast.LENGTH_SHORT).show()
                if (response.body() == null) {
                    Toast.makeText(this, "No Response", Toast.LENGTH_SHORT).show()
                }
            }
            Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show()
            globalToken.value = response.body()!!.data
            authman.writeToken(globalToken.value.toString(), this)
        })
        // should I put it before the response observer?
        globalToken.observe(this, Observer {
            // Toast.makeText(this, "Success".plus(globalToken.value), Toast.LENGTH_SHORT).show()
            if (globalToken.value != "") { // TODO: temporary: will be changed to a response from the server
                navigationhub(this, "CRUD MENU")
                this.finish()
            }
        })

    }
    fun tologinpage(view: View?) {
        tologinpage(this)
        this.finish()
    }
}