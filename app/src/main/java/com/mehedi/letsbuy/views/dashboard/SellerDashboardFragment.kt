package com.mehedi.letsbuy.views.dashboard

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.mehedi.letsbuy.R
import com.mehedi.letsbuy.base.BaseFragment
import com.mehedi.letsbuy.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SellerDashboardFragment :
    BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    @Inject
    lateinit var mAuth: FirebaseAuth


    override fun setListener() {

        binding.apply {
            btnLogout.setOnClickListener {
                mAuth.signOut()
                findNavController().navigate(R.id.action_dashboardFragment_to_startFragment)
                Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_LONG).show()

            }


        }


    }

    override fun allObserver() {

    }


}