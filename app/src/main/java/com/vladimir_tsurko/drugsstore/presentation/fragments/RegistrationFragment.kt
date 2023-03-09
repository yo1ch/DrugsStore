package com.vladimir_tsurko.drugsstore.presentation.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vladimir_tsurko.drugsstore.App
import com.vladimir_tsurko.drugsstore.R
import com.vladimir_tsurko.drugsstore.databinding.FragmentRegistrationBinding
import com.vladimir_tsurko.drugsstore.domain.models.RegistrationModel
import com.vladimir_tsurko.drugsstore.presentation.MainActivity
import com.vladimir_tsurko.drugsstore.presentation.viewmodels.AuthViewModel
import com.vladimir_tsurko.drugsstore.presentation.viewmodels.ViewModelFactory
import com.vladimir_tsurko.drugsstore.utils.Resource
import javax.inject.Inject

class RegistrationFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: AuthViewModel

    private val component by lazy{
        (requireActivity().application as App).component
    }

    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding
        get() = _binding ?: throw RuntimeException("FragmentRegistrationBinding == null")


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? MainActivity)?.hideBottomBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]
        observeRegistrationResponse()
        setupClickListeners()
        checkLoggedUser()
        loginSpan()

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    private fun loginSpan() {
        val spannableText = SpannableString("Already have an account? Log in")
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#254FE6")
                ds.isUnderlineText = false
            }
        }
        spannableText.setSpan(clickableSpan, 25, 31, SpannableString.SPAN_EXCLUSIVE_INCLUSIVE)
        binding.loginText.text = spannableText
        binding.loginText.movementMethod = LinkMovementMethod.getInstance()
    }


    private fun checkLoggedUser(){
        val isUserLogged = viewModel.checkLoggedUser()
        if(isUserLogged){
            findNavController().navigate(R.id.action_registrationFragment_to_main_graph)
        }
    }


    private fun setupClickListeners(){
        binding.registrationButton.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etSignPassword.text.toString()
            val name = binding.etSignFirstName.text.toString()
            val surname = binding.etSignSecondName.text.toString()
            viewModel.registrateUser(
                RegistrationModel(
                    name = name,
                    username = username,
                    password = password,
                    surname = surname,
                )
            )
        }
    }

    private fun observeRegistrationResponse(){
        viewModel.registrationResponse.observe(viewLifecycleOwner){
            when(it) {
                is Resource.Success ->{
                    findNavController().navigate(R.id.action_registrationFragment_to_main_graph)
                }
                is Resource.Error -> Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                else -> {}
            }
        }

    }

}