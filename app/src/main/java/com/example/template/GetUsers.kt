package com.example.template

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.template.model.UsersAdapter
import com.example.template.repository.Repository
import com.example.template.viewModel.MainViewModel
import com.example.template.viewModelFactory.MainViewModelFactory
import okhttp3.internal.notify

class GetUsers : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_users)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        recyclerView = findViewById(R.id.recyclerViewUsers)
        usersAdapter = UsersAdapter(viewModel.myResponseUsers)
        recyclerView.adapter = usersAdapter
    }
    fun refresh(view: View?) {
        viewModel.getUsers()
        usersAdapter.notify()
        //recyclerView.addOnItemTouchListener()
        recyclerView.Recycler().clear()
        recyclerView.swapAdapter(UsersAdapter(viewModel.myResponseUsers), true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.layoutManager = GridLayoutManager(this, 1)
        //
    }
}