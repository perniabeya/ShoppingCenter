package com.example.shoppingcenter.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shoppingcenter.R
import com.example.shoppingcenter.data.Product
import com.example.shoppingcenter.data.ProductsService
import com.example.shoppingcenter.databinding.ActivityProductsBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductsActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductsBinding

    lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id = intent.getStringExtra("PRODUCT_ID")!!

        fun getRetrofit(): ProductsService{
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/products?sortBy=title&order=asc\n" +
                        ".then(res => res.json())\n" +
                        ".then(console.log);")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ProductsService::class.java)
        }
    }
}