package com.base.hilt.ui.createAccount

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
import com.base.hilt.databinding.FragmentCreateAccountBinding
import com.base.hilt.type.OtpInput
import com.base.hilt.type.SignUpInput
import com.base.hilt.utils.PrefKey
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccountFragment : FragmentBase<CreateAccountViewModel,FragmentCreateAccountBinding>() {
    override fun getLayoutId(): Int {
       return  R.layout.fragment_create_account
    }

    override fun setupToolbar() {
        viewModel.setToolbarItems(ToolbarModel(false, "", false))
    }

    override fun initializeScreenVariables() {
        observeData()
    }

    private fun observeData() {
        getDataBinding().btnCreateAcount.setOnClickListener {
            viewModel.signUpApi(
                SignUpInput(
                    first_name =  Optional.Present(getDataBinding().etFirstName.toString().trim()),
                    last_name =  Optional.Present(getDataBinding().etLastName.toString().trim()),
                    alias =  Optional.Present(getDataBinding().etAliasName.toString().trim()),
                    mobile_number = Optional.Present(getDataBinding().etMobileNumber.toString().trim().filter {it.isDigit()}),
                    password = Optional.Present(getDataBinding().etPassword.toString().trim()),
                    confirm_password = Optional.Present(getDataBinding().etConfirmPassword.toString().trim()),
                    email = Optional.Present(getDataBinding().etEmail.toString().trim()),
                    dob = Optional.Present("08-11-1998"),
                    referral_code = Optional.Present(""),
                    device_id = Optional.Present(""),
                    device_type = Optional.Present("1"),
                    ip_address = Optional.Present("192.168.1.45"),
                    user_timezone = Optional.Present("Asia/Culcutta")
                )
            )
            viewModel.signUpLiveData.observe(viewLifecycleOwner) { apiResponse ->
                if (apiResponse != null) {
                    Log.d("TAG", "observeData: ApiResponce${apiResponse.data}")
                }
                if (apiResponse?.data?.signup?.meta?.status_code == 200){
                    Toast.makeText(requireContext(), apiResponse.data?.signup?.meta?.message, Toast.LENGTH_SHORT).show()
                    val otp = apiResponse.data!!.signup.data?.otp
                    val uuid = apiResponse.data!!.signup.data?.uuid
                    Log.i("Tag", "observeData: $otp")
                    viewModel.otpVerifyApi(OtpInput(
                            otp = Optional.Present(otp),
                            type = Optional.Present("1"),
                            uuid = Optional.Present(uuid)
                    ))

                }else{
                    Toast.makeText(requireContext(), apiResponse?.data?.signup?.meta?.message, Toast.LENGTH_SHORT).show()
                }
            }
            viewModel.otpVerifyLiveData.observe(viewLifecycleOwner){
                if (it?.data?.verifySmsOtp?.meta?.status_code == 200){
                    findNavController().popBackStack()
                    Toast.makeText(requireContext(), it.data!!.verifySmsOtp.meta.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun getViewModelClass(): Class<CreateAccountViewModel> = CreateAccountViewModel::class.java
}