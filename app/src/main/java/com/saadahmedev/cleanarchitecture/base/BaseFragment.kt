package com.saadahmedev.cleanarchitecture.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.saadahmedev.cleanarchitecture.helper.SnackbarActionListener
import com.saadahmedev.cleanarchitecture.helper.navigate
import com.saadahmedev.cleanarchitecture.helper.snackBar
import com.saadahmedev.cleanarchitecture.helper.toast
import com.saadahmedev.cleanarchitecture.util.ApiCall
import com.saadahmedev.cleanarchitecture.util.SessionManager
import com.saadahmedev.cleanarchitecture.viewmodel.BaseViewModel
import com.saadahmedsoft.tinydb.TinyDB
import retrofit2.Call

abstract class BaseFragment<BINDING: ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> BINDING
) : Fragment() {

    private lateinit var _binding: BINDING
    private val baseViewModel by activityViewModels<BaseViewModel>()
    private lateinit var _session: SessionManager
    private lateinit var _tinyDb: TinyDB

     val binding: BINDING
        get() = _binding

    val session: SessionManager
        get() = _session

    val tinyDb: TinyDB
        get() = _tinyDb

    abstract val title: String
    abstract val isBackButtonVisible: Boolean

    abstract fun onFragmentCreate(savedInstanceState: Bundle?)
    abstract fun observeData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        _session = SessionManager.getInstance(requireContext())
        _tinyDb = TinyDB.getInstance(requireContext())
        onFragmentCreate(savedInstanceState)
        observeData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        baseViewModel.setTitle(title)
        baseViewModel.setBackButtonState(isBackButtonVisible)
        return _binding.root
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
        toast(requireContext(), message, duration)
    }

    fun navigate(@IdRes id: Int) {
        binding.root.navigate(id)
    }

    fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    fun <T> Call<T>.getResponse(listener: ApiCall.OnResponseGet<T>) {
        ApiCall.enqueue(requireContext(), this) {
            listener.onSuccessful(it)
        }
    }

    fun <T> Call<T>.getNoProgressResponse(listener: ApiCall.OnResponseGet<T>) {
        ApiCall.enqueueNoProgress(requireContext(), this) {
            listener.onSuccessful(it)
        }
    }
}