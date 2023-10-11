package com.saadahmedev.cleanarchitecture.ui.dashboard

import android.os.Bundle
import com.saadahmedev.cleanarchitecture.base.BaseActivity
import com.saadahmedev.cleanarchitecture.databinding.ActivityDashboardBinding
import com.saadahmedev.cleanarchitecture.databinding.AppToolbarBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding>(ActivityDashboardBinding::inflate) {

    override val toolbarBinding: AppToolbarBinding
        get() = binding.appToolbar

    override fun onActivityCreate(savedInstanceState: Bundle?) {}

    override fun observeData() {}
}