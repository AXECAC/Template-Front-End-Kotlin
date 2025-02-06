package com.example.template

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.template.functions.data_manipulation.sessionHash
import com.example.template.functions.data_manipulation.readhash
import com.example.template.functions.data_manipulation.userrole
import com.example.template.functions.navigation.navigationhub
import com.example.template.functions.navigation.tosignuppage
import com.example.template.repository.Repository
import com.example.template.viewModel.MainViewModel
import com.example.template.viewModelFactory.MainViewModelFactory
import java.io.FileNotFoundException

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

        sessionHash.value = "   "
        // hash reading
        progresstext.setText(R.string.loading)
        try {
            sessionHash.value = readhash(this) // TODO: format conf file to a json-like file
            Toast.makeText(this, sessionHash.value, Toast.LENGTH_SHORT).show()
            Log.i("HASH", sessionHash.value ?: "   ")
        } catch (e: FileNotFoundException) {
            Log.i("ERROR", "NO SUCH FILE")
            tosignuppage(this)
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
            } else {
                userrole.value = response.body()
            }
        })

         */

        sessionHash.observe(this, Observer {
            Toast.makeText(this, sessionHash.value, Toast.LENGTH_SHORT).show()
            progresstext.setText(R.string.changing_layout)
            //navigationhub(this, userrole.value!!)
            if (sessionHash.value != "   ")
                navigationhub(this, "MAIN MENU")
            this.finish()
        })
    }
}