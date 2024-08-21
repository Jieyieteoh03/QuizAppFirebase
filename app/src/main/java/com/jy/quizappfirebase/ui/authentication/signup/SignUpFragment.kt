package com.jy.quizappfirebase.ui.authentication.signup


import android.view.View
import androidx.fragment.app.viewModels
import com.jy.quizappfirebase.R
import com.jy.quizappfirebase.core.data.model.Role
import com.jy.quizappfirebase.core.data.model.User
import com.jy.quizappfirebase.databinding.FragmentSignUpBinding
import com.jy.quizappfirebase.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {
    override val viewModel: SignUpViewModel by viewModels()
    override fun getLayoutResource(): Int = R.layout.fragment_sign_up

    override fun onBindView(view: View) {
        super.onBindView(view)

        binding?.run {
            btnSignUp.setOnClickListener {
                val role = spRole.selectedItem.toString().uppercase()
                currentUserRole = Role.valueOf(role)
                val user = User(
                    etName.text.toString(),
                    etEmail.text.toString(),
                    Role.valueOf(role)
                )
                viewModel.signUp(
                    user = user,
                    password = etPassword.text.toString(),
                    password2 = etConfirmPassword.text.toString()
                )
            }
        }
    }
}