package com.example.shoppingcenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.shoppingcenter.data.Product
import com.example.shoppingcenter.databinding.ItemProductsBinding
import com.squareup.picasso.Picasso


class ProductsAdapter(
    var items: List<Product>,
    val onClick: (Int) -> Unit,
) : Adapter<ProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding = ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val products = items[position]
        holder.render(products)
        holder.itemView.setOnClickListener {
            onClick(position)
        }
    }
}

class ProductsViewHolder(val binding: ItemProductsBinding) : ViewHolder(binding.root) {

    fun render(product: Product) {
        binding.productsNameTextView.text = product.title
        Picasso.get().load(product.thumbnail).into(binding.productsNameImageView)
    }
}