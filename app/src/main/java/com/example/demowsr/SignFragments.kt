package com.example.demowsr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.demowsr.databinding.FragmentSignInBinding
import com.example.demowsr.databinding.FragmentSignUpBinding

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                startFragment(binding.root.context, OnBoardingFragment.newInstance(1))
            }
        })

        binding.loginButton.setOnClickListener { onClickLogin() }
    }

    private fun onClickLogin() {
        if(etIsEmpty(binding.etMail)){
            etShowError(binding.etMail, "Пустое поле почты")
            return
        }
        if(etIsEmpty(binding.etPassword)){
            etShowError(binding.etPassword, "Пустое поле пароля")
            return
        }

        loginUser()
    }

    private fun loginUser(){
        var strMail: String = binding.etMail.text.toString()
        var strPassword: String = binding.etPassword.text.toString()

        startFragment(binding.root.context, MainScreenFragment.newInstance())
    }

    companion object{
        fun newInstance() = SignInFragment()
    }
}

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                startFragment(binding.root.context, OnBoardingFragment.newInstance(1))
            }
        })
        binding.cancelButton.setOnClickListener {
            startFragment(it.context, OnBoardingFragment.newInstance(1))
        }
        binding.registerButton.setOnClickListener { onClickRegister() }
    }

    private fun onClickRegister() {

        if(etIsEmpty(binding.etMail)){
            etShowError(binding.etMail, "Пустое поле почты")
            return
        }
        if(etIsEmpty(binding.etPassword)){
            etShowError(binding.etPassword, "Пустое поле пароля")
            return
        }
        if(etIsEmpty(binding.etName)){
            etShowError(binding.etName, "Пустое поле имени")
            return
        }
        if(etIsEmpty(binding.etPhone)){
            etShowError(binding.etPhone, "Пустое поле телефона")
            return
        }

        registerUser()
    }

    private fun registerUser(){
        var strMail: String = binding.etMail.text.toString()
        var strPassword: String = binding.etPassword.text.toString()
        var strName: String = binding.etName.text.toString()
        var strPhone: String = binding.etPhone.text.toString()

        startFragment(binding.root.context, MainScreenFragment.newInstance())
    }

    companion object{
        fun newInstance() = SignUpFragment()
    }


}