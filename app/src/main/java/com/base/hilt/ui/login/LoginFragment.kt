package com.base.hilt.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.apollographql.apollo3.api.Optional
import com.base.hilt.R
import com.base.hilt.base.FragmentBase
import com.base.hilt.base.ToolbarModel
import com.base.hilt.databinding.FragmentLoginBinding
import com.base.hilt.network.ResponseHandler
import com.base.hilt.type.LoginInput
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.jvm.internal.impl.util.CheckResult.SuccessCheck

@AndroidEntryPoint
class LoginFragment : FragmentBase<LoginViewModel, FragmentLoginBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_login

    var res = ""

    override fun setupToolbar() {
    }

    override fun initializeScreenVariables() {

        getDataBinding().btnLogin.setOnClickListener {
            Log.d("Responce", "initializeScreenVariables: ${it}")
            viewModel.callLoginApiModel(
                LoginInput
                    phone = "+11011111111",
                    password = "Test@1234",
                    device_id = Optional.Present (""),
                    device_type = Optional.Present (""),
                    ip_address = Optional.Present (""),
                    user_timezone = Optional.Present ("")
                )
            )
        }
        viewModel.apicallLiveData.observe(viewLifecycleOwner) { apiResponse ->
            Toast.makeText(requireContext(),"${apiResponse.toString()}", Toast.LENGTH_SHORT).show()
            res = apiResponse?.data.toString()+apiResponse?.errors.toString()
            getDataBinding().tvREsponce.text = res

        }

    }

    override fun getViewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java


}