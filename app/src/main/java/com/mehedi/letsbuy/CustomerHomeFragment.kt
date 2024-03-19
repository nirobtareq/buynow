package com.mehedi.letsbuy

import android.util.Log
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.mehedi.letsbuy.base.BaseFragment
import com.mehedi.letsbuy.core.DataState
import com.mehedi.letsbuy.data.Cart
import com.mehedi.letsbuy.data.Product
import com.mehedi.letsbuy.databinding.FragmentCustomerHomeBinding
import com.mehedi.letsbuy.views.dashboard.customer.CustomerProductAdapter
import com.mehedi.letsbuy.views.dashboard.customer.ProductViewModelCustomer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerHomeFragment :
    BaseFragment<FragmentCustomerHomeBinding>(FragmentCustomerHomeBinding::inflate),
    CustomerProductAdapter.CartClickListener {

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


        val customerProductAdapter = CustomerProductAdapter(it, this)

        binding.rvCustomerProduct.adapter = customerProductAdapter


    }

    override fun onCartClick(product: Product) {
        Log.d("TAG", "onCartClick: $product ")

        val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
        val cart = Cart().apply {
            userID = uid
            name = product.name
            price = product.price
            imageLink = product.imageLink
            description = product.description
            amount = product.amount
            sellerID = product.sellerID
            productID = product.productID
        }


        viewmodel.addToCart(cart, uid)
    }


}