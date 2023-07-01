package com.example.wedding_book_keeper.presentation.view.mypage

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.FragmentChangeRoleBinding
import com.example.wedding_book_keeper.presentation.config.BaseFragment
import com.example.wedding_book_keeper.presentation.view.donation.couple.CoupleMainActivity
import com.example.wedding_book_keeper.presentation.view.donation.guest.GuestMainActivity
import com.example.wedding_book_keeper.presentation.view.donation.manager.ManagerMainActivity
import com.example.wedding_book_keeper.presentation.view.wedding.partner.PartnerConnectActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChangeRoleFragment(
    val onClick: (Long) -> Unit,
) : BottomSheetDialogFragment() {
    lateinit var binding: FragmentChangeRoleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentChangeRoleBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtRoleAdmin.setOnClickListener {
            val intent = Intent(requireContext(), PartnerConnectActivity::class.java)
            startActivity(intent)
        }

        binding.txtRoleCouple.setOnClickListener{
            val intent = Intent(requireContext(), CoupleMainActivity::class.java)
            startActivity(intent)
        }

        binding.txtRoleGuest.setOnClickListener{
            val intent = Intent(requireContext(), GuestMainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {
        const val TAG = "MoreActionModal"

        fun newInstance(
            onClick: (Long) -> Unit,
        ): ChangeRoleFragment {
            val modal = ChangeRoleFragment(onClick)
            return modal
        }
    }
}
