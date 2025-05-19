package com.example.bobabliss

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class BobaDetailActivity : AppCompatActivity() {

    private var quantity = 1
    private var price = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boba_detail)

        // Setup toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Boba Details"

        // Receive data from intent
        val image = intent.getIntExtra("imageResId", R.drawable.boba1)
        val title = intent.getStringExtra("title") ?: "N/A"
        val flavor = intent.getStringExtra("flavor") ?: "N/A"
        val description = intent.getStringExtra("description") ?: "No details available"
        val volume = intent.getStringExtra("volume") ?: "0ml"
        price = intent.getDoubleExtra("price", 0.0)

        // Bind views
        val imageView = findViewById<ImageView>(R.id.detailImage)
        val titleText = findViewById<TextView>(R.id.detailTitle)
        val flavorText = findViewById<TextView>(R.id.detailFlavor)
        val descText = findViewById<TextView>(R.id.detailDescription)
        val volumeText = findViewById<TextView>(R.id.detailVolume)
        val priceText = findViewById<TextView>(R.id.detailPrice)
        val quantityText = findViewById<TextView>(R.id.quantityText)
        val plusBtn = findViewById<ImageView>(R.id.plusButton)
        val minusBtn = findViewById<ImageView>(R.id.minusButton)
        val buyNowBtn = findViewById<Button>(R.id.buyNowButton)
        val quantityLayout = minusBtn.parent as LinearLayout

        // Set content
        imageView.setImageResource(image)
        titleText.text = title
        flavorText.text = flavor
        descText.text = description
        volumeText.text = "Volume: $volume"
        priceText.text = "Price: ${String.format("%.2f", price)} AED"
        quantityText.text = quantity.toString()

        // Quantity controls
        plusBtn.setOnClickListener {
            quantity++
            quantityText.text = quantity.toString()
        }

        minusBtn.setOnClickListener {
            if (quantity > 1) {
                quantity--
                quantityText.text = quantity.toString()
            }
        }

        // Buy Now action → Go to BillReceiptActivity
        buyNowBtn.setOnClickListener {
            val intent = Intent(this, BillReceiptActivity::class.java).apply {
                putExtra("imageResId", image)
                putExtra("title", title)
                putExtra("flavor", flavor)
                putExtra("quantity", quantity)
                putExtra("price", price)
            }
            startActivity(intent)
        }

        // Load animations
        val zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
        val slideTop = AnimationUtils.loadAnimation(this, R.anim.slide_in_top)
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val slideBottom = AnimationUtils.loadAnimation(this, R.anim.slide_in_bottom)

        // Apply animations
        imageView.startAnimation(zoomIn)
        titleText.startAnimation(slideTop)
        flavorText.startAnimation(slideTop)
        descText.startAnimation(fadeIn)
        volumeText.startAnimation(fadeIn)
        priceText.startAnimation(fadeIn)
        quantityLayout.startAnimation(slideBottom)
        buyNowBtn.startAnimation(slideBottom)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
