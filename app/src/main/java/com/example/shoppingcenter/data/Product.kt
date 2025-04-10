package com.example.shoppingcenter.data

class ProductsResponse (
    val response: String,
    val products: List<Product>
)

data class Product(
    val id: String,
    val title: String,
    val thumbnail: String
)