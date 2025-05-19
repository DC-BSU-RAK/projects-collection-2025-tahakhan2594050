package com.example.bobabliss

import android.app.AlertDialog
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TeaMenuActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private var isPlaying = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tea_menu)

        // 1) Start and loop background music
        mediaPlayer = MediaPlayer.create(this, R.raw.bgm).apply {
            isLooping = true
            start()
        }

        // 2) Music toggle button
        val musicToggle = findViewById<ImageButton>(R.id.musicToggle)
        musicToggle.setOnClickListener {
            if (isPlaying) {
                mediaPlayer.pause()
                musicToggle.setImageResource(R.drawable.ic_music_off)
            } else {
                mediaPlayer.start()
                musicToggle.setImageResource(R.drawable.ic_music_note)
            }
            isPlaying = !isPlaying
        }

        // 3) Animate toolbar
        val toolbar = findViewById<LinearLayout>(R.id.toolbarLayout)
        val toolbarAnim = AnimationUtils.loadAnimation(this, R.anim.slide_in_top)
        toolbar.startAnimation(toolbarAnim)

        // 4) Set up and animate RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        recyclerView.startAnimation(fadeIn)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.itemAnimator = DefaultItemAnimator()

        // 5) Populate list
        val bobaList = listOf(
            BobaItem(
                R.drawable.boba1,
                "Fresh Orange",
                "Citrus Burst Boba",
                "Clean and earthy, this boba is made from premium organic green tea and sweetened naturally for a light, energizing drink.",
                "160ml",
                4.99
            ),
            BobaItem(
                R.drawable.boba2,
                "Organic Green",
                "Nature’s Sip",
                "Rich mango flavor blended with creamy tea – a Taurus favorite.",
                "180ml",
                5.49
            ),
            BobaItem(
                R.drawable.boba3,
                "Taro Milk Tea",
                "Purple Comfort",
                "A sweet and creamy classic made with taro root, offering a nutty vanilla flavor and smooth purple hue, paired with chewy pearls.",
                "170ml",
                4.79
            ),
            BobaItem(
                R.drawable.boba4,
                "Caramel Coffee",
                "Sweet Mocha Bliss",
                "A rich fusion of bold coffee and velvety caramel, this drink is for caffeine lovers who crave a dessert-like finish.",
                "160ml",
                5.29
            ),
            BobaItem(
                R.drawable.boba5,
                "Mango Graham",
                "Tropical Crunch",
                "A playful mix of creamy mango and crushed graham crackers that delivers both sweetness and texture in every sip.",
                "180ml",
                5.99
            ),
            BobaItem(
                R.drawable.boba6,
                "Strawberry Milk",
                "Berry Sweet Treat",
                "Bursting with strawberry flavor and topped with jellies, this pink drink brings a fruity twist to traditional milk tea.",
                "170ml",
                4.99
            ),
            BobaItem(
                R.drawable.boba7,
                "Wintermalt",
                "Malted Cream Delight",
                "A cozy drink combining winter flavors with creamy malt and tapioca, ideal for sipping on chilly days.",
                "160ml",
                5.19
            ),
            BobaItem(
                R.drawable.boba8,
                "Chocolate Milk",
                "Cocoa Cream Classic",
                "Rich and indulgent, this chocolate milk tea is a dessert in a cup, complete with smooth tapioca pearls.",
                "180ml",
                5.59
            )
        )
        recyclerView.adapter = BobaAdapter(bobaList)

        // 6) Info popup
        val infoIcon = findViewById<ImageView>(R.id.ic_info)
        infoIcon.setOnClickListener { showInfoPopup() }
    }

    private fun showInfoPopup() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_info, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(true)
            .create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialogView.findViewById<Button>(R.id.gotItButton).setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
