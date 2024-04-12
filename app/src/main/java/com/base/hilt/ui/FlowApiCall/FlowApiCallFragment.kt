package com.base.hilt.ui.FlowApiCall


import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.hilt.R
import com.base.hilt.base.FragmentBase
import com.base.hilt.base.ToolbarModel
import com.base.hilt.databinding.FragmentFlowApiCallBinding
import com.base.hilt.network.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlowApiCallFragment : FragmentBase<FlowApiCallViewModel, FragmentFlowApiCallBinding>() {

    private lateinit var adapter: ApiUserAdapter
    override fun getLayoutId():Int = R.layout.fragment_flow_api_call

    override fun setupToolbar() {
        viewModel.setToolbarItems(ToolbarModel(false, "", false))
    }

    override fun initializeScreenVariables() {
        setupObserver()
        setupUI()
    }
    override fun getViewModelClass(): Class<FlowApiCallViewModel> = FlowApiCallViewModel::class.java
    private fun setupUI() {
        getDataBinding().recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter =ApiUserAdapter(requireContext(), arrayListOf(), R.layout.item_layout)

        getDataBinding().recyclerView.addItemDecoration(
            DividerItemDecoration(
                getDataBinding().recyclerView.context,
                (getDataBinding().recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        getDataBinding().recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                when (it) {
                    is UiState.Success -> {
                        getDataBinding().progressBar.visibility = View.GONE
                        Log.i("mad2", "setupObserver: med:${it.data.toString()}")
                        renderList(it.data)
                        getDataBinding().recyclerView.visibility = View.VISIBLE
                    }
                    is UiState.Loading -> {
                        getDataBinding().progressBar.visibility = View.VISIBLE
                        getDataBinding().recyclerView.visibility = View.GONE
                    }
                    is UiState.Error -> {
                        getDataBinding().progressBar.visibility = View.GONE
                        Log.i("mad2", "setupObserver: ${it.message}")
                    }
                }
            }
        }
    }

    private fun renderList(users: List<ApiUserModel>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }



}