package com.example.wedding_book_keeper.presentation.view.donation.manager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.presentation.view.donation.couple.GuestDonationInfo

class ManagerMainDonationAdapter(var guestList: MutableList<GuestDonationInfo>) :
    RecyclerView.Adapter<ManagerMainDonationAdapter.CustomViewHolder>() {

    private var originalList: List<GuestDonationInfo> = guestList.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.main_item_manager, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return guestList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = guestList[position]
        holder.side.text = item.side
        holder.relation.text = item.relation
        holder.guestName.text = item.guestName
        holder.amount.text = item.formattedAmount.toString()
        holder.donationDate.text = item.donationDate.toString()
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val side = itemView.findViewById<TextView>(R.id.txt_side)
        val relation = itemView.findViewById<TextView>(R.id.txt_relation)
        val guestName = itemView.findViewById<TextView>(R.id.txt_guest_name)
        val amount = itemView.findViewById<TextView>(R.id.txt_gift_amount)
        val donationDate = itemView.findViewById<TextView>(R.id.txt_donation_date)
    }

    // 새로운 아이템 세팅 함수에서도 원본 데이터를 갱신해줍니다.
    fun setItems(items: MutableList<GuestDonationInfo>) {
        this.guestList = items
        this.originalList = items.toList()
        notifyDataSetChanged()
    }

    fun filter(filterText: String) {
        guestList = if (filterText.isEmpty()) {
            originalList.toMutableList()
        } else {
            originalList.filter {
                it.guestName.contains(filterText, ignoreCase = true)
                // 필요하다면 다른 필터링 조건도 추가할 수 있습니다.
            }.toMutableList()
        }
        notifyDataSetChanged() // 데이터가 변경되었음을 어댑터에 알림
    }
}
