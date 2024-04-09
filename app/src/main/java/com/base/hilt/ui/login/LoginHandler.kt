package com.base.hilt.ui.login

import androidx.navigation.fragment.findNavController
import com.apollographql.apollo3.api.Optional
import com.base.hilt.type.LoginInput


class LoginHandler(private val mContext: LoginFragment) {
    fun onLoginClick(loginValidator: LoginValidator) {
        mContext.view?.let {
            if (loginValidator.isFormValidated(mContext)) {

                mContext.mLoginModel = LoginInput(
                    Optional.Present(""),
                    Optional.Present(""),
                    Optional.Present(""),
                    loginValidator.password,
                    loginValidator.username,
                    Optional.Present(""))

                mContext.viewModel.callLoginApiModel(mContext.mLoginModel)

                /*
                                val openAction = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                                mContext.findNavController().navigate(openAction)

                */

            }
        }
    }

    fun navigateToCreateAccount() {
        val openAction = LoginFragmentDirections.actionLoginFragmentToNavigationCreate()
        mContext.findNavController().navigate(openAction)
    }

}