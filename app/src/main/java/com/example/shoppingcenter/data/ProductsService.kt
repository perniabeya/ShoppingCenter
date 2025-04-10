package com.example.shoppingcenter.data


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsService {

    @GET("category/{categoryId}")
    suspend fun findProductsByCategory(@Path("categoryId") category: String): ProductsResponse

    @GET("search/{name}")
    suspend fun findProductByName(@Path("name") query: String): ProductsResponse

    @GET("{products-id}")
    suspend fun findProductById(@Path("products-id") id: String): Product

    companion object {
        fun getInstance(): ProductsService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/products/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ProductsService::class.java)
        }
    }
}