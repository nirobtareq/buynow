package com.mehedi.letsbuy

import androidx.fragment.app.viewModels
import com.mehedi.letsbuy.base.BaseFragment
import com.mehedi.letsbuy.core.DataState
import com.mehedi.letsbuy.data.Product
import com.mehedi.letsbuy.databinding.FragmentCustomerHomeBinding
import com.mehedi.letsbuy.views.dashboard.customer.CustomerProductAdapter
import com.mehedi.letsbuy.views.dashboard.customer.ProductViewModelCustomer
import com.mehedi.letsbuy.views.dashboard.seller.products.SellerProductAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerHomeFragment :
    BaseFragment<FragmentCustomerHomeBinding>(FragmentCustomerHomeBinding::inflate) {

    private val viewmodel: ProductViewModelCustomer by viewModels()


    override fun setListener() {

    }

    override fun allObserver() {

        viewmodel.productResponse.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Error -> {
                    loading.dismiss()
                }

                is DataState.Loading -> {
                    loading.show()
                }

                is DataState.Success -> {
                    it.data?.let { it1 -> setDataToRV(it1) }
                    loading.dismiss()
                }
            }

        }

    }

    private fun setDataToRV(it: List<Product>) {

        binding.rvCustomerProduct.adapter = CustomerProductAdapter(it)


    }


}