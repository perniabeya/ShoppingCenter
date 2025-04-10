package com.example.shoppingcenter.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shoppingcenter.R
import android.view.View
import android.widget.Toast
import com.example.shoppingcenter.data.Category
import com.example.shoppingcenter.databinding.ActivityCategoryBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryActivity : AppCompatActivity() {

    lateinit var binding: ActivityCategoryBinding

    lateinit var category: Category

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

        val id = intent.getStringExtra("SUPERHERO_ID")!!
        /*getSuperheroById(id)

        binding.navigationBar.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_category -> {
                    binding.appearanceContent.root.visibility = View.VISIBLE
            }
            true
        }

        binding.navigationBar.selectedItemId = R.id.action_category*/
    }

    fun loadData() {
        /*Picasso.get().load(category.image).into(binding.pictureImageView)

        supportActionBar?.title = category.name
        supportActionBar?.subtitle = category.id

        binding.biographyContent.publisherTextView.text = category.id*/
    }

    /*fun getRetrofit(): CategoryService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CategoryService::class.java)
    }*/

    /*fun getSuperheroById(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = getRetrofit()
                category = service.findSuperheroById(id)

                CoroutineScope(Dispatchers.Main).launch {
                    loadData()
                }
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }*/
}