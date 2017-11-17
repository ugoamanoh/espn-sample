package com.bamtech.multisport.mobile.onboarding

import com.bamtech.multisport.mobile.onboarding.ui.LoginView
import com.bamtech.multisport.mobile.onboarding.utils.Validator
import javax.inject.Inject

/**
 * Created by ugoamanoh on 8/8/17.
 */
class LoginPresenter @Inject constructor(private val validator: Validator) {

    private lateinit var loginView: LoginView
    private var isPasswordValid: Boolean = false
    private var isEmailValid: Boolean = false


    fun attachView(loginView: LoginView) {
        this.loginView = loginView
    }

    fun onEmailTextChanged(text: CharSequence?) {
        isEmailValid = validateEmail(text.toString())
        if (!text.isNullOrEmpty()) {
            if (!isEmailValid) {
                loginView.showEmailError()
            } else {
                loginView.hideEmailError()
            }
        }

        setSubmitButtonState()
    }

    fun onPasswordTextChanged(text: CharSequence?) {
        isPasswordValid = validatePassword(text.toString())
        if (!text.isNullOrEmpty()) {
            if (!isPasswordValid) {
                loginView.showPasswordError()
            } else {
                loginView.hidePasswordError()
            }
        }

        setSubmitButtonState()
    }

    private fun validateEmail(email: String): Boolean {
        return validator.validateEmail(email);
    }

    private fun validatePassword(password: String): Boolean {
        return validator.validatePassword(password)
    }

    private fun setSubmitButtonState() {
        if (isEmailValid && isPasswordValid) {
            loginView.enableSubmitButton()
        } else {
            loginView.disableSubmitButton()
        }
    }

    fun onSubmit(email: String?, password: String?) {
        loginView.showProgressDialog()
        // do login
        loginView.loginSuccessful()
    }

    fun forgotPasswordClicked() {
        loginView.showForgotPassword()
    }

    fun toolbarNavigationClicked() {
        loginView.dismiss()
    }

    fun onResume() {
        loginView.showKeyboard();
    }

}