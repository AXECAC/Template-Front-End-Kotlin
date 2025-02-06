package com.example.template.functions.data_manipulation

import androidx.lifecycle.MutableLiveData
import com.example.template.model.Users

val globalEmail: MutableLiveData<String> = MutableLiveData()

lateinit var deletionrequesteduser: Users
val userrole: MutableLiveData<String> = MutableLiveData()