package com.example.shoppingcenter.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shoppingcenter.R
import com.example.shoppingcenter.adapters.ProductsAdapter
import com.example.shoppingcenter.data.Product
import com.example.shoppingcenter.databinding.ActivityCategoryBinding
import com.example.shoppingcenter.data.ProductsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryActivity : AppCompatActivity() {

    lateinit var binding: ActivityCategoryBinding

    lateinit var productList: List<Product>
    lateinit var adapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id = intent.getStringExtra("CATEGORY_ID")!!
        val name = intent.getStringExtra("CATEGORY_NAME")!!

        supportActionBar?.title = name

        adapter = ProductsAdapter(emptyList()) { position ->
            val product = productList[position]

            val intent = Intent(this, ProductActivity::class.java)
            intent.putExtra("PRODUCT_ID", product.id)
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        getProductsByCategory(id)
    }

    fun loadData() {
        adapter.items = productList
        adapter.notifyDataSetChanged()
    }

    fun getProductsByCategory(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = ProductsService.getInstance()
                productList = service.findProductsByCategory(id).products

                CoroutineScope(Dispatchers.Main).launch {
                    loadData()
                }
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }
}