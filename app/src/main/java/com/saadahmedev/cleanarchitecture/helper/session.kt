package com.saadahmedev.cleanarchitecture.helper

import android.content.Context
import com.saadahmedev.cleanarchitecture.util.SessionManager

fun Context.getToken(): String? = SessionManager.getInstance(this).token
fun Context.getBearerToken(): String? = SessionManager.getInstance(this).bearerToken
fun Context.getPhone(): String? = SessionManager.getInstance(this).number