package com.saadahmedev.cleanarchitecture.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.saadahmedev.cleanarchitecture.databinding.AppToolbarBinding
import com.saadahmedev.cleanarchitecture.helper.SnackbarActionListener
import com.saadahmedev.cleanarchitecture.helper.observe
import com.saadahmedev.cleanarchitecture.helper.onClicked
import com.saadahmedev.cleanarchitecture.helper.snackBar
import com.saadahmedev.cleanarchitecture.helper.toast
import com.saadahmedev.cleanarchitecture.util.ApiCall
import com.saadahmedev.cleanarchitecture.viewmodel.BaseViewModel
import retrofit2.Call

abstract class BaseActivity<BINDING: ViewBinding>(private val bindingInflater: (inflater: LayoutInflater) -> BINDING) : AppCompatActivity() {

    private lateinit var _binding: BINDING
    private val baseViewModel by viewModels<BaseViewModel>()

    val binding: BINDING
        get() = _binding

    abstract val toolbarBinding: AppToolbarBinding?

    abstract fun onActivityCreate(savedInstanceState: Bundle?)

    abstract fun observeData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding.root)
        observeData()
        initToolbar()
        onActivityCreate(savedInstanceState)
    }

    private fun initToolbar() {
        if (toolbarBinding != null) {
            observe(baseViewModel.title) {
                toolbarBinding?.toolbarTitle?.text = it
            }

            observe(baseViewModel.isBackButtonVisible) {
                toolbarBinding?.toolbarBtn?.visibility = if (it) View.VISIBLE else View.GONE
            }
        }

        toolbarBinding?.toolbarBtn?.onClicked {
            super.onBackPressedDispatcher.onBackPressed()
        }
    }

    fun String?.shortSnackBar(action: String, listener: SnackbarActionListener) {
        this?.let { showSnackBar(it, action, Snackbar.LENGTH_SHORT, listener) }
    }

    fun String?.longSnackBar(action: String, listener: SnackbarActionListener) {
        this?.let { showSnackBar(it, action, Snackbar.LENGTH_LONG, listener) }
    }

    fun String?.shortSnackBar() {
        this?.let {
            showSnackBar(it, "Close", Snackbar.LENGTH_SHORT, object : SnackbarActionListener {
                override fun onActionClicked(snackbar: Snackbar) {
                    snackbar.dismiss()
                }
            })
        }
    }

    fun String?.longSnackBar() {
        this?.let {
            showSnackBar(it, "Close", Snackbar.LENGTH_LONG, object : SnackbarActionListener {
                override fun onActionClicked(snackbar: Snackbar) {
                    snackbar.dismiss()
                }
            })
        }
    }

    fun String?.shortToast() {
        this?.let { showToast(it, Toast.LENGTH_SHORT) }
    }

    fun String?.longToast() {
        this?.let { showToast(it, Toast.LENGTH_LONG) }
    }

    private fun showSnackBar(message: String, action: String, duration: Int, listener: SnackbarActionListener) {
        snackBar(_binding.root, message, action, duration, listener)
    }

    private fun showToast(message: String, duration: Int) {
        toast(this, message, duration)
    }

    fun <T> Call<T>.getResponse(listener: ApiCall.OnResponseGet<T>) {
        ApiCall.enqueue(this@BaseActivity, this) {
            listener.onSuccessful(it)
        }
    }

    fun <T> Call<T>.getNoProgressResponse(listener: ApiCall.OnResponseGet<T>) {
        ApiCall.enqueueNoProgress(this@BaseActivity, this) {
            listener.onSuccessful(it)
        }
    }
}