package com.example.shoppingcenter.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shoppingcenter.R
import com.example.shoppingcenter.data.Product
import com.example.shoppingcenter.data.ProductsService
import com.example.shoppingcenter.databinding.ActivityProductBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductBinding

    lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id = intent.getStringExtra("PRODUCT_ID")!!

        getProductById(id)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_product, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {
                Toast.makeText(applicationContext, "¡Mensaje de ejemplo!", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_share -> {

                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun loadData() {
        supportActionBar?.title = product.title
        binding.productTextView.text = product.title
        binding.descriptionTextView.text = product.description
        binding.priceTextView.text = "${product.price}€"
        binding.ratingTextView.text = product.rating.toString()

        Picasso.get().load(product.thumbnail).into(binding.productImageView)

    }

    fun getProductById(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = ProductsService.getInstance()
                product = service.findProductById(id)

                CoroutineScope(Dispatchers.Main).launch {
                    loadData()
                }
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }
}