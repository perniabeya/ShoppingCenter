package com.example.shoppingcenter.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.shoppingcenter.R
import com.example.shoppingcenter.adapters.CategoryAdapter
import com.example.shoppingcenter.data.Category
import com.example.shoppingcenter.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    val categoryList = Category.getAll()

    lateinit var binding: ActivityMainBinding

    lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapter = CategoryAdapter(categoryList) { position ->
            val category = categoryList[position]
            Toast.makeText(this, category.name, Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
    }
}