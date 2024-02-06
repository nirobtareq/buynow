package com.mehedi.letsbuy.views.register

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mehedi.letsbuy.base.BaseFragment
import com.mehedi.letsbuy.core.DataState
import com.mehedi.letsbuy.databinding.FragmentRegisterBinding
import com.mehedi.letsbuy.isEmpty


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {


    val viewModel: RegistrationViewModel by viewModels()


    override fun setListener() {

        with(binding) {

            btnRegister.setOnClickListener {
                etName.isEmpty()
                etEmail.isEmpty()
                etPassword.isEmpty()


                if (!etName.isEmpty() && !etEmail.isEmpty() && !etPassword.isEmpty()) {
                    //Toast.makeText(context, "All input done !", Toast.LENGTH_LONG).show()

                    val user = User(
                        etName.text.toString(),
                        etEmail.text.toString(),
                        etPassword.text.toString(),
                        "Seller",
                        ""
                    )

                    viewModel.userRegistration(user)

                }
            }


        }


    }

    override fun allObserver() {
        registrationObserver()

    }

    //OOAD -> Object Oriented Analysis and design
    private fun registrationObserver() {
        viewModel.registrationResponse.observe(viewLifecycleOwner) {

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
                    Toast.makeText(context, " created User : ${it.data}", Toast.LENGTH_SHORT).show()
                }
            }


        }

    }

}