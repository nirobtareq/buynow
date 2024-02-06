package com.mehedi.letsbuy.views.starter

import androidx.navigation.fragment.findNavController
import com.mehedi.letsbuy.R
import com.mehedi.letsbuy.base.BaseFragment
import com.mehedi.letsbuy.databinding.FragmentStartBinding


class StartFragment : BaseFragment<FragmentStartBinding>(FragmentStartBinding::inflate) {

    override fun setListener() {


        with(binding) {
            btnLogin.setOnClickListener {
                findNavController().navigate(R.id.action_startFragment_to_loginFragment)
            }

            btnRegister.setOnClickListener {
                findNavController().navigate(R.id.action_startFragment_to_registerFragment)
            }
        }
    }

    override fun allObserver() {


    }
}