package com.example.thusqlite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Rcvadapter(private val ds: MutableList<thongtinhs>,private var banitem:(thongtinhs)->Unit):RecyclerView.Adapter<Rcvadapter.ViewHolder>() {
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var tvid = view.findViewById<TextView>(R.id.tvid)
        var tvten = view.findViewById<TextView>(R.id.tvten)
        var tvgioitinh = view.findViewById<TextView>(R.id.tvgioitinh)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Rcvadapter.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Rcvadapter.ViewHolder, position: Int) {
        var ketnoi = ds[position]
        holder.itemView.setOnClickListener {
            banitem(ketnoi)
        }
        holder.tvid.text = ketnoi.id.toString()
        holder.tvten.text = ketnoi.ten
        holder.tvgioitinh.text = ketnoi.gioitinh
    }

    override fun getItemCount(): Int {
        return ds.size
    }
}
