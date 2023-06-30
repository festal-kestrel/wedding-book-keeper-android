package com.example.wedding_book_keeper.presentation.view.donation.couple

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.FragmentFilterBinding
import com.example.wedding_book_keeper.presentation.view.mypage.ChangeRoleFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FilterFragment(val onClick: (Long) -> Unit) : BottomSheetDialogFragment() {
    lateinit var binding: FragmentFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {
        const val TAG = "MoreActionModal"

        fun newInstance(onClick: (Long) -> Unit): FilterFragment {
            val modal = FilterFragment(onClick)
            return modal
        }
    }
}
