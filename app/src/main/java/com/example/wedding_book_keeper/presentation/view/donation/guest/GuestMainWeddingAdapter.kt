package com.example.wedding_book_keeper.presentation.view.donation.guest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wedding_book_keeper.R

class GuestMainWeddingAdapter(private val weddingList: MutableList<GuestWeddingInfo>) :
    RecyclerView.Adapter<GuestMainWeddingAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.main_item_guest, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return weddingList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = weddingList[position]
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

    fun setItems(items: MutableList<GuestWeddingInfo>) {
        weddingList.addAll(items)
    }
}
