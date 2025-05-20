package com.example.bobabliss

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BobaAdapter(private val items: List<BobaItem>) :
    RecyclerView.Adapter<BobaAdapter.BobaViewHolder>() {

    class BobaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bobaImage: ImageView = itemView.findViewById(R.id.bobaImage)
        val bobaTitle: TextView = itemView.findViewById(R.id.bobaTitle)
        val bobaFlavor: TextView = itemView.findViewById(R.id.bobaFlavor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BobaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_boba, parent, false)
        return BobaViewHolder(view)
    }

    override fun onBindViewHolder(holder: BobaViewHolder, position: Int) {
        val item = items[position]
        holder.bobaImage.setImageResource(item.imageResId)
        holder.bobaTitle.text = item.title
        holder.bobaFlavor.text = item.flavor

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, BobaDetailActivity::class.java).apply {
                putExtra("imageResId", item.imageResId)
                putExtra("title", item.title)
                putExtra("flavor", item.flavor)
                putExtra("description", item.description)
                putExtra("volume", item.volume)
                putExtra("price", item.price)  // ✅ Including price
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}
