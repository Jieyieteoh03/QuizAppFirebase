package com.jy.quizappfirebase.ui.authentication.login


import android.view.View
import androidx.fragment.app.viewModels
import com.jy.quizappfirebase.R
import com.jy.quizappfirebase.databinding.FragmentLoginBinding
import com.jy.quizappfirebase.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val viewModel: LoginViewModel by viewModels()
    override fun getLayoutResource(): Int = R.layout.fragment_login

    override fun onBindView(view: View) {
        super.onBindView(view)

        binding?.run {
            btnLogin.setOnClickListener {
                viewModel.login(
                    etEmail.text.toString(),
                    etPassword.text.toString(),
                )
            }
        }
    }
}