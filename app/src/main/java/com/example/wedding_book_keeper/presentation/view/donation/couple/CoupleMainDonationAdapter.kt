package com.example.wedding_book_keeper.presentation.view.donation.couple

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.presentation.view.donation.guest.GuestWeddingInfo

class CoupleMainDonationAdapter(var guestList: MutableList<GuestDonationInfo>) :
    RecyclerView.Adapter<CoupleMainDonationAdapter.CustomViewHolder>() {
    private var filteredGuestList: MutableList<GuestDonationInfo> = guestList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.main_item_couple, parent, false)
        return CustomViewHolder(view)
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = filteredGuestList[position]
        holder.side.text = item.side
        holder.relation.text = item.relation
        holder.guestName.text = item.guestName
        holder.amount.text = item.formattedAmount.toString()
        holder.donationDate.text = item.donationDate.toString()
    }

    override fun getItemCount(): Int {
        return filteredGuestList.size
    }


    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val side = itemView.findViewById<TextView>(R.id.txt_side)
        val relation = itemView.findViewById<TextView>(R.id.txt_relation)
        val guestName = itemView.findViewById<TextView>(R.id.txt_guest_name)
        val amount = itemView.findViewById<TextView>(R.id.txt_amount)
        val donationDate = itemView.findViewById<TextView>(R.id.txt_donation_date)
    }

    fun setItems(items: List<GuestDonationInfo>) {
        guestList = items.toMutableList()
        filteredGuestList = items.toMutableList()
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredGuestList = if (query.isEmpty()) {
            guestList
        } else {
            guestList.filter { it.guestName.contains(query, true) }.toMutableList()
        }
        notifyDataSetChanged()
    }
}
