package com.example.template.functions.data_manipulation

import android.content.Context
import java.io.File
import com.example.template.functions.navigation.tosignuppage

// Functions to manipulate user data (configuration files, requests, etc.)

fun logout(context: Context) {
    writehash(context, "   ")
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