package com.example.wedding_book_keeper.presentation.view.donation.guest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wedding_book_keeper.R

class GuestMainWeddingAdapter(private var weddingList: MutableList<GuestWeddingInfo>) :
    RecyclerView.Adapter<GuestMainWeddingAdapter.CustomViewHolder>() {
    private var filteredWeddingList: MutableList<GuestWeddingInfo> = weddingList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.main_item_guest, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredWeddingList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = filteredWeddingList[position]
        holder.groomName.text = item.groomName
        holder.bridalName.text = item.bridalName
        holder.amount.text = item.formattedAmount.toString()
        holder.donationDate.text = item.donationDate.toString()
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val groomName = itemView.findViewById<TextView>(R.id.txt_groom_name)
        val bridalName = itemView.findViewById<TextView>(R.id.txt_bridal_name)
        val amount = itemView.findViewById<TextView>(R.id.txt_amount)
        val donationDate = itemView.findViewById<TextView>(R.id.txt_donation_date)
    }

    fun setItems(items: List<GuestWeddingInfo>) {
        weddingList = items.toMutableList()
        filteredWeddingList = items.toMutableList()
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredWeddingList = if (query.isEmpty()) {
            weddingList
        } else {
            weddingList.filter { it.groomName.contains(query, true) || it.bridalName.contains(query, true) }.toMutableList()
        }
        notifyDataSetChanged()
    }
}
