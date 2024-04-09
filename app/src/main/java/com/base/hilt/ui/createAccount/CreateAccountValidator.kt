package com.base.hilt.ui.createAccount

import androidx.databinding.BaseObservable
import com.base.hilt.R
import com.base.hilt.ui.login.LoginFragment
import com.base.hilt.utils.Validation

class CreateAccountValidator : BaseObservable() {

    var firstName: String = ""
    var lastName: String = ""
    var userName: String = ""
    var mobileNumber: String = ""
    var password: String = ""
    var confirmPassword: String = ""
    var email: String = ""
    var dob : String = ""

    fun isFormValidated(
        context: CreateAccountFragment
    ): Boolean {
        var isFormValidated = true

        if (firstName.trim().isEmpty()) {
            isFormValidated = false
            context.getDataBinding().tilFirstName.error =
                context.resources.getString(R.string.please_enter_first_name)
        }
        else {
            context.getDataBinding().tilFirstName.error = null
        }

        if (lastName.trim().isEmpty()) {
            isFormValidated = false
            context.getDataBinding().tilLastName.error =
                context.resources.getString(R.string.please_enter_last_name)
        }
        else {
            context.getDataBinding().tilLastName.error = null
        }
        if (userName.trim().isEmpty()) {
            isFormValidated = false
            context.getDataBinding().tilAliasName.error =
                context.resources.getString(R.string.please_enter_username)
        }
        else {
            context.getDataBinding().tilAliasName.error = null
        }
        if (mobileNumber.trim().isEmpty()) {
            isFormValidated = false
            context.getDataBinding().tilMobileNumber.error =
                context.resources.getString(R.string.please_enter_mobile_number)
        } else if (mobileNumber.trim().length < 12) {
            isFormValidated = false
            context.getDataBinding().tilMobileNumber.error =
                context.resources.getString(R.string.mobile_number_max_length_exceeded)
        }
        else {
            context.getDataBinding().tilMobileNumber.error = null
        }
        if (password.trim().isEmpty()) {
            isFormValidated = false
            context.getDataBinding().tilPassword.error =
                context.resources.getString(R.string.please_enter_password)
        } else if (password.trim().length < 8) {
            isFormValidated = false
            context.getDataBinding().tilPassword.error =
                context.resources.getString(R.string.password_min_length_error)
        } else if (!Validation.isValidPassword(password.trim())) {
            isFormValidated = false
            context.getDataBinding().tilPassword.error =
                context.resources.getString(R.string.password_invalid_format_error)
        } else {
            context.getDataBinding().tilPassword.error = null
        }
        if (confirmPassword.trim().isEmpty()) {
            isFormValidated = false
            context.getDataBinding().tilConfirmPassword.error =
                context.resources.getString(R.string.please_enter_confirm_password)
        } else if (confirmPassword.trim() != password.trim()) {
            isFormValidated = false
            context.getDataBinding().tilConfirmPassword.error =
                context.resources.getString(R.string.password_mismatch_error)
        }else {
            context.getDataBinding().tilConfirmPassword.error = null
        }
        if (email.trim().isEmpty()) {
            isFormValidated = false
            context.getDataBinding().tilEmail.error =
                context.resources.getString(R.string.please_enter_email)
        } else if (!Validation.isEmailValid(email.trim())) {
            isFormValidated = false
            context.getDataBinding().tilEmail.error =
                context.resources.getString(R.string.email_invalid_format_error)
        }
        else {
            context.getDataBinding().tilEmail.error = null
        }


        return isFormValidated
    }
}