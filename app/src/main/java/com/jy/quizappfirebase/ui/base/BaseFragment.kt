package com.jy.quizappfirebase.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.jy.quizappfirebase.R
import com.jy.quizappfirebase.core.data.model.Role
import com.jy.quizappfirebase.ui.authentication.login.LoginFragmentDirections
import com.jy.quizappfirebase.ui.authentication.signup.SignUpFragmentDirections
import kotlinx.coroutines.launch

abstract class BaseFragment<T: ViewBinding> : Fragment() {
    protected abstract val viewModel: BaseViewModel
    protected var binding: T? = null
    protected abstract fun getLayoutResource(): Int
    protected var currentUserRole: Role? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(getLayoutResource(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBindData(view)
        onBindView(view)
    }

    protected open fun onBindView(view: View) {
        binding = DataBindingUtil.bind(view)
    }

    protected open fun onBindData(view: View) {
        lifecycleScope.launch {
            viewModel.error.collect{
                showSnackBar(view, it, true)
            }
        }

        lifecycleScope.launch {
            viewModel.finish.collect{
                showSnackBar(view, it)
            }
        }

        lifecycleScope.launch {
            viewModel.role.collect {
                currentUserRole = it
            }
        }

        lifecycleScope.launch {
            viewModel.success.collect {
                showSnackBar(view, it)
                currentUserRole?.let { role ->
                    if(it.contains("Sign Up")) {
                        findNavController().navigate(
                            when(role) {
                                Role.TEACHER -> SignUpFragmentDirections.actionSignUpToTeacherDashboard()
                                else -> SignUpFragmentDirections.actionSignUpToStudentDashboard()
                            }
                        )
                    }
                    if(it.contains("Login")) {
                        findNavController().navigate(
                            when(role) {
                                Role.TEACHER -> LoginFragmentDirections.actionLoginToTeacherDashboard()
                                else -> LoginFragmentDirections.actionLoginToStudentDashboard()
                            }
                        )
                    }
                }
                if(it.contains("Add")) {
                    findNavController().popBackStack()
                }
            }
        }
    }

    protected fun showSnackBar(view: View, msg:String, isError:Boolean = false) {
        val snackbar = Snackbar.make(view,msg,Snackbar.LENGTH_LONG)
        val color = if(isError) {
            R.color.red
        } else {
            R.color.green
        }
        snackbar.setBackgroundTint(
            ContextCompat.getColor(requireContext(),color)
        )
        snackbar.show()
    }

}