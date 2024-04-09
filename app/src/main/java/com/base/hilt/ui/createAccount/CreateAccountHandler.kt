package com.base.hilt.ui.createAccount

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.api.Optional
import com.base.hilt.type.OtpInput
import com.base.hilt.type.SignUpInput
import com.base.hilt.ui.login.LoginFragmentDirections


class CreateAccountHandler(private val mContext: CreateAccountFragment) {

    fun onSignupClick(createAccountValidator: CreateAccountValidator) {




           mContext.view?.let {

               if (createAccountValidator.isFormValidated(mContext)) {

                   mContext.mSignupModel = SignUpInput(
                       alias = Optional.Present(createAccountValidator.userName),
                       confirm_password = Optional.Present(createAccountValidator.confirmPassword),
                       device_id = Optional.Present("f4r345g4w554454ef43r4r34r"),
                       device_type = Optional.Present("1"),
                       dob = Optional.Present("12-12-2002"),
                       email = Optional.Present(createAccountValidator.email),
                       first_name = Optional.Present(createAccountValidator.firstName),
                       ip_address = Optional.Present("192.168.1.145"),
                       last_name = Optional.Present(createAccountValidator.lastName),
                       mobile_number = Optional.Present(createAccountValidator.mobileNumber),
                       password = Optional.Present(createAccountValidator.password),
                       referral_code = Optional.Absent,
                       user_timezone = Optional.Present("Asia/Culcutta")
                   )
                   mContext.viewModel.signUpApi(mContext.mSignupModel)

               }

           }


    }

}