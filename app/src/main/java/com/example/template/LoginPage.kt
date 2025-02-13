package com.example.template

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.template.functions.*
import com.example.template.functions.data_manipulation.*
import com.example.template.functions.navigation.*
import com.example.template.repository.Repository
import com.example.template.viewModel.MainViewModel
import com.example.template.viewModelFactory.MainViewModelFactory
import com.example.template.functions.navigation.tosignuppage
class LoginPage : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    // 'in' prefix for 'input'
    lateinit var inemail: EditText
    lateinit var inpassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        inemail = findViewById(R.id.email_input)
        inpassword = findViewById(R.id.password_input)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    fun signin(view: View?) {
        if (removespaces(inemail.text.toString()) == "" ||
            removespaces(inpassword.text.toString()) == "") {
            Toast.makeText(this, "You have an empty field", Toast.LENGTH_SHORT).show()
            return
        }
        if (checkForInternet(this).not()) {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show()
            return
        }

        // server check
        viewModel.login(
            removespaces(inemail.text.toString()),
            removespaces(inpassword.text.toString())
        )
        viewModel.myUnitResponse.observe(this, Observer {
                response ->
            if (response == null) {
                Toast.makeText(this, "No Response", Toast.LENGTH_SHORT).show()
            } else if (response.code() != 200) {
                var msg = response.code().toString()
                if (response.code() == 404) {
                    msg = "No such user"
                }
                Log.i("ERROR", "HASH ERROR")
                Toast.makeText(this, "ERROR: ".plus(msg), Toast.LENGTH_SHORT).show()
            } else if (response.code() == 200) {
                Log.i("SUCCESS", response.body().toString())
                Toast.makeText(this, "Success".plus(response.body()), Toast.LENGTH_SHORT).show()
                globalEmail.value = removespaces(inemail.text.toString()) //response.body()
            }
            //writehash(this, sessionHash.value!!)

        })
        /*
        // should I put it before the response observer?
        sessionHash.observe(this, Observer {
            Toast.makeText(this, "Asking for the role", Toast.LENGTH_SHORT).show()
            // viewModel.getRole()
            //navigationhub(this, userrole.value!!)
            //this.finish()
            viewModel.myStringResponse.observe(this, Observer {
                    response ->
                if (response.code() != 200) {
                    Toast.makeText(this, "Error: ".plus(response.code()), Toast.LENGTH_SHORT).show()
                    viewModel.getRole()
                    /*
                    intent = Intent(this, SignUpPage::class.java)
                    startActivity(intent)
                    this.finish()*/
                } else { // TODO: some problem with fetching a role
                    Toast.makeText(this, "role is ".plus(response.body()), Toast.LENGTH_SHORT).show()
                    userrole.value = response.body()
                }
            })
        })

        userrole.observe(this, Observer {
            //progresstext.setText(R.string.changing_layout)
            if (userrole.value != null) {
                navigationhub(this, userrole.value!!)
                this.finish()
            }
        })
        LEGACY
         */
        globalEmail.observe(this, Observer {
            //userrole.value = inrole.text.toString()
            response ->
            navigationhub(this, "CRUD MENU")
            this.finish()
            //this.finish()
        })
    }
    fun tosignuppage(view: View?) {
        tosignuppage(this)
        this.finish()
    }
}