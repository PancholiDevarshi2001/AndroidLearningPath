package com.base.hilt.ui.createAccount

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.apollographql.apollo3.api.Optional
import com.base.hilt.R
import com.base.hilt.base.FragmentBase
import com.base.hilt.base.ToolbarModel
import com.base.hilt.databinding.FragmentCreateAccountBinding
import com.base.hilt.network.ResponseHandler
import com.base.hilt.type.OtpInput
import com.base.hilt.type.SignUpInput
import com.base.hilt.ui.login.LoginHandler
import com.base.hilt.ui.login.LoginValidator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccountFragment : FragmentBase<CreateAccountViewModel,FragmentCreateAccountBinding>() {
    var mSignupModel = SignUpInput()
    override fun getLayoutId(): Int {
        return  R.layout.fragment_create_account
    }

    override fun setupToolbar() {
        viewModel.setToolbarItems(ToolbarModel(false, "", false))
    }

    override fun initializeScreenVariables() {
        getDataBinding().signupHandler = CreateAccountHandler(this@CreateAccountFragment)
        getDataBinding().signupValidator = CreateAccountValidator()
        observeData()
    }

    private fun observeData() {

        viewModel.otpVerifyLiveData.observe(this, Observer {
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
                        if (it?.response.data?.verifySmsOtp?.meta?.status_code == 200){
                            findNavController().popBackStack()
                            Toast.makeText(requireContext(), it.response.data!!.verifySmsOtp.meta.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        })


        viewModel.signUpLiveData.observe(this, Observer {
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
                        Log.d("TAG", "observeData: ApiResponce${it.response.data}")
                        if (it.response.data?.signup?.meta?.status_code == 200){
                            Toast.makeText(requireContext(), it.response.data?.signup?.meta?.message, Toast.LENGTH_SHORT).show()
                            val otp = it.response.data!!.signup.data?.otp
                            val uuid = it.response.data!!.signup.data?.uuid
                            Log.i("Tag", "observeData: $otp")
                            viewModel.otpVerifyApi(OtpInput(
                                otp = Optional.Present(otp),
                                type = Optional.Present("1"),
                                uuid = Optional.Present(uuid)
                            ))
                        }else{
                            Toast.makeText(requireContext(), it?.response?.data?.signup?.meta?.message, Toast.LENGTH_SHORT).show()

                        }

                    }

                }
            }
        })


    }
    override fun getViewModelClass(): Class<CreateAccountViewModel> = CreateAccountViewModel::class.java
}