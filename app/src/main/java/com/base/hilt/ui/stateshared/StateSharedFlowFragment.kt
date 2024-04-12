package com.base.hilt.ui.stateshared

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.base.hilt.R
import com.base.hilt.base.FragmentBase
import com.base.hilt.base.ToolbarModel
import com.base.hilt.databinding.FragmentStateSharedFlowBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StateSharedFlowFragment :
    FragmentBase<StateSharedViewModel, FragmentStateSharedFlowBinding>() {
    override fun getLayoutId(): Int {return R.layout.fragment_state_shared_flow}
    override fun setupToolbar() {
        viewModel.setToolbarItems(ToolbarModel(true, "Statesharedflow", true))
    }

    override fun initializeScreenVariables() {
        getDataBinding().btnFlow.setOnClickListener {
            lifecycleScope.launch {
                viewModel.triggerflow().collectLatest {
                    getDataBinding().tvFlow.text = it
                }
            }
        }
        observeData()

    }

    fun observeData() {
        viewModel.liveData.observe(this) {
            getDataBinding().tvLiveData.text = it
        }
        lifecycleScope.launchWhenStarted {
            viewModel.stateFlow.collectLatest {
                getDataBinding().tvStateFlow.text = it
                Snackbar.make(getDataBinding().root, it, Snackbar.LENGTH_LONG).show()
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.sharedFlow.collectLatest {
                getDataBinding().tvSharedFlow.text = it
                Snackbar.make(getDataBinding().root, it, Snackbar.LENGTH_LONG).show()
            }
        }


    }

    override fun getViewModelClass(): Class<StateSharedViewModel> = StateSharedViewModel::class.java

}