package com.example.wedding_book_keeper.presentation.view.donation.manager

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.WeddingBookKeeperApplication
import com.example.wedding_book_keeper.data.remote.WeddingBookKeeperClient
import com.example.wedding_book_keeper.data.remote.response.GuestDonationReceiptResponse
import com.example.wedding_book_keeper.presentation.view.donation.couple.GuestDonationInfo
import com.example.wedding_book_keeper.presentation.view.donation.manager.ApprovalDialogFragment.Companion.TAG
import com.example.wedding_book_keeper.presentation.view.mypage.CoupleMyPageActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Locale

class ManagerMainDonationAdapter(
    private val fragmentManager: FragmentManager,

    var guestList: MutableList<GuestDonationInfo>) :
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
        holder.donationDate.text = item.weddingDate?.let { formatDate(it) }
        holder.checkBox.isChecked = item.isChecked
        holder.guestId.text = item.guestId.toString()

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked = isChecked
        }

        holder.checkBox.setOnClickListener{
            if (item.isChecked){
                val dialogFragment = ApprovalDialogFragment.newInstance()
                dialogFragment.setOnApprovalListener(object :
                    ApprovalDialogFragment.OnApprovalListener {
                    override fun onApproval() {
                        // 승인 api 호출
                        WeddingBookKeeperClient.weddingService.patchDonationApproval(WeddingBookKeeperApplication.prefs.weddingId, Integer.parseInt(holder.guestId.text.toString()))
                            .enqueue(object : Callback<Unit> {
                                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                                    if (response.isSuccessful) {
                                        val body = response.body()
                                        body?.let {
                                            Log.d("Wedding", "승인성공")
                                        }
                                    }
                                }

                                override fun onFailure(call: Call<Unit>, t: Throwable) {
                                    Log.e("patchDonationApproval", "Error: ${t.message}")
                                }
                            })
                        fragmentManager.popBackStack()
                    }

                    @RequiresApi(Build.VERSION_CODES.O)
                    override fun onCancel() {
                        holder.checkBox.isChecked = false
                    }
                })
                dialogFragment.show(fragmentManager, TAG)
            }
            else{
                val dialogFragment = RejectionDialogFragment.newInstance()
                dialogFragment.setOnRejectionListener(object :
                    RejectionDialogFragment.OnRejectionListener {
                    override fun onRejection() {
                        // 반려 api 호출
                        WeddingBookKeeperClient.weddingService.patchDonationRejection(weddingId = 90, Integer.parseInt(holder.guestId.text.toString()))
                            .enqueue(object : Callback<Unit> {
                                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                                    if (response.isSuccessful) {
                                        val body = response.body()
                                        body?.let {
                                            Log.d("Wedding", "반려성공")
                                        }
                                    }
                                }
                                override fun onFailure(call: Call<Unit>, t: Throwable) {
                                    Log.e("patchDonationRejection", "Error: ${t.message}")
                                }
                            })
                        fragmentManager.popBackStack()
                    }

                    @RequiresApi(Build.VERSION_CODES.O)
                    override fun onCancel() {
                        holder.checkBox.isChecked = true
                    }
                })
                dialogFragment.show(fragmentManager, TAG)
            }
        }
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val side = itemView.findViewById<TextView>(R.id.txt_side)
        val relation = itemView.findViewById<TextView>(R.id.txt_relation)
        val guestName = itemView.findViewById<TextView>(R.id.txt_guest_name)
        val amount = itemView.findViewById<TextView>(R.id.txt_gift_amount)
        val donationDate = itemView.findViewById<TextView>(R.id.txt_donation_date)
        val checkBox = itemView.findViewById<CheckBox>(R.id.btn_checkbox)
        val guestId = itemView.findViewById<TextView>(R.id.txt_guest_id)
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
    private fun formatDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yy.MM.dd  HH:mm", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        return outputFormat.format(date)
    }
}
