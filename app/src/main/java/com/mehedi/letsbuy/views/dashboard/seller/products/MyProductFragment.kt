package com.mehedi.letsbuy.views.dashboard.seller.products

import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.mehedi.letsbuy.base.BaseFragment
import com.mehedi.letsbuy.core.DataState
import com.mehedi.letsbuy.data.Product
import com.mehedi.letsbuy.databinding.FragmentMyProductBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MyProductFragment :
    BaseFragment<FragmentMyProductBinding>(FragmentMyProductBinding::inflate) {

    private val viewModel: ProductViewModel by viewModels()


    override fun setListener() {

        FirebaseAuth.getInstance()
            .currentUser?.let {
                viewModel.getProductById(it.uid)
            }


    }

    override fun allObserver() {


        viewModel.productResponse.observe(viewLifecycleOwner) {

            when (it) {
                is DataState.Error -> {
                    loading.dismiss()
                }

                is DataState.Loading -> {
                    loading.show()
                }

                is DataState.Success -> {
                    it.data?.let{ it1 -> setDataToRV(it1) }
                    loading.dismiss()
                }
            }


        }


    }

    private fun setDataToRV(it: List<Product>) {

        binding.rvSellerProduct.adapter = SellerProductAdapter(it)


    }


}