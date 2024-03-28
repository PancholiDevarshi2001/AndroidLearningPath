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
import com.base.hilt.utils.MyPreference
import com.base.hilt.utils.PrefKey
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.reflect.jvm.internal.impl.util.CheckResult.SuccessCheck

@AndroidEntryPoint
class LoginFragment : FragmentBase<LoginViewModel, FragmentLoginBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_login


    @Inject
    lateinit var pref: MyPreference
    override fun setupToolbar() {
        viewModel.setToolbarItems(ToolbarModel(false, "", false))
    }

    override fun initializeScreenVariables() {

        getDataBinding().tvcreateAccount.setOnClickListener {

            val action = LoginFragmentDirections.actionLoginFragmentToNavigationCreate()
            findNavController().navigate(action)
        }


        getDataBinding().btnLogin.setOnClickListener {
            Log.d("Responce", "initializeScreenVariables: $it")
            viewModel.callLoginApiModel(
                LoginInput(
                    phone = getDataBinding().etUserName.toString().trim(),
                    password = getDataBinding().etPassword.toString().trim(),
                    device_id = Optional.Present(""),
                    device_type = Optional.Present(""),
                    ip_address = Optional.Present(""),
                    user_timezone = Optional.Present("")
                )
            )
        }
        viewModel.apicallLiveData.observe(viewLifecycleOwner) { apiResponse ->
            Toast.makeText(
                requireContext(),
                apiResponse?.data?.login?.meta?.message,
                Toast.LENGTH_SHORT
            ).show()
            val accesstoken = apiResponse?.data?.login?.data?.access_token
            if (accesstoken != null) {
                pref.setValueString(PrefKey.TOKEN, accesstoken)
            }
            pref.setValueBoolean(PrefKey.IS_USERlOGIN, true)
            Log.i("2181", "initializeScreenVariables:$accesstoken ")
        }
    }

    override fun getViewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java
}