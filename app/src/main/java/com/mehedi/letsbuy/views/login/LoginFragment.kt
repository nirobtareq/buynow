package com.mehedi.letsbuy.views.login

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mehedi.letsbuy.base.BaseFragment
import com.mehedi.letsbuy.core.DataState
import com.mehedi.letsbuy.core.Nodes
import com.mehedi.letsbuy.databinding.FragmentLoginBinding
import com.mehedi.letsbuy.isEmpty
import com.mehedi.letsbuy.views.dashboard.customer.CustomerDashboard
import com.mehedi.letsbuy.views.dashboard.seller.SellerDashboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()
    override fun setListener() {

        with(binding) {
            btnLogin.setOnClickListener {
                etEmail.isEmpty()
                etPassword.isEmpty()

                if (!etEmail.isEmpty() && !etPassword.isEmpty()) {
                    val user = UserLogin(etEmail.text.toString(), etPassword.text.toString())
                    viewModel.userLogin(user)
                    loading.show()
                }
            }
        }


    }

    //dry
    override fun allObserver() {
        viewModel.loginResponse.observe(viewLifecycleOwner) {

            when (it) {
                is DataState.Error -> {
                    loading.dismiss()
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                is DataState.Loading -> {
                    loading.show()
                    Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
                }

                is DataState.Success -> {
                    loading.dismiss()

                    it.data?.apply {

                        when (userType) {
                            Nodes.USER_TYPE_CUSTOMER -> {
                                startActivity(
                                    Intent(
                                        requireContext(),
                                        CustomerDashboard::class.java
                                    )
                                )
                                requireActivity().finish()
                            }

                            Nodes.USER_TYPE_SELLER -> {
                                startActivity(Intent(requireContext(), SellerDashboard::class.java))
                                requireActivity().finish()
                            }

                            else -> {
                                Toast.makeText(
                                    requireContext(),
                                    " Something went wrong",
                                    Toast.LENGTH_LONG
                                ).show()

                            }
                        }

                    }


                }
            }

        }
    }


}