package com.mehedi.letsbuy.views.dashboard.customer

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mehedi.letsbuy.data.Product
import com.mehedi.letsbuy.databinding.ItemProductBinding
import com.mehedi.letsbuy.databinding.ItemSellerProductBinding

class CustomerProductAdapter(val productList: List<Product>) :
    RecyclerView.Adapter<CustomerProductAdapter.ProductViewHolder>() {


    class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        productList[position].let {

            holder.binding.apply {
                txtProductName.text = it.name
                txtProductDescription.text = it.description
                txtProductPrice.text = "Price $ ${it.price}"
                txtProductStock.text = "Stock $ ${it.amount}"
                ivProduct.load(it.imageLink)


            }


        }
    }


}