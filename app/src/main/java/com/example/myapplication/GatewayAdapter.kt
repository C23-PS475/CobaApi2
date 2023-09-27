package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GatewayAdapter(private val context: Context, private val gatewayList: List<Gateway>) :
    RecyclerView.Adapter<GatewayAdapter.ViewHolder>() {

    // Kelas ViewHolder untuk mendeklarasikan elemen UI yang akan ditampilkan pada setiap item
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gwIdTextView: TextView = itemView.findViewById(R.id.gwIdTextView)
        val gwNameTextView: TextView = itemView.findViewById(R.id.gwNameTextView)
        val distanceTextView: TextView = itemView.findViewById(R.id.distanceTextView)
    }

    // Override method untuk membuat ViewHolder baru
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gateway_list, parent, false)
        return ViewHolder(view)
    }

    // Override method untuk mengikat data ke ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gateway = gatewayList[position]

        // Set data ke elemen UI dalam ViewHolder
        holder.gwIdTextView.text = "GW ID: ${gateway.gw_id}"
        holder.gwNameTextView.text = "GW Name: ${gateway.gw_name}"
        holder.distanceTextView.text = "Distance: ${gateway.distance}"
    }

    // Override method untuk mengembalikan jumlah item dalam daftar
    override fun getItemCount(): Int {
        return gatewayList.size
    }
}
