package com.saadahmedev.cleanarchitecture.ui.dashboard.tabs.home

import android.os.Bundle
import com.saadahmedev.cleanarchitecture.base.BaseFragment
import com.saadahmedev.cleanarchitecture.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override val title: String
        get() = "Home"
    override val isBackButtonVisible: Boolean
        get() = false

    override fun onFragmentCreate(savedInstanceState: Bundle?) {}

    override fun observeData() {}
}