package com.example.vacationspotcalculator

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private lateinit var weatherSpinner: Spinner
    private lateinit var budgetSpinner: Spinner
    private lateinit var activitySpinner: Spinner
    private lateinit var resultText: TextView
    private lateinit var resultCard: CardView
    private lateinit var calculateBtn: Button
    private lateinit var infoBtn: Button
    private lateinit var gifBackground: ImageView

    data class Destination(
        val weather: String,
        val budget: String,
        val activity: String,
        val location: String
    )

    private val destinations = listOf(
        Destination("Hot", "Low", "Relaxing", "Goa, India"),
        Destination("Hot", "Medium", "Relaxing", "Phuket, Thailand"),
        Destination("Hot", "High", "Adventurous", "Dubai, UAE"),
        Destination("Hot", "High", "Cultural", "Cairo, Egypt"),
        Destination("Cold", "Low", "Relaxing", "Shimla, India"),
        Destination("Cold", "Medium", "Cultural", "Prague, Czech Republic"),
        Destination("Cold", "High", "Adventurous", "Swiss Alps"),
        Destination("Cold", "High", "Relaxing", "Reykjavik, Iceland"),
        Destination("Mild", "Low", "Relaxing", "Hoi An, Vietnam"),
        Destination("Mild", "Medium", "Cultural", "Kyoto, Japan"),
        Destination("Mild", "High", "Adventurous", "Queenstown, New Zealand"),
        Destination("Rainy", "Low", "Relaxing", "Kerala, India"),
        Destination("Rainy", "Medium", "Cultural", "Bogotá, Colombia"),
        Destination("Rainy", "High", "Adventurous", "Amazon Rainforest")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Views
        gifBackground = findViewById(R.id.gifBackground)
        weatherSpinner = findViewById(R.id.weatherSpinner)
        budgetSpinner = findViewById(R.id.budgetSpinner)
        activitySpinner = findViewById(R.id.activitySpinner)
        resultText = findViewById(R.id.resultText)
        resultCard = findViewById(R.id.resultCard)
        calculateBtn = findViewById(R.id.calculateBtn)
        infoBtn = findViewById(R.id.infoBtn)

        // Load GIF Background
        Glide.with(this)
            .asGif()
            .load(R.drawable.travel_bg)
            .into(gifBackground)

        // Populate spinners
        weatherSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayOf("Hot", "Cold", "Mild", "Rainy"))
        budgetSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayOf("Low", "Medium", "High"))
        activitySpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayOf("Relaxing", "Adventurous", "Cultural"))

        // ✳️ Animations
        val fadeZoomIn = AnimationUtils.loadAnimation(this, R.anim.fade_zoom_in)
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        val scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down)
        val scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up)
        val fadeInModal = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        // ✳️ Startup fadeZoomIn animation with delays
        weatherSpinner.postDelayed({ weatherSpinner.startAnimation(fadeZoomIn) }, 100)
        budgetSpinner.postDelayed({ budgetSpinner.startAnimation(fadeZoomIn) }, 200)
        activitySpinner.postDelayed({ activitySpinner.startAnimation(fadeZoomIn) }, 300)
        calculateBtn.postDelayed({ calculateBtn.startAnimation(fadeZoomIn) }, 400)
        infoBtn.postDelayed({ infoBtn.startAnimation(fadeZoomIn) }, 500)
        resultCard.postDelayed({ resultCard.startAnimation(fadeZoomIn) }, 600)

        // ✳️ Button scale animations on press
        calculateBtn.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> v.startAnimation(scaleDown)
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> v.startAnimation(scaleUp)
            }
            false
        }

        infoBtn.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> v.startAnimation(scaleDown)
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> v.startAnimation(scaleUp)
            }
            false
        }

        // Calculate destination logic
        calculateBtn.setOnClickListener {
            val weather = weatherSpinner.selectedItem.toString()
            val budget = budgetSpinner.selectedItem.toString()
            val activity = activitySpinner.selectedItem.toString()
            val destination = calculateDestination(weather, budget, activity)

            resultText.text = "You should visit: $destination 🌍"
            resultCard.visibility = View.VISIBLE
            resultCard.startAnimation(slideUp)
        }

        // Show Info Modal with fade-in and close button
        infoBtn.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.modal_info)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val modalRoot = dialog.findViewById<View>(R.id.modalRoot)
            val closeModalBtn = dialog.findViewById<ImageView>(R.id.closeModalBtn)

            modalRoot?.startAnimation(fadeInModal)

            closeModalBtn?.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }
    }

    private fun calculateDestination(weather: String, budget: String, activity: String): String {
        val match = destinations.find {
            it.weather == weather && it.budget == budget && it.activity == activity
        }
        return match?.location ?: "Bali, Indonesia"
    }
}
