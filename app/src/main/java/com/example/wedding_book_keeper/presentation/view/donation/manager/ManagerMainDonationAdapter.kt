package com.example.wedding_book_keeper.presentation.view.donation.manager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.data.remote.response.GuestDonationReceiptResponse
import com.example.wedding_book_keeper.presentation.view.donation.couple.CoupleMainDonationAdapter
import com.example.wedding_book_keeper.presentation.view.donation.couple.GuestDonationInfo

class ManagerMainDonationAdapter(var guestList: MutableList<GuestDonationInfo>) :
    RecyclerView.Adapter<ManagerMainDonationAdapter.CustomViewHolder>() {
    private var filteredGuestList: MutableList<GuestDonationInfo> = guestList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.main_item_manager, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredGuestList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = filteredGuestList[position]
        holder.side.text = item.guestSide
        holder.relation.text = item.relation
        holder.guestName.text = item.guestName
        holder.amount.text = item.formattedAmount
        holder.donationDate.text = item.weddingDate
        holder.checkBox.isChecked = item.isChecked

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked = isChecked
        }
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val side = itemView.findViewById<TextView>(R.id.txt_side)
        val relation = itemView.findViewById<TextView>(R.id.txt_relation)
        val guestName = itemView.findViewById<TextView>(R.id.txt_guest_name)
        val amount = itemView.findViewById<TextView>(R.id.txt_gift_amount)
        val donationDate = itemView.findViewById<TextView>(R.id.txt_donation_date)
        val checkBox = itemView.findViewById<CheckBox>(R.id.btn_checkbox)
    }

    fun setItemsApi(guestDonationReceipts: MutableList<GuestDonationReceiptResponse>) {
        val guestDonationInfos = convertToGuestDonationInfo(guestDonationReceipts)
        guestList = guestDonationInfos.toMutableList()
        filteredGuestList = guestDonationInfos.toMutableList()

        guestList.forEachIndexed { index, guestDonationInfo ->
            guestDonationInfo.isChecked = guestDonationReceipts[index].hasPaid
        }

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
    
    private fun convertToGuestDonationInfo(guestDonationReceipts: MutableList<GuestDonationReceiptResponse>): MutableList<GuestDonationInfo> {
        val guestDonationInfos = mutableListOf<GuestDonationInfo>()
        for (guestDonationReceipt in guestDonationReceipts) {
            val guestDonationInfo = GuestDonationInfo.convertToGuestDonationInfo(guestDonationReceipt)
            guestDonationInfos.add(guestDonationInfo)
        }
        return guestDonationInfos
    }
}
