package com.example.shoppingcenter.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shoppingcenter.R
import com.example.shoppingcenter.data.Product
import com.example.shoppingcenter.data.ProductsService
import com.example.shoppingcenter.databinding.ActivityProductBinding
import com.example.shoppingcenter.utils.SessionManager
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.appcompat.app.AlertDialog

class ProductActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductBinding
    lateinit var product: Product
    var isFavorite = false
    lateinit var favoriteMenu: MenuItem
    lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_product)
        enableEdgeToEdge()

        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        session = SessionManager(this)

        val id = intent.getStringExtra("PRODUCT_ID")!!

        isFavorite = session.isFavorite(id)

        getProductById(id)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_product, menu)
        this.favoriteMenu = menu.findItem(R.id.action_favorite)
        setFavoriteIcon()
        return true
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)

        // Establece el título y el mensaje del diálogo
        builder.setTitle(R.string.dialog_delete_confirmation_title)
        builder.setMessage(R.string.dialog_delete_confirmation_message)

        // Define las acciones de los botones
        builder.setPositiveButton(R.string.action_yes) { dialog, which ->
            // Acción cuando se presiona "Sí"
            // Aquí puedes agregar lo que quieres hacer

            session.removeFavorite(product.id)
            isFavorite = false
            setFavoriteIcon()
        }

        builder.setNegativeButton(R.string.action_no) { dialog, which ->
            // Acción cuando se presiona "No"
            // Aquí puedes agregar lo que quieres hacer
        }

        // Crea y muestra el diálogo
        val dialog = builder.create()
        dialog.show()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {
                if (!isFavorite) {
                    session.addFavorite(product.id)
                    isFavorite = true
                } else {
                    showDialog()
                }
                setFavoriteIcon()
                return true
            }

            R.id.action_share -> {

                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun setFavoriteIcon() {
        if (isFavorite) {
            favoriteMenu.setIcon(R.drawable.ic_favorite_selected)
        } else {
            favoriteMenu.setIcon(R.drawable.ic_favorite)
        }
    }

    @SuppressLint("SetTextI18n")
    fun loadData() {
        supportActionBar?.title = product.title
        binding.productTextView.text = product.title
        binding.descriptionTextView.text = product.description
        binding.priceTextView.text = getString(R.string.text_price) +" : ${product.price}€"
        binding.ratingTextView.text = getString(R.string.text_rating) + " : ${product.rating}"
        //binding.ratingTextView.text = product.rating.toString()+R.string.text_rating

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