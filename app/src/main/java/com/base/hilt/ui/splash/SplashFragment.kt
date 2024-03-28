package com.base.hilt.ui.splash

import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import com.base.hilt.R
import com.base.hilt.UserDataQuery
import com.base.hilt.base.FragmentBase
import com.base.hilt.base.ToolbarModel
import com.base.hilt.base.ViewModelBase
import com.base.hilt.databinding.FragmentSplashBinding
import com.base.hilt.ui.splash.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : FragmentBase<SplashViewModel, FragmentSplashBinding>() {


    override fun getLayoutId(): Int {
        return R.layout.fragment_splash
    }

    override fun setupToolbar() {
        viewModel.setToolbarItems(ToolbarModel(false, null, false))
    }

    override fun getViewModelClass(): Class<SplashViewModel> = SplashViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initializeScreenVariables() {
        observeData()
        viewModel.callAPi()
        Log.i("madmad", "initializeScreenVariables: gwew")
    }

    private fun observeData() {
        viewModel.apicallLiveData.observe(viewLifecycleOwner){
            Log.i("madmad", "observeData error: ${it?.errors.toString()}")
            Log.i("madmad", "observeData data: ${it?.data.toString()}")
        }

    }

    override fun onResume() {
        super.onResume()
        GlobalScope.launch(context = Dispatchers.Main) {
            delay(3000)
            val action = SplashFragmentDirections.actionSplashFragmentToNavigationHome()
            getDataBinding().main.findNavController().navigate(action)
        }
    }

}
