package com.saadahmedev.cleanarchitecture.helper

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

interface SnackbarActionListener {
    fun onActionClicked(snackbar: Snackbar)
}

fun snackBar(view: View, message: String, action: String, duration: Int, listener: SnackbarActionListener) {
    val snackbar = Snackbar.make(view, message, duration)
    snackbar.setAction(action) {
            listener.onActionClicked(snackbar)
        }
    snackbar.show()
}

fun toast(context: Context, message: String, duration: Int) {
    Toast.makeText(context, message, duration).show()
}