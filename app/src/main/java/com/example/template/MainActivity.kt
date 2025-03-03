package com.example.template

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.template.preferencesManager.AuthManager
import com.example.template.functions.data_manipulation.globalToken
import com.example.template.functions.navigation.navigationhub
import com.example.template.functions.navigation.tosignuppage
import com.example.template.repository.Repository
import com.example.template.viewModel.MainViewModel
import com.example.template.viewModelFactory.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var progresstext: TextView
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        progresstext = findViewById(R.id.progresstext)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        //lateinit var intent: Intent// = Intent(this, SignUpPage::class.java)
        super.onCreate(savedInstanceState)

        globalToken.value = ""
        // hash reading
        progresstext.setText(R.string.loading)
        val authman = AuthManager()
        try {
            globalToken.value = authman.readToken(this)
            Log.i("TOKEN: ", globalToken.value ?: " ")
        } catch (e: Exception) {
            authman.writeToken("", this)
            // The user has opened the app for the first time creating the field
            globalToken.value = authman.readToken(this)
        }





        /*
        viewModel.getRole()
        viewModel.myStringResponse.observe(this, Observer {
            response ->
            if (response.code() != 200) {
                Log.i("ERROR", "ROLE ERROR")
                intent = Intent(this, SignUpPage::class.java)
                startActivity(intent)
                this.finish()
            } else {this
                userrole.value = response.body()
            }
        })

         */

        globalToken.observe(this, Observer {
            // Toast.makeText(this, globalToken.value, Toast.LENGTH_SHORT).show()
            progresstext.setText(R.string.changing_layout)
            // TODO: make a request to the server for a token verification, and only after it is verified change the activity
            if (globalToken.value != "") // TODO: temporary: will be changed to a response from the server
                navigationhub(this, "CRUD MENU")
            else
                tosignuppage(this)
            this.finish()
        })
    }
}