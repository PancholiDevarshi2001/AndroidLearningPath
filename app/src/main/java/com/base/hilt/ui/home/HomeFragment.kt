package com.base.hilt.ui.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.base.hilt.R
import com.base.hilt.base.FragmentBase
import com.base.hilt.base.ToolbarModel
import com.base.hilt.base.ViewModelBase
import com.base.hilt.databinding.FragmentHomeBinding
import com.base.hilt.network.ResponseData
import com.base.hilt.network.ResponseHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : FragmentBase<ViewModelBase, FragmentHomeBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun setupToolbar() {
        viewModel.setToolbarItems(ToolbarModel(true, "Home", true))
    }

    override fun getViewModelClass(): Class<ViewModelBase> = ViewModelBase::class.java


    override fun initializeScreenVariables() {
        observeData()
        getDataBinding().btn.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_home_to_loginFragment)
        }
    }

    private fun observeData() {
    }
}