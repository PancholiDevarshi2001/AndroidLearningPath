package com.base.hilt.ui.login

import androidx.databinding.BaseObservable
import com.base.hilt.R
import com.base.hilt.utils.Validation

class LoginValidator : BaseObservable() {

    var username: String = ""
    var password: String = ""

    fun isFormValidated(
        context: LoginFragment
    ): Boolean {
        var isFormValidated = true
        if (username.trim().isEmpty()) {
            isFormValidated = false
            context.getDataBinding().tilUserName.error =
                context.resources.getString(R.string.please_enter_mobile_number)
        } else if (username.trim().length < 12) {
            isFormValidated = false
            context.getDataBinding().tilUserName.error =
                context.resources.getString(R.string.mobile_number_max_length_exceeded)
        } else {
            context.getDataBinding().tilUserName.error = null
        }
        if (password.trim().isBlank()) {
            isFormValidated = false
            context.getDataBinding().tilPassword.error =
                context.resources.getString(R.string.please_enter_password)
        } else if (password.trim().length < 8) {
            isFormValidated = false
            context.getDataBinding().tilPassword.error =
                context.resources.getString(R.string.please_enter_minimum_password)
        } else if (!Validation.isValidPassword(password.trim())) {
            isFormValidated = false
            context.getDataBinding().tilPassword.error =
                context.resources.getString(R.string.please_enter_valid_password)
        } else {
            context.getDataBinding().tilPassword.error = null
        }
        return isFormValidated
    }


}