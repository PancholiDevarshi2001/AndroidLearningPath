package com.base.hilt.ui.notifications

import android.content.Intent
import com.base.hilt.MainActivity
import com.base.hilt.R
import com.base.hilt.base.FragmentBase
import com.base.hilt.base.LocaleManager
import com.base.hilt.base.ToolbarModel
import com.base.hilt.base.ViewModelBase
import com.base.hilt.databinding.FragmentNotificationsBinding
import com.base.hilt.utils.MyPreference
import com.base.hilt.utils.PrefKey
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotificationsFragment : FragmentBase<ViewModelBase, FragmentNotificationsBinding>() {

    @Inject
    lateinit var localeManager: LocaleManager

    @Inject
    lateinit var mPref: MyPreference
    override fun getLayoutId(): Int {
        return R.layout.fragment_notifications
    }

    override fun setupToolbar() {
        viewModel.setToolbarItems(ToolbarModel(true, "Change Language", true))
    }

    override fun initializeScreenVariables() {

//        getDataBinding().viewModel = viewModel

        observeData()
    }

    private fun observeData() {

    }

    override fun getViewModelClass(): Class<ViewModelBase> =
        ViewModelBase::class.java

}