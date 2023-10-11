package com.saadahmedev.cleanarchitecture.ui.dashboard.tabs.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.saadahmedev.cleanarchitecture.base.BaseFragment
import com.saadahmedev.cleanarchitecture.databinding.FragmentHomeBinding
import com.saadahmedev.cleanarchitecture.ui.dashboard.tabs.home.viewmodel.ProductViewModel
import com.saadahmedev.cleanarchitecture.ui.dashboard.tabs.productDetails.viewmodel.ProductDetailsViewModel
import com.saadahmedev.cleanarchitecture.util.ProgressDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override val title: String
        get() = "Home"
    override val isBackButtonVisible: Boolean
        get() = false

    private val productViewModel: ProductViewModel by viewModels()
    private val productDetailsViewModel: ProductDetailsViewModel by viewModels()
    private lateinit var progressDialog: ProgressDialog

    override fun onFragmentCreate(savedInstanceState: Bundle?) {
        progressDialog = ProgressDialog.getInstance(requireContext())
        //productViewModel.getProducts(50)
        productDetailsViewModel.getProduct(2)
    }

    override fun observeData() {
        CoroutineScope(Dispatchers.IO).launch {
            productViewModel.productList.collectLatest {
                when {
                    it.isLoading -> {
                        withContext(Dispatchers.Main) {
                            progressDialog.show()
                        }
                    }

                    it.error.isNotBlank() -> {
                        progressDialog.dismiss()
                        it.error.shortSnackBar()
                    }

                    it.productList.isNotEmpty() -> {
                        progressDialog.dismiss()
                        it.productList[0].title.shortSnackBar()
                    }
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            productDetailsViewModel.product.collectLatest {
                when {
                    it.isLoading -> {
                        withContext(Dispatchers.Main) {
                            progressDialog.show()
                        }
                    }

                    it.error.isNotBlank() -> {
                        progressDialog.dismiss()
                        it.error.shortSnackBar()
                    }

                    it.product?.title?.isNotBlank()!! -> {
                        progressDialog.dismiss()
                        it.product?.title.shortSnackBar()
                    }
                }
            }
        }
    }
}