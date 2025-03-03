package com.example.template.functions.data_manipulation

import android.content.Context
import java.io.File
import com.example.template.functions.navigation.tosignuppage
import com.example.template.preferencesManager.AuthManager
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking




// Functions to manipulate user data (configuration files, requests, etc.)

fun logout(context: Context) {
    //writehash(context, "")
    globalToken.value = ""
    val authman = AuthManager()
    authman.writeToken("", context)
    tosignuppage(context)
}

// Hash manipulation functions
fun readhash(context: Context): String {
    return File(context.cacheDir.path, "hash").readText(Charsets.UTF_8)
}
fun writehash(context: Context, hash: String) {
    File(context.cacheDir.path, "hash").writeText(hash)
}

/*
fun writeinfo(context: Context, info: String) {
    File(context.cacheDir.path, "trash-hack_user.conf").writeText(info)
}
fun readinfo(context: Context): String {
    return File(context.cacheDir.path, "trash-hack_user.conf").readText(Charsets.UTF_8)
}
 */