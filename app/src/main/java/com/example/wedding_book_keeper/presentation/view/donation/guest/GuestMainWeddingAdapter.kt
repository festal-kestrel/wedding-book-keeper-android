package com.example.wedding_book_keeper.presentation.view.donation.guest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.data.remote.response.DonationReceiptResponse
import com.example.wedding_book_keeper.presentation.view.donation.guest.GuestWeddingInfo.Companion.convertToGuestWeddingInfo
import java.text.SimpleDateFormat
import java.util.Locale

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
        holder.brideName.text = item.brideName
        holder.amount.text = item.formattedAmount
        holder.donationDate.text = item.weddingDate?.let { formatDate(it) }
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val groomName = itemView.findViewById<TextView>(R.id.txt_groom_name)
        val brideName = itemView.findViewById<TextView>(R.id.txt_bridal_name)
        val amount = itemView.findViewById<TextView>(R.id.txt_gift_amount)
        val donationDate = itemView.findViewById<TextView>(R.id.txt_donation_date)
    }

    fun setItemsApi(donationReceipts: MutableList<DonationReceiptResponse>) {
        val guestWeddingInfos = convertToGuestWeddingInfo(donationReceipts)
        weddingList = guestWeddingInfos.toMutableList()
        filteredWeddingList = guestWeddingInfos.toMutableList()
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredWeddingList = if (query.isEmpty()) {
            weddingList
        } else {
            weddingList.filter { it.groomName!!.contains(query, true) || it.brideName!!.contains(query, true) }.toMutableList()
        }
        notifyDataSetChanged()
    }

    private fun convertToGuestWeddingInfo(donationReceipts: MutableList<DonationReceiptResponse>): MutableList<GuestWeddingInfo> {
        val guestWeddingInfos = mutableListOf<GuestWeddingInfo>()
        for (donationReceipt in donationReceipts) {
            val guestWeddingInfo = convertToGuestWeddingInfo(donationReceipt)
            guestWeddingInfos.add(guestWeddingInfo)
        }
        return guestWeddingInfos;
    }
    private fun formatDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yy.MM.dd  HH:mm", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        return outputFormat.format(date)
    }
}
