package com.example.bobabliss

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class BillReceiptActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill_receipt)

        // Retrieve passed data
        val imageResId = intent.getIntExtra("imageResId", R.drawable.boba1)
        val title = intent.getStringExtra("title") ?: "Unknown Boba"
        val flavor = intent.getStringExtra("flavor") ?: "Unknown Flavor"
        val quantity = intent.getIntExtra("quantity", 1)
        val price = intent.getDoubleExtra("price", 0.0)
        val total = quantity * price

        // Find views
        val receiptImage = findViewById<ImageView>(R.id.receiptImage)
        val receiptDetails = findViewById<TextView>(R.id.receiptDetails)
        val nextButton = findViewById<Button>(R.id.nextButton)

        // Set data into views
        receiptImage.setImageResource(imageResId)

        val formattedDetails = """
            🧋 Tea: $title
            🍯 Flavor: $flavor
            🔢 Quantity: $quantity
            💰 Price per cup: ${String.format("%.2f", price)} AED
            ───────────────────────
            🧾 Total: ${String.format("%.2f", total)} AED
        """.trimIndent()

        receiptDetails.text = formattedDetails

        // Next button navigates to EndActivity (Thank You page)
        nextButton.setOnClickListener {
            val intent = Intent(this, EndActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
