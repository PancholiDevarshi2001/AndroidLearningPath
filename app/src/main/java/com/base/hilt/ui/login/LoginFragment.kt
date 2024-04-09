package com.base.hilt.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import androidx.navigation.fragment.findNavController
import com.apollographql.apollo3.api.Optional
import com.base.hilt.R
import com.base.hilt.adapter.LoginMutation_ResponseAdapter
import com.base.hilt.base.FragmentBase
import com.base.hilt.base.ToolbarModel
import com.base.hilt.databinding.FragmentLoginBinding
import com.base.hilt.network.ResponseHandler
import com.base.hilt.type.LoginInput
import com.base.hilt.utils.MyPreference
import com.base.hilt.utils.PrefKey
import com.base.hilt.utils.PrefKey.TOKEN
import com.base.hilt.utils.SingleLiveEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.reflect.jvm.internal.impl.util.CheckResult.SuccessCheck

@AndroidEntryPoint
class LoginFragment : FragmentBase<LoginViewModel, FragmentLoginBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_login

    var mLoginModel = LoginInput(password = "", phone = "")


    @Inject
    lateinit var pref: MyPreference
    override fun setupToolbar() {
        viewModel.setToolbarItems(ToolbarModel(false, "", false))
    }

    override fun initializeScreenVariables() {

        getDataBinding().loginHandler = LoginHandler(this@LoginFragment)
        getDataBinding().validatorData = LoginValidator()
//        getDataBinding().btnLogin.setOnClickListener {
//
//            viewModel.callLoginApiModel(
//                LoginInput(
//                    phone = getDataBinding().etUserName.text.toString().trim(),
//                    password = getDataBinding().etPassword.text.toString().trim(),
//                    device_id = Optional.Absent,
//                    device_type = Optional.Absent,
//                    ip_address = Optional.Absent,
//                    user_timezone = Optional.Absent
//                )
//            )
//        }
        viewModel.apicallLiveData.observe(this, Observer {
            if (it == null) {
                return@Observer
            }
            when (it) {
                is ResponseHandler.Loading -> {

                    viewModel.showProgressBar(true)
                }

                is ResponseHandler.OnFailed -> {

                    viewModel.showProgressBar(false)
                    httpFailedHandler(it.code!!, it.message, null)
                }

                is ResponseHandler.OnSuccessResponse -> {

                    viewModel.showProgressBar(false)
                    getDataBinding().apply {
                        Log.d(
                            "Responce",
                            "accesstoken: ${it.response.data?.login?.data?.access_token}"
                        )
                        findNavController().navigate(R.id.action_loginFragment_to_navigation_home)
                        val accessToken = it.response.data?.login?.data?.access_token
                        if (accessToken != null) {
                            pref.setValueString(TOKEN, accessToken)
                        }
                        pref.setValueBoolean(PrefKey.IS_USERlOGIN, true)
                    }
                }
            }
        })

    }

    override fun getViewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java
}