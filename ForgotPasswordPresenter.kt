package com.bamtech.multisport.mobile.onboarding

import com.bamtech.multisport.mobile.onboarding.ui.ForgotPasswordView
import com.bamtech.multisport.mobile.onboarding.utils.Validator
import javax.inject.Inject

/**
 * Created by ugoamanoh on 8/10/17.
 */
class ForgotPasswordPresenter @Inject constructor(private val validator: Validator) {

    private lateinit var view: ForgotPasswordView

    fun attachView(view: ForgotPasswordView) {
        this.view = view
        view.showEnterEmailView()
    }

    fun onSubmit(email: String) {
        view.hideKeyboard()
        view.showEmailSentView(email)
    }

    fun onEmailTextChanged(text: CharSequence?) {
        if (!text.isNullOrEmpty()) {
            if (!validateEmail(text.toString())) {
                view.showEmailError()
                view.disableSubmitButton()
            } else {
                view.hideEmailError()
                view.enableSubmitButton()
            }
        }
    }

    private fun validateEmail(email: String): Boolean {
        return validator.validateEmail(email);
    }

    fun toolbarNavigationClicked() {
        view.dismiss()
    }

    fun onLoginClicked() {
        view.dismiss()
    }

}